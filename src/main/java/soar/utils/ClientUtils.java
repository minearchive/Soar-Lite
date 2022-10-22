package soar.utils;

import net.minecraft.server.MinecraftServer;
import soar.Soar;
import soar.management.mod.render.FullbrightMod;

public class ClientUtils {
	
    public static boolean isFullbright() {
        MinecraftServer server = MinecraftServer.getServer();

        if (server != null && server.isCallingFromMinecraftThread()) {
            return false;
        }

        return Soar.INSTANCE.modManager.getModByClass(FullbrightMod.class).isToggled();
    }
}
