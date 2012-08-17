package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.entityapi.EntityAPI;
import net.minecraft.server.EntitySquid;
import net.minecraft.server.World;

public class APIEntitySquid extends EntitySquid implements APIEntity {
	
	private EntityAPI plugin;
	
	public APIEntitySquid(World world){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("EntityAPI");
		
		if (plugin == null){
			world.removeEntity(this);
		}
		
		this.plugin = (EntityAPI) plugin;
	}
	
}
