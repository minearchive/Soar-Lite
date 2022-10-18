package soar.utils;

public class ServerUtils implements Utils{

	public static String getServerIP() {
		
		String ip;
		
		if(mc.getCurrentServerData().serverIP != null) {
			ip = mc.getCurrentServerData().serverIP;
		}else {
			ip = "SinglePlayer";
		}
		
		return ip;
	}
	
	public static boolean isHypixel() {
		if(mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP.contains("hypixel")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int getPing() {
		if(mc.isSingleplayer()) {
			return 0;
		}else {
			return (int) mc.getCurrentServerData().pingToServer;
		}
	}
}
