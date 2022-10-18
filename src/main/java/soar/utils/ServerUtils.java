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
	
	public static int getPing() {
		if(mc.isSingleplayer()) {
			return 0;
		}else {
			return (int) mc.getCurrentServerData().pingToServer;
		}
	}
}
