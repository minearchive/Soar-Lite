package soar.management.mod;

import soar.utils.animation.SimpleAnimation;

public enum ModCategory {
	HUD("Hud"), PERFORMANCE("Performance"), PLAYER("Player"), RENDER("Render"), OTHER("Other");
	private final String name;
	SimpleAnimation scrollAnimation = new SimpleAnimation(0.0F);
	float scrollY = 0;

	ModCategory(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

