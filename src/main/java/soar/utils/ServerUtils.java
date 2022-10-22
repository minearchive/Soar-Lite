package soar.utils;

public final class ServerUtils implements Utils {

	public static String getServerIP() {
		String ip;

		if (MC.getCurrentServerData() != null) {
			ip = MC.getCurrentServerData().serverIP;
		} else {
			ip = "SinglePlayer";
		}

		return ip;
	}

	public static boolean isHypixel() {
		return MC.getCurrentServerData() != null && MC.getCurrentServerData().serverIP.contains("hypixel");
	}

	public static int getPing() {
		if (MC.isSingleplayer()) {
			return 0;
		} else {
			return (int) MC.getCurrentServerData().pingToServer;
		}
	}
}
