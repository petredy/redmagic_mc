package redmagic.core;

import java.util.logging.Level;

import redmagic.Redmagic;
import redmagic.configuration.Comments;
import redmagic.configuration.Reference;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Logger {
	
	public static void init(Configuration config){
		Property propDebug = config.get(Configuration.CATEGORY_GENERAL, Reference.DEBUG, true);
		propDebug.comment = Comments.DEBUG;
		Redmagic.DEBUG = propDebug.getBoolean(true);
		Logger.log("Initialise Debugging...");
	}
	
	public static void logServer(Level level, String message){
		//if(RedMagic.DEBUG)ConsoleLogManager.loggerLogManager.log(level, message);
	}
	
	public static void log(Level level, Object message){
		if(Redmagic.DEBUG)System.out.println("["+ Reference.MOD_NAME + "]" + getString(message));
	}
	
	public static void warn(Object message){
		if(Redmagic.DEBUG)System.out.println("["+ Reference.MOD_NAME + "]" + getString(message));
	}
	
	public static void error(Object message){
		log(Level.SEVERE, message);
	}
	
	public static void log(Object message){
		log(Level.FINE, message);
	}
	
	private static String getString(Object message){
		if(message instanceof String)return ((String)message).toString();
		if(message instanceof Integer)return ((Integer)message).toString();
		if(message instanceof Float)return ((Float)message).toString();
		if(message instanceof Boolean)return ((Boolean)message).toString();
		if(message instanceof Object)return ((Object)message).toString();
		if(message == null)return "null";
		return "";
	}
	
	
	
}
