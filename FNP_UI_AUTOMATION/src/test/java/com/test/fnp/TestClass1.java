package com.test.fnp;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.datahelper.DataHelper;
import com.project.pageobject.HomePage;
import com.project.utils.BaseClass;
import com.project.utils.EnvironmentConfig;

public class TestClass1 extends BaseClass{
 
	HomePage homePage;
	@BeforeClass
	public void initialize() {
	 homePage=new HomePage(driver);
	}
	
	// To verify Home Page -- 
	//test
	@Test
	public void verifyMainPage() {
		String page= EnvironmentConfig.getInstance().getProperty("pageurl");
		System.out.println(page);
    	driver.get(page);
  	    homePage.searchItem();
	}
	
	@Test(dataProviderClass = DataHelper.class, dataProvider = "UserDetails")
	public void verifyDeliveryLocation(String name , String password) {
		System.out.println(name + " "+password);
		String page= EnvironmentConfig.getInstance().getProperty("pageurl");
		System.out.println(page);
    	driver.get(page);
  	    homePage.verifyDeliveryAddress();
  	    
	}
}
