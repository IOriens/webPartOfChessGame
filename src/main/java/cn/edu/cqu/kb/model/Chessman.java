package cn.edu.cqu.kb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.edu.cqu.kb.model.MRuleExample.Criteria;
import cn.edu.cqu.util.Mappers;

public class Chessman implements Serializable{
	private static final long serialVersionUID = -440171240233291829L;

	public static final int 黑将 = 21, 红将 = 1;

	private Integer chessid;

	private Integer score;

	private String type;

	private Boolean isred;

	private ArrayList<Rule> rules;

	public Integer getChessid() {
		return chessid;
	}

	public void setChessid(Integer chessid) {
		this.chessid = chessid;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public ChessmanType getChessType() {
		final ChessmanType type = ChessmanType.valueOf(this.type);
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Boolean getIsred() {
		return isred;
	}

	public void setIsred(Boolean isred) {
		this.isred = isred;
	}

	@Override
	public int hashCode() {
		return chessid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chessman other = (Chessman) obj;
		if (chessid == null) {
			if (other.chessid != null)
				return false;
		} else if (!chessid.equals(other.chessid))
			return false;
		if (isred == null) {
			if (other.isred != null)
				return false;
		} else if (!isred.equals(other.isred))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public ArrayList<Rule> getRules() {
		if (rules == null) {
			switch (getChessType()) {
			case 马:
				MRuleExample mRuleExample = new MRuleExample();
				Criteria mcriteria = mRuleExample.createCriteria();
				mcriteria.andChessidEqualTo(chessid);
				final List<MRule> mRules = Mappers.getMRuleMapper().selectByExample(mRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(mRules);
				break;
			case 兵:
				BRuleExample bRuleExample = new BRuleExample();
				cn.edu.cqu.kb.model.BRuleExample.Criteria bCriteria = bRuleExample.createCriteria();
				bCriteria.andChessidEqualTo(chessid);
				final List<BRule> bRules = Mappers.getBRuleMapper().selectByExample(bRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(bRules);
				break;
			case 士:
				SRuleExample sRuleExample = new SRuleExample();
				cn.edu.cqu.kb.model.SRuleExample.Criteria sCriteria = sRuleExample.createCriteria();
				sCriteria.andChessidEqualTo(chessid);
				final List<SRule> sRules = Mappers.getSRuleMapper().selectByExample(sRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(sRules);
				break;
			case 将:
				JRuleExample jRuleExample = new JRuleExample();
				cn.edu.cqu.kb.model.JRuleExample.Criteria jCriteria = jRuleExample.createCriteria();
				jCriteria.andChessidEqualTo(chessid);
				final List<JRule> jRules = Mappers.getJRuleMapper().selectByExample(jRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(jRules);

				break;
			case 炮:
				PRuleExample pRuleExample = new PRuleExample();
				cn.edu.cqu.kb.model.PRuleExample.Criteria pCriteria = pRuleExample.createCriteria();
				pCriteria.andChessidEqualTo(chessid);
				final List<PRule> pRules = Mappers.getPRuleMapper().selectByExample(pRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(pRules);
				break;
			case 相:
				XRuleExample xRuleExample = new XRuleExample();
				cn.edu.cqu.kb.model.XRuleExample.Criteria xCriteria = xRuleExample.createCriteria();
				xCriteria.andChessidEqualTo(chessid);
				final List<XRule> xRules = Mappers.getXRuleMapper().selectByExample(xRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(xRules);
				break;
			case 车:
				CRuleExample cRuleExample = new CRuleExample();
				cn.edu.cqu.kb.model.CRuleExample.Criteria cCriteria = cRuleExample.createCriteria();
				cCriteria.andChessidEqualTo(chessid);
				final List<CRule> cRules = Mappers.getCRuleMapper().selectByExample(cRuleExample);
				rules = new ArrayList<Rule>();
				rules.addAll(cRules);
				break;
			}

			for (Rule rule : rules)
				rule.setType(getChessType());
		}
		return rules;
	}
}