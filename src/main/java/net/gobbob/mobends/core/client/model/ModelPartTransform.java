package net.gobbob.mobends.core.client.model;

import net.gobbob.mobends.core.util.GLHelper;
import net.gobbob.mobends.core.util.IVec3f;
import net.gobbob.mobends.core.util.SmoothOrientation;
import net.gobbob.mobends.core.util.Vec3f;
import net.minecraft.client.renderer.GlStateManager;

/*
 * Used for manipulating the transform of things that are
 * going to postRender this part.
 */
public class ModelPartTransform implements IModelPart
{
	public Vec3f position;
	public Vec3f scale;
	public SmoothOrientation rotation;
	
	public ModelPartTransform()
	{
		this.position = new Vec3f();
		this.scale = new Vec3f(1, 1, 1);
		this.rotation = new SmoothOrientation();
	}
	
	@Override
	public void applyStandaloneTransform(float scale)
	{
		this.applyOwnTransform(scale);
	}

	@Override
	public void renderPart(float scale)
	{
		//Since this is just a transform, do nothing.
	}
	
	@Override
	public void renderJustPart(float scale)
	{
	}

	@Override
	public void update(float ticksPerFrame)
	{
		this.rotation.update(ticksPerFrame);
	}

	@Override
	public IVec3f getPosition() { return this.position; }
	@Override
	public IVec3f getScale() { return this.scale; }
	@Override
	public SmoothOrientation getRotation() { return this.rotation; }

	@Override
	public void syncUp(IModelPart part)
	{
		if(part == null)
			return;
		this.position.set(part.getPosition());
		this.rotation.set(part.getRotation());
		this.scale.set(part.getScale());
	}

	@Override
	public boolean isShowing()
	{
		return true;
	}

	@Override
	public void applyOwnTransform(float scale)
	{
		if (this.position.x != 0.0F || this.position.y != 0.0F || this.position.z != 0.0F)
        	GlStateManager.translate(this.position.x * scale, this.position.y * scale, this.position.z * scale);
        
		GLHelper.rotate(this.rotation.getSmooth());
        
        if(this.scale.x != 0.0F || this.scale.y != 0.0F || this.scale.z != 0.0F)
        	GlStateManager.scale(this.scale.x, this.scale.y, this.scale.z);
	}

	@Override
	public void propagateTransform(float scale)
	{
		this.applyOwnTransform(scale);
	}

	@Override
	public void applyPostTransform(float scale)
	{
	}

	@Override
	public void setVisible(boolean showModel)
	{
		// Do nothing
	}
}
