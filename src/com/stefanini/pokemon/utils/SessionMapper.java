package com.stefanini.pokemon.utils;

import java.util.HashMap;
import java.util.Map;

public class SessionMapper {
	private static Map<String,Boolean> listUsersLogged;
	
	public SessionMapper() {
		if(listUsersLogged == null) listUsersLogged = new HashMap<>();
	}
	
	public void set(String key, Boolean status) {
		listUsersLogged.put(key, status);
	}
	
	public boolean get(String key) {
		return listUsersLogged.get(key);
	}
	
	public void clear() {
		listUsersLogged.clear();
	}
}
