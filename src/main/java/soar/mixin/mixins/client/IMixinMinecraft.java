package soar.mixin.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;

public interface IMixinMinecraft {

    static IMixinMinecraft getInstance() {
        return (IMixinMinecraft) Minecraft.getMinecraft();
    }

    void setSession(String username, String id, String token, String type);

    float getPartialTicks();
}
