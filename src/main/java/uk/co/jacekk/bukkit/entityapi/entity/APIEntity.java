package uk.co.jacekk.bukkit.entityapi.entity;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

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
	
}
