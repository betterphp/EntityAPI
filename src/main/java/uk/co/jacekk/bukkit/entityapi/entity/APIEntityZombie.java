package uk.co.jacekk.bukkit.entityapi.entity;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import uk.co.jacekk.bukkit.entityapi.ai.APIPathfinderGoal;
import net.minecraft.server.EntityZombie;
import net.minecraft.server.PathfinderGoal;
import net.minecraft.server.World;

public class APIEntityZombie extends EntityZombie implements APIEntity {
	
	private EntityAPI plugin;
	
	private List<PathfinderGoal> goalSelectors;
	private List<PathfinderGoal> targetSelectors;
	
	public APIEntityZombie(World world){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
		
		try{
			Field goala = this.goalSelector.getClass().getDeclaredField("a");
			goala.setAccessible(true);
			this.goalSelectors = (List<PathfinderGoal>) goala.get(this.goalSelector);
			
			Field targeta = this.targetSelector.getClass().getDeclaredField("a");
			targeta.setAccessible(true);
			this.targetSelectors = (List<PathfinderGoal>) targeta.get(this.targetSelector);
		}catch (Exception e){
			e.printStackTrace();
			this.world.removeEntity(this);
		}
	}
	
	public void addGoalSelector(APIPathfinderGoal selector){
		this.goalSelector.a(this.goalSelectors.size(), selector);
	}
	
	public void addTargetSelector(APIPathfinderGoal selector){
		this.goalSelector.a(this.goalSelectors.size(), selector);
	}
	
}
