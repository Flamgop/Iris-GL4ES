package net.coderbot.iris.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LevelRenderer.class)
public interface WorldRendererAccessor {
	@Accessor("entityRenderDispatcher")
	EntityRenderDispatcher getEntityRenderDispatcher();

	@Invoker("renderChunkLayer")
	void invokeRenderChunkLayer(RenderType terrainLayer, PoseStack modelView, double cameraX, double cameraY, double cameraZ);

	@Invoker("setupRender")
	void invokeSetupTerrain(Camera camera, Frustum frustum, boolean hasForcedFrustum, int frame, boolean spectator);

	@Invoker("renderEntity")
	void invokeRenderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers);

	@Accessor("level")
	ClientLevel getWorld();

	@Accessor("frameId")
	int getFrame();

	@Accessor("frameId")
	void setFrame(int frame);
}
