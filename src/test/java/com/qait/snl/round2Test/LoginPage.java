package com.qait.snl.round2Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
	WebDriver driver;
	JavascriptExecutor js;
	public LoginPage(WebDriver driver,	JavascriptExecutor js) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.js = js;
				
	}
	
	public void openFormAuthentication()
	{
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(18) > a:nth-child(1)').click();");
	}
	
	public void enterInCorrectCredentials()
	{
		js.executeScript(("return document.getElementById('username').value='incorrectcredentials'"));
		js.executeScript(("return document.getElementById('password').value='incorrectcredentials'"));
		js.executeScript("document.querySelector('#login > button > i').click();");
	}
	
	public void enterCorrectCredentials()
	{
		js.executeScript(("return document.getElementById('username').value='tomsmith'"));
		js.executeScript(("return document.getElementById('password').value='SuperSecretPassword!'"));
		js.executeScript("document.querySelector('#login > button > i').click();");
	}

}
