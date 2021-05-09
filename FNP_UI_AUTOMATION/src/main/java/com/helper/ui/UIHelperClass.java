package com.helper.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utils.CommonTestConstants;

public class UIHelperClass {
	
	WebDriver driver ;
	
	public UIHelperClass(WebDriver driver){
		this.driver=driver;
	}

	public void explicitWait(WebElement locator) {
		WebDriverWait  wait =new WebDriverWait(driver, CommonTestConstants.explicitWait);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	
	public void scrollTillVisibleText(String text) {
	    WebElement ele= driver.findElement(By.xpath("//*[text(),'" +text  +"']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", ele);
	}
	
	public void scrollTillVisibleText(WebElement ele) {
	    JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", ele);
	}
	
	public void scrollToTop() {
	    JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,-250)");
	}
	
	public void scrollDownTillBottomOfPage() {
	    JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void takeScreenshot() throws IOException {
		String location = System.getProperty("user.dir")+ CommonTestConstants.imageFolderPath;
	    TakesScreenshot screenshot=(TakesScreenshot)driver;
	    File file=screenshot.getScreenshotAs(OutputType.FILE);
	    System.out.println(System.currentTimeMillis());
	    File finalOutput= new File(location + "/Image"+ System.currentTimeMillis()+".jpg");
	    FileUtils.copyFile(file, finalOutput);
	}
	
	public void fluentWait(String  text) {
		By locator=By.xpath("//*[text()='" +text  +"']");
		@SuppressWarnings("unchecked")
		Wait wait =new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5,TimeUnit.SECONDS).ignoring(Exception.class);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void keyBoardAndMouseAction(WebElement  ele) {
	
		Actions actions=new Actions(driver);
		Action action=actions.moveToElement(ele).doubleClick().build();
		action.perform();
	}
}
