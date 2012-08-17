package uk.co.jacekk.bukkit.entityapi;

import java.lang.reflect.Constructor;

import net.minecraft.server.EntityLiving;
import net.minecraft.server.World;

import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.world.WorldInitEvent;

import uk.co.jacekk.bukkit.baseplugin.BaseListener;
import uk.co.jacekk.bukkit.entityapi.entity.APIEntityType;

public class EntityReplaceListener extends BaseListener<EntityAPI> {
	
	public EntityReplaceListener(EntityAPI plugin){
		super(plugin);
	}
	
	private void replaceEntity(Entity bukkkitEntity){
		APIEntityType entityType = APIEntityType.getByEntityType(bukkkitEntity.getType());
		
		if (entityType == null){
			return;
		}
		
		World world = (World) ((CraftWorld) bukkkitEntity.getWorld()).getHandle();
		EntityLiving entity = (EntityLiving) ((CraftEntity) bukkkitEntity).getHandle();
		
		if (entity.dead || !APIEntityType.isCustomEntity(entity.getClass())){
			return;
		}
		
		try{
			Constructor<?> constructor = entityType.getEntityClass().getConstructor(World.class);
			EntityLiving newEntity = (EntityLiving) constructor.newInstance(world);
			
			newEntity.setPositionRotation(entity.locX, entity.locY, entity.locZ, entity.pitch, entity.yaw);
			
			if (entity.passenger != null){
				entity.passenger.mount(newEntity);
			}
			
			world.removeEntity(entity);
			world.addEntity(newEntity, SpawnReason.CUSTOM);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if (event.getSpawnReason() != SpawnReason.CUSTOM){
			this.replaceEntity(event.getEntity());
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onWorldInit(WorldInitEvent event){
		for (Entity entity : event.getWorld().getEntities()){
			this.replaceEntity(entity);
		}
	}
	
}
