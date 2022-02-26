package com.test.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.test.Constants.SourcePath;



public class Utils {
	
	public static String getApp_Property(String key)
	{
		File file = new File(SourcePath.CONFIG_PROPERTIES_PATH);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties ps = new Properties();
		try {
			ps.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = ps.getProperty(key);
		return value;
		
	}
	public static String getUserData_Property(String key)
	{
		File file = new File(SourcePath.USERDATA_PATH);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties ps = new Properties();
		try {
			ps.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = ps.getProperty(key);
		return value;
		
	}
	public static String getUpdateUserData_Property(String key)
	{
		File file = new File(SourcePath.UPDATE_USERDATA_PATH);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties ps = new Properties();
		try {
			ps.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = ps.getProperty(key);
		return value;
		
	}

}
