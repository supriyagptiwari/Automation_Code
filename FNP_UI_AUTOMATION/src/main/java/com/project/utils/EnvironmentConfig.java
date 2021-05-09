package com.project.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentConfig {

	static EnvironmentConfig s_instance;
	private Properties envProps;
	private String currentEnv;
	private String market;

	private EnvironmentConfig(Properties props) {
		envProps = props;
	}

	private EnvironmentConfig(Properties props, String env, String market) {
		envProps = props;
		currentEnv = env;
		this.market=market;
	}

	public synchronized static void initEnvironment(String path) {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));

			if (s_instance == null) {
				s_instance = new EnvironmentConfig(props);
			} else {
				for (Object eachKey : props.keySet()) {
					EnvironmentConfig.getInstance().setProperty(eachKey.toString(),
							props.get(eachKey.toString()).toString());
				}
			}

		} catch (IOException e) {
			System.out
					.println(("Exception in loading the Property file form initEnvironment(path)-->" + e.getMessage()));
		}

	}

	public void setProperty(String key, String value) {
		envProps.setProperty(key, value);
	}
	
	public String getProperty(String key) {
		return (String) envProps.get(key);
	}

	public synchronized static void initEnvironment(String env, String market) {

		if (s_instance != null) {
			throw new RuntimeException("Trying to reinitilize env with:" + env);
		}
		Properties props = new Properties();
		s_instance = new EnvironmentConfig(props, env, market);
	}

	public static EnvironmentConfig getInstance() {
		if (s_instance == null) {
			throw new RuntimeException("Environment not initialized.");
		}
		return s_instance;
	}

}
