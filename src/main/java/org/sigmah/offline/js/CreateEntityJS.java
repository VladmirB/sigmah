package org.sigmah.offline.js;

/*
 * #%L
 * Sigmah
 * %%
 * Copyright (C) 2010 - 2016 URD
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.extjs.gxt.ui.client.data.RpcMap;
import com.google.gwt.core.client.JsArrayString;
import java.util.HashMap;
import java.util.Map;
import org.sigmah.shared.command.CreateEntity;

/**
 * JavaScript version of the {@link CreateEntity} command.
 * 
 * @author Raphaël Calabro (rcalabro@ideia.fr)
 */
public final class CreateEntityJS extends CommandJS {
	
	protected CreateEntityJS() {
	}
	
	public static CreateEntityJS toJavaScript(CreateEntity createEntity) {
		final CreateEntityJS createEntityJS = Values.createJavaScriptObject(CreateEntityJS.class);
		
		createEntityJS.setEntityName(createEntity.getEntityName());
		createEntityJS.setProperties(createEntity.getProperties());
		
		return createEntityJS;
	}
	
	public CreateEntity toCreateEntity() {
		return new CreateEntity(getEntityName(), getPropertyMap());
	}
	
	public native String getEntityName() /*-{
		return this.entityName;
	}-*/;

	public native void setEntityName(String entityName) /*-{
		this.entityName = entityName;
	}-*/;
	
	public native JsMap<String, String> getProperties() /*-{
		return this.properties;
	}-*/;
	
	public native void setProperties(JsMap<String, String> properties) /*-{
		this.properties = properties;
	}-*/;
	
	public Map<String, Object> getPropertyMap() {
		if(getProperties() != null) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			
			final JsMap<String, String> properties = getProperties();
			final JsArrayString keys = properties.keyArray();
			
			final ObjectJsMapBoxer boxer = new ObjectJsMapBoxer();
			
			for(int index = 0; index < keys.length(); index++) {
				final String key = keys.get(index);
				map.put(key, boxer.fromString(properties.get(key)));
			}
			
			return map;
		}
		return null;
	}
	
	public void setProperties(RpcMap properties) {
		if(properties != null) {
			final JsMap<String, String> map = JsMap.createMap();
			final ObjectJsMapBoxer boxer = new ObjectJsMapBoxer();
			
			for(final Map.Entry<String, Object> entry : properties.entrySet()) {
				map.put(entry.getKey(), boxer.toString(entry.getValue()));
			}
			
			setProperties(map);
		}
	}
}
