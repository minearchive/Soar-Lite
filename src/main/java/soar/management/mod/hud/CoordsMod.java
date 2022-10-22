package soar.management.mod.hud;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import soar.Soar;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventRender2D;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;
import soar.management.setting.Setting;
import soar.utils.RenderUtils;

public class CoordsMod extends Mod{

	public CoordsMod() {
		super("Coords", ModCategory.HUD);
	}
	
	@Override
	public void setup() {
		
		Setting backgroundSetting = this.addBooleanSetting("Background", this, true);
		Setting shadowSetting = this.addBooleanSetting("Font Shadow", this, true);
		
		backgroundSetting.setCategory("Render");
		shadowSetting.setCategory("Render");
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
		
		String biome = "";
		Chunk chunk = mc.theWorld.getChunkFromBlockCoords(new BlockPos(mc.thePlayer));
		
		boolean shadow = Soar.INSTANCE.settingsManager.getSettingByName(this, "Font Shadow").getValBoolean();
		boolean background = Soar.INSTANCE.settingsManager.getSettingByName(this, "Background").getValBoolean();
		int maxWidth = 100;
		
		biome = chunk.getBiome(new BlockPos(mc.thePlayer), mc.theWorld.getWorldChunkManager()).biomeName;
		
		if(maxWidth < fr.getStringWidth("Biome: " + biome)) {
			maxWidth = fr.getStringWidth("Biome: " + biome) + 5;
		}else {
			maxWidth = 100;
		}
		
		if(background) {
			RenderUtils.drawRect(this.getX(), this.getY(), maxWidth + 4.5F, 46.5F, this.getBackgroundColor());
		}
		
		fr.drawString("X: " + (int) (mc.thePlayer.posX), this.getX() + 4.5F, this.getY() + 4.5F, this.getFontColor(), shadow);
		fr.drawString("Y: " + (int) (mc.thePlayer.posY), this.getX() + 4.5F, this.getY() + 14.5F, this.getFontColor(), shadow);
		fr.drawString("Z: " + (int) (mc.thePlayer.posZ), this.getX() + 4.5F, this.getY() + 24.5F, this.getFontColor(), shadow);
		fr.drawString("Biome: " + biome, this.getX() + 4.5F, this.getY() + 34.5F, this.getFontColor(), shadow);
		
		this.setWidth(maxWidth + 5);
		this.setHeight(47);
	}
}
