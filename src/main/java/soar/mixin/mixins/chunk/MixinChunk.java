package soar.mixin.mixins.chunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.chunk.Chunk;
import soar.utils.ClientUtils;

@Mixin(Chunk.class)
public class MixinChunk {

	/*
	 * From patcher mod
	 * @ https://github.com/Sk1erLLC/Patcher
	 */
    @Inject(method = {"getLightFor", "getLightSubtracted"}, at = @At("HEAD"), cancellable = true)
    private void patchFullbright(CallbackInfoReturnable<Integer> cir) {
        if (ClientUtils.isFullbright()) {
            cir.setReturnValue(15);
        }
    }
}
