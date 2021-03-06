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

import java.util.Map;

/**
 *
 * @author Raphaël Calabro (rcalabro@ideia.fr)
 */
public class JsMapEntry<K, V> implements Map.Entry<K, V> {
	
	private final K key;
	private final String keyAsString;
	private final JsMap<String, V> nativeMap;

	public JsMapEntry(K key, String keyAsString, JsMap<String, V> nativeMap) {
		this.key = key;
		this.keyAsString = keyAsString;
		this.nativeMap = nativeMap;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return nativeMap.get(keyAsString);
	}

	@Override
	public V setValue(V value) {
		return nativeMap.put(keyAsString, value);
	}
	
}
