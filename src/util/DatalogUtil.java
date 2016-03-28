package util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class DatalogUtil {
	private DatalogUtil(){
		
	}
	
	public static final int concurrency = Runtime.getRuntime().availableProcessors();
	
	public static <T> Set<T> createConcurrentSet(){
		return Collections.newSetFromMap(createConcurrentMap());
	}
	
	public static <K, V> ConcurrentMap<K, V> createConcurrentMap(){
		return new ConcurrentHashMap<>(16, 0.75f, concurrency);
	}
	
	public static <K, V> Set<V> getSetFromMap(Map<K, Set<V>> map, K key){
		Set<V> values = map.get(key);
		if(values == null){
			values = new LinkedHashSet<>();
			map.put(key, values);
		}
		return values;
	}
}
