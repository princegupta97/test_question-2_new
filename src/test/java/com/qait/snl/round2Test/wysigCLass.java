package com.qait.snl.round2Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class wysigCLass 
{
	WebDriver driver;
	JavascriptExecutor js;
	public wysigCLass(WebDriver driver,	JavascriptExecutor js) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.js = js;
				
	}
	
	public void wysigEditor()
	{
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(39) > a:nth-child(1)').click()");
	}
	
	public String changeTextOfEditor()
	{
		driver.switchTo().frame("mce_0_ifr");
		driver.findElement(By.id("tinymce")).clear();
		driver.findElement(By.id("tinymce")).sendKeys("QAInfotech");
		WebElement element = driver.findElement(By.id("tinymce"));
		Actions actions = new Actions(driver);
		
		actions.moveToElement(element, 0, 5)
		    .clickAndHold()
		    .moveByOffset(60, 0)
		    .release()
		    .perform();
		driver.switchTo().defaultContent();
		js.executeScript(("return document.querySelector('#mceu_3 > button > i').click()"));
		
		driver.switchTo().frame("mce_0_ifr");
		String text = (String) js.executeScript("return document.querySelector('#tinymce > p:nth-child(1) > strong').textContent");
		System.out.print(text);
		return text;
	}
	

}
