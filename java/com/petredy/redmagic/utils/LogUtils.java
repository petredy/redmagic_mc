package com.petredy.redmagic.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Reference;

import cpw.mods.fml.common.FMLLog;



public class LogUtils {
	
	private static Logger redLogger;
	
	public static void init(Configuration config){
		Property propDebug = config.get(Configuration.CATEGORY_GENERAL, Reference.DEBUG, true);
		propDebug.comment = Comments.DEBUG;
		Redmagic.DEBUG = propDebug.getBoolean(true);
		if (Redmagic.DEBUG){
			redLogger = LogManager.getLogger(Reference.MOD_ID);
			LogUtils.info("Initialise Debugging...");
		}
	}

	public static void log(Object message){
		if(message == null){
			info(null);
		}else{
			info(message.toString());
		}
	}
	
    public static void log(Level logLevel, String message) {

    	if(Redmagic.DEBUG){
    		redLogger.log(logLevel, message);
    	}
    }

    public static void fatal(String message) {

        log(Level.FATAL, message);
    }

    public static void warning(String message) {

        log(Level.WARN, message);
    }

    public static void info(String message) {

        log(Level.INFO, message);
    }

    public static void debug(String message) {

        log(Level.DEBUG, message);
    }
	
	
	
}
