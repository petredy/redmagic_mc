package redmagic.handlers;

import redmagic.configuration.Localizations;
import redmagic.helpers.LocalizationHelper;
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
				LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
			}catch(Throwable err){
				//Logger.log("Couldn't find " + localizationFile);
			}
		}
	}
	
	
}
