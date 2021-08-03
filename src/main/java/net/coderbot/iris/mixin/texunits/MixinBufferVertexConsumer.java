package net.coderbot.iris.mixin.texunits;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.coderbot.iris.texunits.TextureUnit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Mixin(BufferVertexConsumer.class)
@Environment(EnvType.CLIENT)
public interface MixinBufferVertexConsumer {
	// Note: the below code doesn't work due to https://github.com/FabricMC/Mixin/issues/34
	/*@ModifyConstant(method = "overlay(II)Lnet/minecraft/client/render/VertexConsumer;", constant = @Constant(intValue = 1), require = 1)
	default int iris$fixOverlayIndex(int index) {
		return TextureUnit.OVERLAY.getSamplerId();
	}

	@ModifyConstant(method = "light(II)Lnet/minecraft/client/render/VertexConsumer;", constant = @Constant(intValue = 2), require = 1)
	default int iris$fixLightIndex(int index) {
		return TextureUnit.LIGHTMAP.getSamplerId();
	}*/

	/**
	 * @reason FabricMC Mixin does not support injections into interfaces
	 * @author coderbot16
	 */
	@Overwrite
	default VertexConsumer overlayCoords(int u, int v) {
		return ((BufferVertexConsumer) this).uvShort((short) u, (short) v, TextureUnit.OVERLAY.getSamplerId());
	}

	/**
	 * @reason FabricMC Mixin does not support injections into interfaces
	 * @author coderbot16
	 */
	@Overwrite
	default VertexConsumer uv2(int u, int v) {
		return ((BufferVertexConsumer) this).uvShort((short) u, (short) v, TextureUnit.LIGHTMAP.getSamplerId());
	}
}
