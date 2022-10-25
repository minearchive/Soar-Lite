package soar.mixin.mixins.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import soar.Soar;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer implements IMixinEntityRenderer {
    @Shadow
    private float smoothCamFilterX;

    @Shadow
    private float smoothCamFilterY;

    @Shadow private float smoothCamYaw;

    @Shadow private float smoothCamPitch;

    @Shadow private float smoothCamPartialTicks;

    @Override
    public float getSmoothCamFilterX() {
        return smoothCamFilterX;
    }

    @Override
    public float getSmoothCamFilterY() {
        return smoothCamFilterY;
    }

    @Override
    public void setSmoothCamYaw(float f) {
        this.smoothCamYaw = f;
    }

    @Override
    public void setSmoothCamPitch(float f) {
        this.smoothCamPitch = f;
    }

    @Override
    public float getSmoothCamYaw() {
        return smoothCamYaw;
    }

    @Override
    public float getSmoothCamPitch() {
        return smoothCamPitch;
    }

    @Override
    public void setSmoothCamPartialTicks(float f) {
        smoothCamPartialTicks = f;
    }

    @Override
    public float getSmoothCamPartialTicks() {
        return smoothCamPartialTicks;
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationYaw:F"))
    public float redirectRotYaw(Entity instance) {
        return Soar.INSTANCE.modManager.perspectiveMod.getCameraYaw();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationPitch:F"))
    public float redirectRotPitch(Entity instance) {
        return Soar.INSTANCE.modManager.perspectiveMod.getCameraPitch();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F"))
    public float redirectPrevRotPitch(Entity instance) {
        return Soar.INSTANCE.modManager.perspectiveMod.getCameraPitch();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F"))
    public float redirectPrevRotYaw(Entity instance) {
        return Soar.INSTANCE.modManager.perspectiveMod.getCameraYaw();
    }

    @Redirect(method = "updateCameraAndRender",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;inGameHasFocus:Z"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;setGrabbed(Z)V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/util/MouseHelper;mouseXYChange()V")
            )
    )
    public boolean redirectInGameHasFocus(Minecraft instance) {
        if(Soar.INSTANCE.modManager.perspectiveMod.perspectiveToggled) {
            Soar.INSTANCE.modManager.perspectiveMod.handleCamera();
        }
        return instance.inGameHasFocus && !Soar.INSTANCE.modManager.perspectiveMod.perspectiveToggled;
    }
}
