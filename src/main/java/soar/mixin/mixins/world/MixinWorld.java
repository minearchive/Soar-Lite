package soar.mixin.mixins.world;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import soar.utils.ClientUtils;

@Mixin(World.class)
public class MixinWorld {

	/*
	 * From patcher mod
	 * @ https://github.com/Sk1erLLC/Patcher
	 */
    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void checkLightFor(CallbackInfoReturnable<Boolean> cir) {
        if (this.canFullbright()) {
            cir.setReturnValue(true);
        }
    }

	/*
	 * From patcher mod
	 * @ https://github.com/Sk1erLLC/Patcher
	 */
    @Inject(method = {"getLightFromNeighborsFor", "getLightFromNeighbors", "getRawLight", "getLight(Lnet/minecraft/util/BlockPos;)I", "getLight(Lnet/minecraft/util/BlockPos;Z)I" }, at = @At("HEAD"), cancellable = true)
    private void getLightFromNeighborsFor(CallbackInfoReturnable<Integer> cir) {
        if (this.canFullbright()) {
            cir.setReturnValue(15);
        }
    }
    
	/*
	 * From patcher mod
	 * @ https://github.com/Sk1erLLC/Patcher
	 */
    @Unique
    private boolean canFullbright() {
        return Minecraft.getMinecraft().isCallingFromMinecraftThread() && ClientUtils.isFullbright();
    }
}
