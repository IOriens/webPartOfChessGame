package cn.edu.cqu.kb.model;

public class Rule {

	private Integer id;

	private Integer chessid;

	private ChessmanType type;

	private Integer movetoX;

	private Integer movetoY;

	public Rule(ChessmanType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChessid() {
		return chessid;
	}

	public void setChessid(Integer chessid) {
		this.chessid = chessid;
	}

	public ChessmanType getType() {
		return type;
	}

	public void setType(ChessmanType type) {
		this.type = type;
	}

	public Integer getMovetoX() {
		return movetoX;
	}

	public void setMovetoX(Integer movetoX) {
		this.movetoX = movetoX;
	}

	public Integer getMovetoY() {
		return movetoY;
	}

	public void setMovetoY(Integer movetoY) {
		this.movetoY = movetoY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chessid == null) ? 0 : chessid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movetoX == null) ? 0 : movetoX.hashCode());
		result = prime * result + ((movetoY == null) ? 0 : movetoY.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (chessid == null) {
			if (other.chessid != null)
				return false;
		} else if (!chessid.equals(other.chessid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movetoX == null) {
			if (other.movetoX != null)
				return false;
		} else if (!movetoX.equals(other.movetoX))
			return false;
		if (movetoY == null) {
			if (other.movetoY != null)
				return false;
		} else if (!movetoY.equals(other.movetoY))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
