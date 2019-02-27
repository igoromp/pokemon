package com.stefanini.pokemon.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Log {
	
	private static final String context = "[POKEMON] - ";
	
	private Log() { }
	
	public static void info(String msg) {
		Logger.getGlobal().info(context + msg);
	}
	
	public static void error(String msg) {
		Logger.getGlobal().severe(context + msg);
	}
	
	public static void log(Level level, String msg) {
		Logger.getGlobal().log(level, context + msg);
	}
	
	public static void log(Level level, String msg, Throwable thrown) {
		Logger.getGlobal().log(level, context + msg, thrown);
	}

	public static void warning(String msg) {
		Logger.getGlobal().warning(context + msg);
	}
	
}
