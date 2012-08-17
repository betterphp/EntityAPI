package uk.co.jacekk.bukkit.entityapi.entity;

import java.util.HashMap;

import net.minecraft.server.EntityLiving;

import org.bukkit.entity.EntityType;

public enum APIEntityType {
	
	BLAZE(			EntityType.BLAZE,			APIEntityBlaze.class),
	CAVE_SPIDER(	EntityType.CAVE_SPIDER,		APIEntityCaveSpider.class),
	CHICKEN(		EntityType.CHICKEN,			APIEntityChicken.class),
	COW(			EntityType.COW,				APIEntityCow.class),
	CREEPER(		EntityType.CREEPER,			APIEntityCreeper.class),
	ENDER_DRAGON(	EntityType.ENDER_DRAGON,	APIEntityEnderDragon.class),
	ENDERMAN(		EntityType.ENDERMAN,		APIEntityEnderman.class),
	GHAST(			EntityType.GHAST,			APIEntityGhast.class),
	GIANT(			EntityType.GIANT,			APIEntityGiantZombie.class),
	IRON_GOLEM(		EntityType.IRON_GOLEM,		APIEntityIronGolem.class),
	MAGMA_CUBE(		EntityType.MAGMA_CUBE,		APIEntityMagmaCube.class),
	MUSHROOM_COW(	EntityType.MUSHROOM_COW,	APIEntityMushroomCow.class),
	OCELOT(			EntityType.OCELOT,			APIEntityOcelot.class),
	PIG(			EntityType.PIG,				APIEntityPig.class),
	PIG_ZOMBIE(		EntityType.PIG_ZOMBIE,		APIEntityPigZombie.class),
	SHEEP(			EntityType.SHEEP,			APIEntitySheep.class),
	SILVERFISH(		EntityType.SILVERFISH,		APIEntitySilverfish.class),
	SKELETON(		EntityType.SKELETON,		APIEntitySkeleton.class),
	SLIME(			EntityType.SLIME,			APIEntitySlime.class),
	SNOWMAN(		EntityType.SNOWMAN,			APIEntitySnowman.class),
	SPIDER(			EntityType.SPIDER,			APIEntitySpider.class),
	SQUID(			EntityType.SQUID,			APIEntitySquid.class),
	VILLAGER(		EntityType.VILLAGER,		APIEntityVillager.class),
	WOLF(			EntityType.WOLF,			APIEntityWolf.class),
	ZOMBIE(			EntityType.ZOMBIE,			APIEntityZombie.class);
	
	private EntityType type;
	private Class<? extends EntityLiving> clazz;
	
	private static HashMap<EntityType, APIEntityType> typeLookupTable;
	private static HashMap<Class<? extends EntityLiving>, APIEntityType> classLookupTable;
	
	private APIEntityType(EntityType type, Class<? extends EntityLiving> clazz){
		this.type = type;
		this.clazz = clazz;
	}
	
	static {
		typeLookupTable = new HashMap<EntityType, APIEntityType>();
		classLookupTable = new HashMap<Class<? extends EntityLiving>, APIEntityType>();
		
		for (APIEntityType type : values()){
			typeLookupTable.put(type.getType(), type);
			classLookupTable.put(type.getEntityClass(), type);
		}
	}
	
	public EntityType getType(){
		return this.type;
	}
	
	public Class<? extends EntityLiving> getEntityClass(){
		return this.clazz;
	}
	
	public static boolean entityTypeSupported(EntityType entityType){
		return typeLookupTable.containsKey(entityType);
	}
	
	public static APIEntityType getByEntityType(EntityType entityType){
		return typeLookupTable.get(entityType);
	}
	
	public static boolean isCustomEntity(Class<? extends EntityLiving> clazz){
		return classLookupTable.containsKey(clazz);
	}
	
	public static APIEntityType getByClass(Class<? extends EntityLiving> clazz){
		return classLookupTable.get(clazz);
	}
	
}
