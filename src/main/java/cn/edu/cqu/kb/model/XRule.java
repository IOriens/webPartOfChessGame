package cn.edu.cqu.kb.model;

public class XRule extends Rule {
	private static final long serialVersionUID = 3370891473754603728L;

	public XRule() {
		super(ChessmanType.Па);
	}

	private Integer notexistX;

	private Integer notexistY;

	private Integer notexistblackX;

	private Integer notexistblackY;

	private Integer notexistredX;

	private Integer notexistredY;

	public Integer getNotexistX() {
		return notexistX;
	}

	public void setNotexistX(Integer notexistX) {
		this.notexistX = notexistX;
	}

	public Integer getNotexistY() {
		return notexistY;
	}

	public void setNotexistY(Integer notexistY) {
		this.notexistY = notexistY;
	}

	public Integer getNotexistblackX() {
		return notexistblackX;
	}

	public void setNotexistblackX(Integer notexistblackX) {
		this.notexistblackX = notexistblackX;
	}

	public Integer getNotexistblackY() {
		return notexistblackY;
	}

	public void setNotexistblackY(Integer notexistblackY) {
		this.notexistblackY = notexistblackY;
	}

	public Integer getNotexistredX() {
		return notexistredX;
	}

	public void setNotexistredX(Integer notexistredX) {
		this.notexistredX = notexistredX;
	}

	public Integer getNotexistredY() {
		return notexistredY;
	}

	public void setNotexistredY(Integer notexistredY) {
		this.notexistredY = notexistredY;
	}

}