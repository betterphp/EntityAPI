package uk.co.jacekk.bukkit.entityapi.entity;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import uk.co.jacekk.bukkit.entityapi.ai.APINavigation;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityMushroomCow;
import net.minecraft.server.World;

public class APIEntityMushroomCow extends EntityMushroomCow implements APIEntity {
	
	private EntityAPI plugin;
	
	private SpawnReason spawnReason;
	private double speedMultiplier;
	
	public APIEntityMushroomCow(World world, SpawnReason spawnReason){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
		this.spawnReason = spawnReason;
		this.speedMultiplier = 1.0d;
		
		try{
			Field navigation = EntityLiving.class.getDeclaredField("navigation");
			navigation.setAccessible(true);
			navigation.set(this, new APINavigation(this, this.world, 16.0f));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public APIEntityMushroomCow(World world){
		this(world, SpawnReason.DEFAULT);
	}
	
	public SpawnReason getSpawnReason(){
		return this.spawnReason;
	}
	
	public void setMovementSpeedMultiplier(double multiplier) throws IllegalArgumentException {
		if (multiplier < 0.0d || multiplier > 8.0d){
			throw new IllegalArgumentException("The multiplier must be between 0 and 8");
		}
		
		this.speedMultiplier = multiplier;
	}
	
	public double getMovementSpeedMultiplier(){
		return this.speedMultiplier;
	}
	
}
