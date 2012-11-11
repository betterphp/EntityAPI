package uk.co.jacekk.bukkit.entityapi;

import java.lang.reflect.Constructor;

import net.minecraft.server.EntityLiving;
import net.minecraft.server.World;

import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import uk.co.jacekk.bukkit.baseplugin.v5.event.BaseListener;

public class EntityReplaceListener extends BaseListener<EntityAPI> {
	
	public EntityReplaceListener(EntityAPI plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		LivingEntity bukkkitEntity = event.getEntity();
		SpawnReason spawnReason = event.getSpawnReason();
		
		APIEntityType entityType = APIEntityType.getByEntityType(bukkkitEntity.getType());
		
		if (entityType == null){
			return;
		}
		
		EntityLiving entity = (EntityLiving) ((CraftEntity) bukkkitEntity).getHandle();
		
		if (entity.dead || !APIEntityType.isCustomEntity(entity.getClass())){
			return;
		}
		
		World world = (World) ((CraftWorld) bukkkitEntity.getWorld()).getHandle();
		
		try{
			Constructor<?> constructor = entityType.getEntityClass().getConstructor(World.class, SpawnReason.class);
			EntityLiving newEntity = (EntityLiving) constructor.newInstance(world, spawnReason);
			
			newEntity.setPositionRotation(entity.locX, entity.locY, entity.locZ, entity.pitch, entity.yaw);
			
			if (entity.passenger != null){
				entity.passenger.mount(newEntity);
			}
			
			world.removeEntity(entity);
			world.addEntity(newEntity, event.getSpawnReason());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
