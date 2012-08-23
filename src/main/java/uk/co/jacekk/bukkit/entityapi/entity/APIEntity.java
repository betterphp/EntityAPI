package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import uk.co.jacekk.bukkit.entityapi.ai.APIPathfinderGoal;

public interface APIEntity {
	
	/**
	 * Gets the reason that caused this entity to be spawned. Note that this may not carry over across a server restart.
	 * 
	 * @return	The SpawnReason.
	 */
	public SpawnReason getSpawnReason();
	
	/**
	 * Sets the movement speed multiplier, this is a factor that is used to modify the distance that a entity moves per update. It effectively changes their speed.
	 * 
	 * @param multiplier	The multiplier
	 * @throws				IllegalArgumentException if the multiplier is negative or greater than 8.0 
	 */
	public void setMovementSpeedMultiplier(double multiplier) throws IllegalArgumentException;
	
	/**
	 * Gets the movement speed multiplier applied to this entity.
	 * 
	 * @return	The multiplier
	 */
	public double getMovementSpeedMultiplier();
	
	/**
	 * If true the entity will not follow a path that passes through water blocks.
	 * 
	 * @param avoidWater	If the entity should avoid water.
	 */
	public void setAvoidsWater(boolean avoidWater);
	
	/**
	 * @return	If the entity should avoid water.
	 */
	public boolean getAvoidsWater();
	
	/**
	 * Sets if the entity is able to pass through closed doors, this does not necessarily mean that they will break doors.
	 * 
	 * @param breakDoors	If the entity should break doors.
	 */
	public void setBreakDoors(boolean breakDoors);
	
	/**
	 * @return	If the entity should break doors.
	 */
	public boolean getBreakDoors();
	
	/**
	 * Sets if the entity is able to pass through open doors.
	 * 
	 * @param enterDoors	If the entity should go through open doors.
	 */
	public void setEnterDoors(boolean enterDoors);
	
	/**
	 * @return	If the entity should go through open doors.
	 */
	public boolean getEnterDoors();
	
	/**
	 * Sets id the entity should follow a path that is exposed to direct sunlight.
	 * 
	 * @param avoidSun	If the entity should avoid sunlight.
	 */
	public void setAvoidSun(boolean avoidSun);
	
	/**
	 * @return	If the entity should avoid sunlight.
	 */
	public boolean getAvoidSun();
	
	/**
	 * Sets if the swimming AI should be applied to this entity, if it is it will move upwards when in water.
	 * 
	 * @param canSwim	If the entity can swim.
	 */
	public void setCanSwim(boolean canSwim);
	
	/**
	 * @return	If the entity can swim.
	 */
	public boolean getCanSwim();
	
	/**
	 * Adds a new goal selector.
	 * 
	 * @param selector	The goal selector to add.
	 */
	public void addGoalSelector(APIPathfinderGoal selector);
	
	/**
	 * Adds a new target selector.
	 * 
	 * @param selector	The target selector to add.
	 */
	public void addTargetSelector(APIPathfinderGoal selector);
	
}
