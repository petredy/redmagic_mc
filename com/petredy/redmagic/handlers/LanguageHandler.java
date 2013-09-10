package com.petredy.redmagic.handlers;

import com.petredy.redmagic.lib.Localizations;
import com.petredy.redmagic.utils.LocalizationUtils;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageHandler {
	
	public static void init(){
		loadLanguages();	
	}
	/***
	* Loads in all the localization files from the Localizations library class
	*/
	private static void loadLanguages() {
		// For every file specified in the Localization library class, load them into the Language Registry
		for (String localizationFile : Localizations.localeFiles) {
			try{
				LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationUtils.getLocaleFromFileName(localizationFile), LocalizationUtils.isXMLLanguageFile(localizationFile));
			}catch(Throwable err){
				//Logger.log("Couldn't find " + localizationFile);
			}
		}
	}
	
	
}
