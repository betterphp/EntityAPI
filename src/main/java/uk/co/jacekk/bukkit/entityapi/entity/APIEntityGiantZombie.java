package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import net.minecraft.server.EntityGiantZombie;
import net.minecraft.server.World;

public class APIEntityGiantZombie extends EntityGiantZombie implements APIEntity {
	
	private EntityAPI plugin;
	
	public APIEntityGiantZombie(World world){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
	}
	
}
