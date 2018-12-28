package net.gobbob.mobends.core.animation.bit;

import net.gobbob.mobends.core.EntityData;
import net.gobbob.mobends.core.animation.layer.AnimationLayer;

public abstract class AnimationBit<T extends EntityData<?>>
{
	/*
	 * The layer that this bit is performed by.
	 * Used to callback, e.g. when the animation is finished.
	 * */
	protected AnimationLayer<? extends T> layer;
	
	/*
	 * Called by the AnimationLayer before it plays
	 * this bit.
	 */
	public void setupForPlay(AnimationLayer<? extends T> layer, T entityData)
	{
		this.layer = layer;
		this.onPlay(entityData);
	}
	
	/*
	 * Returns the actions currently being performed
	 * by the entityData. Used by BendsPacks
	 */
	public abstract String[] getActions(T entityData);
	
	/*
	 * Called by setupForPlay to setup the beginning of
	 * this animation bit.
	 */
	public void onPlay(T entityData) {}
	
	/*
	 * Called by an AnimationLayer to perform a continuous
	 * animation.
	 */
	public abstract void perform(T entityData);
}