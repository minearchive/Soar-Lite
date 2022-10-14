package soar.mixin.mixins.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.GuiIngame;
import soar.management.event.impl.EventRender2D;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

	@Inject(method = "renderGameOverlay", at = @At("TAIL"))
	public void onRender2D(float partialTicks, CallbackInfo ci) {
		EventRender2D event = new EventRender2D(partialTicks);
		event.call();
	}
}
