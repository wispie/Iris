package net.coderbot.iris.mixin;

import net.minecraft.client.gl.GLImportProcessor;
import net.minecraft.client.gl.Program;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(Program.class)
public class MixinGlShader {
	@Redirect(method = "loadProgram", at = @At(value = "INVOKE", target = "net/minecraft/client/gl/GLImportProcessor.readSource (Ljava/lang/String;)Ljava/util/List;"))
	private static List<String> iris$allowSkippingMojImportDirectives(GLImportProcessor includeHandler, String shaderSource) {
		// Mojang's code for handling #moj_import directives uses regexes that can cause StackOverflowErrors.
		//
		// Rather than fix the crash, we just don't try to process directives if they don't exist, which is fine
		// for Iris since we don't allow using moj_import.
		if (!shaderSource.contains("moj_import")) {
			return Collections.singletonList(shaderSource);
		}

		return includeHandler.readSource(shaderSource);
	}
}
