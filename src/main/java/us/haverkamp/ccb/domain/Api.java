package us.haverkamp.ccb.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public abstract class Api {
	public final String toString() {
		final Field[] fields = this.getClass().getDeclaredFields();
		
		final Map<String, Object> map = new HashMap<String, Object>();
		for(Field field : fields) {
			field.setAccessible(true); // allows access for private fields
			final int modifiers = field.getModifiers();
			
			// TODO consider if we should filter out final as well
			if(!Modifier.isStatic(modifiers)) {
				final String name = field.getName();
	
				try {
					final Object value = field.get(this);
					
					if(value != null) {
						map.put(name, value);
					}
				} catch(IllegalArgumentException e) {
					// this will never occur due to how this is implemented
					Logger.getLogger(this.getClass()).logException(e, Level.SEVERE);
				} catch(IllegalAccessException | NullPointerException e) {
					Logger.getLogger(this.getClass()).logException(e, Level.SEVERE);
				}
			}
		}
		
		return map.toString();
	}
}
