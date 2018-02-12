package com.ebbk.lock.untils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertyUtil implements java.io.Serializable{

	private static ConfigPropertyUtil instance;	//配置实例
	
	private final static String propertiesFile = "redisson-config.properties";//配置文件名
	

	private static Properties configInfoProps = null;

	private File configrationFile;				//属性文件
	
	private ConfigPropertyUtil(File configrationFile){
		this.configrationFile = configrationFile;
	}
	
	public static Properties getConfigInfoProps() {
		return configInfoProps;
	}
	
	static{
		initialize();
	}
	
	/**
	 * 初始化配置实例.
	 * @return
	 * @throws RuntimeException
	 */
	 public static synchronized ConfigPropertyUtil initialize() throws RuntimeException {
		 if(instance != null){
			 throw new RuntimeException("配置属性只能初始化一次！");
		 }
		 ClassLoader loader = ConfigPropertyUtil.class.getClassLoader();
		 File file = new File(loader.getResource(propertiesFile).getFile());
		 instance = new ConfigPropertyUtil(file);
		 instance.setProperty();
		 return instance;
	 }
	 
	 /**
	  * 返回配置实例
	  * @return
	  * @throws RuntimeException
	  */
	 public static synchronized ConfigPropertyUtil getInstance() throws RuntimeException{
		 if(instance == null){
			 initialize();
		 }
		 return instance;
	 }
	 
	 /**
	 * 获取属性
	 * @param name
	 * @return
	 */
	public static String getProperty(String name){
		return configInfoProps.getProperty(name);
	}
		
	/**
	 * 设置属性
	 * @return
	 */
	@SuppressWarnings("static-access")
	private void setProperty(){
		Properties pro = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(configrationFile);
			pro.load(inputStream);
			instance.configInfoProps = pro;					
		} catch (IOException e) {
		}
	}
	

}
