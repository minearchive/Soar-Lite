package soar.mixin.mixins.client;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ClientBrandRetriever.class)
public class MixinClientBrandRetriever {
	@ModifyConstant(method = "getClientModName", constant = @Constant(stringValue = "vanilla"))
	// Using some plugins you can see players client brand
	// And if there will be crash it will show that the client is modified in crash report
	// And it will show in f3 menu
	// Kinda useless
	private static String changeClientModName(String constant) {
		return "soar-lite";
	}
}
