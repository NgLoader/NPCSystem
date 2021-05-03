package de.ngloader.ncpsystem.wrapper;

public enum EntityAnimation {

	SWING_MAIN_ARM(0),
	TAKE_DAMAGE(1),
	LEAVE_BED(2),
	SIWNG_OFFHAND(3),
	CRITICAL_EFFECT(4),
	MAGIC_CRITICAL_EFFECT(5);

	private final int animation;

	private EntityAnimation(int animation) {
		this.animation = animation;
	}

	public int getAnimation() {
		return this.animation;
	}
}