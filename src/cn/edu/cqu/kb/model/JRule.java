package cn.edu.cqu.kb.model;

public class JRule extends Rule {
	public JRule() {
		super(ChessmanType.½«);
	}

	private Integer inblackhomeX;

	private Integer inblackhomeY;

	private Integer inredhomeX;

	private Integer inredhomeY;

	private Integer notexistblackX;

	private Integer notexistblackY;

	private Integer notexistredX;

	private Integer notexistredY;

	private Integer notfacetofaceX;

	private Integer notfacetofaceY;

	private Boolean facetoface;

	private Boolean eatanother;

	public Integer getInblackhomeX() {
		return inblackhomeX;
	}

	public void setInblackhomeX(Integer inblackhomeX) {
		this.inblackhomeX = inblackhomeX;
	}

	public Integer getInblackhomeY() {
		return inblackhomeY;
	}

	public void setInblackhomeY(Integer inblackhomeY) {
		this.inblackhomeY = inblackhomeY;
	}

	public Integer getInredhomeX() {
		return inredhomeX;
	}

	public void setInredhomeX(Integer inredhomeX) {
		this.inredhomeX = inredhomeX;
	}

	public Integer getInredhomeY() {
		return inredhomeY;
	}

	public void setInredhomeY(Integer inredhomeY) {
		this.inredhomeY = inredhomeY;
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

	public Integer getNotfacetofaceX() {
		return notfacetofaceX;
	}

	public void setNotfacetofaceX(Integer notfacetofaceX) {
		this.notfacetofaceX = notfacetofaceX;
	}

	public Integer getNotfacetofaceY() {
		return notfacetofaceY;
	}

	public void setNotfacetofaceY(Integer notfacetofaceY) {
		this.notfacetofaceY = notfacetofaceY;
	}

	public Boolean getFacetoface() {
		return facetoface;
	}

	public void setFacetoface(Boolean facetoface) {
		this.facetoface = facetoface;
	}

	public Boolean getEatanother() {
		return eatanother;
	}

	public void setEatanother(Boolean eatanother) {
		this.eatanother = eatanother;
	}
}