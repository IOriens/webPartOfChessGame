package cn.edu.cqu.kb.model;

public class CRule extends Rule {

	public CRule() {
		super(ChessmanType.³µ);
	}

	private Integer existblackX;

	private Integer existblackY;

	private Integer existredX;

	private Integer existredY;

	private Integer notexistinxX1;

	private Integer notexistinxX2;

	private Integer notexistinyY1;

	private Integer notexistinyY2;

	public Integer getExistblackX() {
		return existblackX;
	}

	public void setExistblackX(Integer existblackX) {
		this.existblackX = existblackX;
	}

	public Integer getExistblackY() {
		return existblackY;
	}

	public void setExistblackY(Integer existblackY) {
		this.existblackY = existblackY;
	}

	public Integer getExistredX() {
		return existredX;
	}

	public void setExistredX(Integer existredX) {
		this.existredX = existredX;
	}

	public Integer getExistredY() {
		return existredY;
	}

	public void setExistredY(Integer existredY) {
		this.existredY = existredY;
	}

	public Integer getNotexistinxX1() {
		return notexistinxX1;
	}

	public void setNotexistinxX1(Integer notexistinxX1) {
		this.notexistinxX1 = notexistinxX1;
	}

	public Integer getNotexistinxX2() {
		return notexistinxX2;
	}

	public void setNotexistinxX2(Integer notexistinxX2) {
		this.notexistinxX2 = notexistinxX2;
	}

	public Integer getNotexistinyY1() {
		return notexistinyY1;
	}

	public void setNotexistinyY1(Integer notexistinyY1) {
		this.notexistinyY1 = notexistinyY1;
	}

	public Integer getNotexistinyY2() {
		return notexistinyY2;
	}

	public void setNotexistinyY2(Integer notexistinyY2) {
		this.notexistinyY2 = notexistinyY2;
	}

}