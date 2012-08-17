package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;

import net.minecraft.server.EntityBlaze;
import net.minecraft.server.World;

public class APIEntityBlaze extends EntityBlaze implements APIEntity {
	
	private EntityAPI plugin;
	
	public APIEntityBlaze(World world){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
	}
	
}
