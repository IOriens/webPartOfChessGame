package cn.edu.cqu.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.cqu.kb.model.Rule;

/**
 * @author quan
 * @describe 保存搜索过的情况的搜索结果
 *
 */
public class Holder {
	private HashMap<String, S> situation;
	private File mapFile;

	private int getRequest = 0, match = 0, matchPart = 0, putRequest = 0, add = 0, update = 0, diff = 0;

	private Holder(File mapFile) {
		this.mapFile = mapFile;
		if (mapFile.exists())
			readFromFile(mapFile);
		else
			situation = new HashMap<>(1000000);
	}

	public static Holder getInstance(String chessFile) {
		File mapFile = new File(chessFile + ".map");
		Holder holder = new Holder(mapFile);
		return holder;
	}

	public void initCountInfo() {
		getRequest = 0;
		match = 0;
		matchPart = 0;
		putRequest = 0;
		add = 0;
		update = 0;
		diff = 0;
	}

	public void printCountInfo() {
		System.err.println(
				"getRequest = " + getRequest + ", match = " + match + ", matchPart = " + matchPart + "\nputRequest = " + putRequest + ", add = " + add + ", update = " + update + ", diff = " + diff);
	}

	public void addSituation(Chessboard chessboard, int height) {
		putRequest++;
		List<Rule> rules = chessboard.getReasonList();
		String key = chessboard.getKey();
		S s = situation.get(key);
		if (s != null) {
			if (s.height < height && rules.size() > 0) {
				update++;
				s.height = height;
				s.alpha = chessboard.getAlpha();
				s.beta = chessboard.getBeta();
				s.reasonList.clear();
				s.reasonList.addAll(rules);
				s.rule = rules.get(0);
				s.cost = chessboard.getCost();
				// TODO 代码待检验
				s.extendAlpha = chessboard.isExtendAlpha();
				s.extendBeta = chessboard.isExtendBeta();
				situation.put(key, s);
			}
		} else if (rules.size() > 0) {
			add++;
			s = new S();
			s.height = height;
			s.alpha = chessboard.getAlpha();
			s.beta = chessboard.getBeta();
			s.reasonList = new ArrayList<>();
			s.reasonList.addAll(rules);
			s.rule = rules.get(0);
			s.cost = chessboard.getCost();
			// TODO 代码重新写
			s.extendAlpha = chessboard.isExtendAlpha();
			s.extendBeta = chessboard.isExtendBeta();
			situation.put(key, s);
		}
	}

	public boolean getRule(Chessboard chessboard, int height) {
		getRequest++;

		S s = situation.get(chessboard.getKey());
		if (s != null) {
			if (s.height >= height) {
				int diff = chessboard.getCost() - s.cost;
				chessboard.setAlpha(s.alpha + diff);
				chessboard.setBeta(s.beta + diff);
				// TODO 推理链全部加进去对推理路径的选择会不会产生影响
				chessboard.setReasonList(s.reasonList);
				// TODO 代码重新写
				chessboard.setExtendAlpha(s.extendAlpha);
				chessboard.setExtendBeta(s.extendBeta);
				match++;
				if (diff != 0)
					diff++;
				return true;
			} else {
				chessboard.promoteRule(s.rule);
				matchPart++;
				return false;
			}
		} else
			return false;
	}

	public int size() {
		return situation.size();
	}

	public void writeToFile() {
		FileOutputStream out;
		try {
			out = new FileOutputStream(mapFile);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(situation);
			objOut.flush();
			objOut.close();
			System.out.println("write " + size() + " history to file");
		} catch (IOException e) {
			System.out.println("write object failed");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void readFromFile(File file) {
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(in);
			situation = (HashMap<String, S>) objIn.readObject();
			objIn.close();
			System.err.println("read " + size() + " history from file");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("read history from file failed" + file.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("read history from file failed" + file.getName());
		}
	}
}

class S implements Serializable {
	private static final long serialVersionUID = 3215108442982386688L;
	int height, alpha, beta, cost;
	boolean extendAlpha, extendBeta;
	Rule rule;
	ArrayList<Rule> reasonList;
}
