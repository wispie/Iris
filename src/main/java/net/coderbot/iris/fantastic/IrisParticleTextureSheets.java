package net.coderbot.iris.fantastic;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;

public class IrisParticleTextureSheets {
	public static final ParticleTextureSheet OPAQUE_TERRAIN_SHEET = new ParticleTextureSheet() {
		public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
			// Cutout is handled by the particle shader for us.
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.setShaderTexture(0, SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
			bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
		}

		public void draw(Tessellator tessellator) {
			tessellator.draw();
		}

		public String toString() {
			return "OPAQUE_TERRAIN_SHEET";
		}
	};
}
