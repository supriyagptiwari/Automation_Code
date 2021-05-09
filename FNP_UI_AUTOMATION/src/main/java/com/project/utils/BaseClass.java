package com.project.utils;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	String currentUserDirectry=System.getProperty("user.dir");
	public static String country;
	public static WebDriver driver;
	
	@SuppressWarnings("resource")
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser", "env", "market"})
	 public void beforeSuite(String browser,String env, @Optional("") String market, ITestContext context)
	{
		
		System.out.println("Test Environment --> "+env);
		System.out.println("Test Market --> "+market);
		
		country=market;
		EnvironmentConfig.initEnvironment(env, market);
		String path= System.getProperty("user.dir")+"/"+CommonTestConstants.propertyPath;
		System.out.println(path);
		EnvironmentConfig.initEnvironment(path);
		EnvironmentConfig.getInstance().setProperty("currentEnv", env);
		EnvironmentConfig.getInstance().setProperty("country",market);
		getDriver(browser);
		
	
	}
	
	
	public void getDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new ChromeDriver();
			break;
		default:
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
}
