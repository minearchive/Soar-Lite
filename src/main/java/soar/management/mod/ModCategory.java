package soar.management.mod;

import soar.utils.animation.SimpleAnimation;

public enum ModCategory {
	HUD, PERFORMANCE, PLAYER, RENDER, OTHER;
	SimpleAnimation scrollAnimation = new SimpleAnimation(0.0F);
	float scrollY = 0;
}
