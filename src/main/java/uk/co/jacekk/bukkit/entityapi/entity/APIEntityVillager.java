package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.World;

public class APIEntityVillager extends EntityVillager implements APIEntity {
	
	private EntityAPI plugin;
	
	public APIEntityVillager(World world, int profession){
		super(world, profession);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
	}
	
	public APIEntityVillager(World world){
		this(world, 0);
	}
	
}
