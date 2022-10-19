package soar.utils.mouse;

import org.lwjgl.input.Mouse;

public final class MouseUtils {

	public enum ScrollDirection {
		UP, DOWN;
	}

	public static ScrollDirection scroll() {
		int mouse = Mouse.getDWheel();

		if (mouse > 0) {
			return ScrollDirection.UP;
		} else if (mouse < 0) {
			return ScrollDirection.DOWN;
		} else {
			return null;
		}
	}

	public static boolean isInside(int mouseX, int mouseY, double x, double y, double width, double height) {
		return (mouseX >= x && mouseX <= (x + width)) && (mouseY >= y && mouseY <= (y + height));
	}
}