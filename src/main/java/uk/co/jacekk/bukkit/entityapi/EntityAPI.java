package uk.co.jacekk.bukkit.entityapi;

import java.lang.reflect.Method;

import net.minecraft.server.EntityTypes;

import uk.co.jacekk.bukkit.baseplugin.BasePlugin;
import uk.co.jacekk.bukkit.entityapi.entity.APIEntityType;

public class EntityAPI extends BasePlugin {
	
	public void onEnable(){
		super.onEnable(false);
		
		try{
			Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[]{Class.class, String.class, int.class});
			
			a.setAccessible(true);
			
			for (APIEntityType APIType : APIEntityType.values()){
				a.invoke(null, APIType.getEntityClass(), APIType.getType().getName(), APIType.getType().getTypeId());
			}
		}catch (Exception e){
			e.printStackTrace();
			
			this.setEnabled(false);
			return;
		}
		
		this.pluginManager.registerEvents(new EntityReplaceListener(this), this);
	}
	
}
