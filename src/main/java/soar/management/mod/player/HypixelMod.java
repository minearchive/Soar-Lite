package soar.management.mod.player;

import net.minecraft.network.play.server.S02PacketChat;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventReceivePacket;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;
import soar.management.setting.Setting;
import soar.utils.ServerUtils;

public final class HypixelMod extends Mod {

	public HypixelMod() {
		super("Hypixel", ModCategory.PLAYER);
	}

	@Override
	public void setup() {
		Setting autoGG = this.addBooleanSetting("Auto GG", this, true);

		autoGG.setCategory("Auto Mods");
	}

    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
    	if(ServerUtils.isHypixel()) {
            if (event.getPacket() instanceof S02PacketChat) {
                S02PacketChat chatPacket = (S02PacketChat) event.getPacket();
                String chatMessage = chatPacket.getChatComponent().getUnformattedText();
                if(chatMessage.contains("WINNER!") ||  chatMessage.contains("1st Killer -") || chatMessage.contains("Top Survivors")) {
                	mc.thePlayer.sendChatMessage("/achat gg");
                }
            }
    	}
    }
}
