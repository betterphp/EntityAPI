package uk.co.jacekk.bukkit.entityapi.ai;

import uk.co.jacekk.bukkit.entityapi.entity.api.APIEntity;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.MathHelper;
import net.minecraft.server.Navigation;
import net.minecraft.server.PathEntity;
import net.minecraft.server.World;

public class APINavigation extends Navigation {
	
	private APIEntity entity;
	
	public APINavigation(EntityLiving entity, World world, float maxPathDistance){
		super(entity, world, maxPathDistance);
		
		this.entity = (APIEntity) entity;
	}
	
	@Override
	public boolean a(double d0, double d1, double d2, float f){
		PathEntity pathentity = this.a(MathHelper.floor(d0), ((int) d1), MathHelper.floor(d2));
		
		return this.a(pathentity, f);
	}
	
	@Override
	public boolean a(EntityLiving entityliving, float f){
		PathEntity pathentity = this.a(entityliving);
		
		return pathentity != null ? this.a(pathentity, f) : false;
	}
	
	@Override
	public boolean a(PathEntity path, float speed){
		return super.a(path, speed * (float) this.entity.getMovementSpeedMultiplier());
	}
	
}
