package com.qait.snl.round2Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Hover 
{
	WebDriver driver;
	JavascriptExecutor js;
	public Hover(WebDriver driver,JavascriptExecutor js)
	{
		this.driver = driver;
		this.js = js;
		
	}
	
	public long getNoOfImages()
	{
		 return (Long) js.executeScript("return document.getElementsByClassName('figure').length");
	}
	public void getMouseHoverText() throws InterruptedException
	{
		Actions ToolTip1 = new Actions(driver);
	    WebElement googleLogo = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
	    ToolTip1.clickAndHold(googleLogo).perform();
	    Assert.assertTrue(driver.findElement(By.cssSelector("div.figcaption>h5")).isDisplayed());
	}
	
	public void getMouseHoverOutText() throws InterruptedException
	{
		Assert.assertFalse(driver.findElement(By.cssSelector("div.figcaption>h5")).isDisplayed());	
	}

}
