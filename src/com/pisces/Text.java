package com.pisces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Text {
	private static HashMap<String, String> intl=new HashMap<String, String>();
	private static String lang="";
	
	public static void load(String languageCode) {
		lang=languageCode;
		FileReader fw = null;
		try {
			FileHandle file=Gdx.files.internal("../pisces-core/assets/lang/"+languageCode+".txt");
			fw = new FileReader(file.path());
			String line=null;
			BufferedReader read=new BufferedReader(fw);
			
			while ((line=read.readLine())!=null){
				String[] split=line.split("=");
				if (split.length==1) {
					intl.put(split[0],  split[0]);
				} else {
					intl.put(split[0],  split[1]);
				}
			}
			
			read.close();
			fw.close();
		} catch (FileNotFoundException e) {
			Tools.err("Couldn't find the file: ./assets/lang/"+languageCode+".txt");
			e.printStackTrace();
		} catch (IOException e) {
			Tools.err("Some sort of error closing file: ./assets/lang/"+languageCode+".txt");
			e.printStackTrace();
		}
	}
	
	public static String I(String english) {
		if (intl.containsKey(english)) {
			return intl.get(english);
		}
		intl.put(english,  english);
		return english;
	}
	
	public static String getLanguageCode() {
		return lang;
	}
}
