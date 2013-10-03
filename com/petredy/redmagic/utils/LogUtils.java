package com.petredy.redmagic.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Reference;

import cpw.mods.fml.common.FMLLog;


import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class LogUtils {
	
	private static Logger redLogger = Logger.getLogger(Reference.MOD_ID);
	
	public static void init(Configuration config){
		Property propDebug = config.get(Configuration.CATEGORY_GENERAL, Reference.DEBUG, false);
		propDebug.comment = Comments.DEBUG;
		Redmagic.DEBUG = propDebug.getBoolean(true);
		if (Redmagic.DEBUG){
			LogUtils.info("Initialise Debugging...");
			redLogger.setParent(FMLLog.getLogger());
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

    public static void severe(String message) {

        log(Level.SEVERE, message);
    }

    public static void debug(String message) {

        log(Level.WARNING, "[DEBUG] " + message);
    }

    public static void warning(String message) {

        log(Level.WARNING, message);
    }

    public static void info(String message) {

        log(Level.INFO, message);
    }

    public static void config(String message) {

        log(Level.CONFIG, message);
    }

    public static void fine(String message) {

        log(Level.FINE, message);
    }

    public static void finer(String message) {

        log(Level.FINER, message);
    }

    public static void finest(String message) {

        log(Level.FINEST, message);
    }
	
	
	
}
