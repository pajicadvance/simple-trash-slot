package me.pajic.simple_trash_slot;

public enum SlotPosition {
	V1(76, 43),
	V2(76, 25),
	V3(76, 7),
	H1(133, 61),
	H2(151, 61);

	public final int x;
	public final int y;

	SlotPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
