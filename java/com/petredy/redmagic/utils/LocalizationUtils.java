package com.petredy.redmagic.utils;

public class LocalizationUtils {

	public static boolean isXMLLanguageFile(String fileName) {
		return fileName.endsWith(".xml");
	}
	
	public static String getLocaleFromFileName(String fileName) {
		return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
	}
	
}
