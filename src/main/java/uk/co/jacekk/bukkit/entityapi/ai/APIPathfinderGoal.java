package uk.co.jacekk.bukkit.entityapi.ai;

import net.minecraft.server.PathfinderGoal;

public abstract class APIPathfinderGoal extends PathfinderGoal {
	
	@Override
	public boolean a(){
		return this.shouldExecute();
	}
	
	@Override
	public boolean b(){
		return this.shouldContinue();
	}
	
	@Override
	public boolean i(){
		return this.isContinuous();
	}
	
	@Override
	public void e(){
		this.start();
	}
	
	@Override
	public void c(){
		this.reset();
	}
	
	@Override
	public void d(){
		this.update();
	}
	
	public abstract boolean isContinuous();
	
	public abstract boolean shouldExecute();
	
	public abstract boolean shouldContinue();
	
	public abstract void start();
	
	public abstract void reset();
	
	public abstract void update();
	
}
