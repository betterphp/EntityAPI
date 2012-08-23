package uk.co.jacekk.bukkit.entityapi.entity;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import uk.co.jacekk.bukkit.entityapi.ai.APINavigation;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.Navigation;
import net.minecraft.server.World;

public class APIEntityVillager extends EntityVillager implements APIEntity {
	
	private EntityAPI plugin;
	
	private SpawnReason spawnReason;
	private double speedMultiplier;
	
	public APIEntityVillager(World world, int profession, SpawnReason spawnReason){
		super(world, profession);
		
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
			
			this.getNavigation().a(true);
			this.getNavigation().b(true);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public APIEntityVillager(World world, SpawnReason spawnReason){
		this(world, 0, spawnReason);
	}
	
	public APIEntityVillager(World world){
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
	
	public void setAvoidsWater(boolean avoidWater){
		this.getNavigation().a(avoidWater);
	}

	public boolean getAvoidsWater(){
		return this.getNavigation().a();
	}
	
	public void setBreakDoors(boolean breakDoors){
		this.getNavigation().b(breakDoors);
	}
	
	public boolean getBreakDoors(){
		return this.getNavigation().c();
	}
	
	public void setEnterDoors(boolean enterDoors){
		this.getNavigation().c(enterDoors);
	}
	
	public boolean getEnterDoors(){
		try{
			Field j = Navigation.class.getDeclaredField("j");
			j.setAccessible(true);
			
			return (Boolean) j.get(this.getNavigation());
		}catch (Exception e){
			e.printStackTrace();
			return true;
		}
	}
	
	public void setAvoidSun(boolean avoidSun){
		this.getNavigation().d(avoidSun);
	}
	
	public boolean getAvoidSun(){
		try{
			Field f = Navigation.class.getDeclaredField("f");
			f.setAccessible(true);
			
			return (Boolean) f.get(this.getNavigation());
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void setCanSwim(boolean canSwim){
		this.getNavigation().e(canSwim);
	}
	
	public boolean getCanSwim(){
		try{
			Field m = Navigation.class.getDeclaredField("m");
			m.setAccessible(true);
			
			return (Boolean) m.get(this.getNavigation());
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
