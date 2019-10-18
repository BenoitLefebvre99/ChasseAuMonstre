package utils;

public enum TypeCaseUnit {
	SHOT("shot.png"),
	WALKED("walked.png"),
	MONSTRE("monstre.png"),
	TARGET("target.png"),
	NORMAL("normal.png"),
	UP("up.png"),
	DOWN("down.png"),
	RIGHT("right.png"),
	LEFT("left.png"),
	UP_LEFT("up_left.png"),
	UP_RIGHT("up_right.png"),
	DOWN_LEFT("down_left.png"),
	DOWN_RIGHT("down_right.png"),
	BUISSON("buisson.png");
	
	private final String FILENAME;
	
	TypeCaseUnit(String tmp) {this.FILENAME=tmp;}
	public String GetFILENAME() {return this.FILENAME;}
}
