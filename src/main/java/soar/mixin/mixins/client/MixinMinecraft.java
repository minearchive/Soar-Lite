package soar.mixin.mixins.client;

import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import soar.Soar;
import soar.management.event.impl.EventKey;
import soar.management.event.impl.EventTick;

@Mixin(Minecraft.class)
public class MixinMinecraft implements IMixinMinecraft {

    @Mutable
    @Shadow
    @Final
    private Session session;

    @Shadow
    private String serverName;

    @Shadow private Timer timer;

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void startClient(CallbackInfo ci) {
        Soar.INSTANCE.startClient();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void stopClient(CallbackInfo ci) {
        Soar.INSTANCE.stopClient();
    }

    @Inject(method = "runTick", at = @At("TAIL"))
    private void onTick(final CallbackInfo ci) {
        EventTick event = new EventTick();
        event.call();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void onKey(CallbackInfo ci) {
        if (Keyboard.getEventKeyState() && Minecraft.getMinecraft().currentScreen == null) {
            EventKey event = new EventKey(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey());
            event.call();
        }
    }

    @Override
    public void setSession(String username, String id, String token, String type) {
        this.session = new Session(username,id,token,type);
    }

    @Override
    public float getPartialTicks() {
        return timer.renderPartialTicks;
    }

}
