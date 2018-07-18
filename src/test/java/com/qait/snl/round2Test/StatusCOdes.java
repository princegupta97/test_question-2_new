package com.qait.snl.round2Test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.gargoylesoftware.htmlunit.WebClient;

import io.restassured.RestAssured;

public class StatusCOdes 
{
	WebDriver driver;
	JavascriptExecutor js;
	public StatusCOdes(WebDriver driver,	JavascriptExecutor js) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.js = js;
				
	}
	
	
	public void clickOnStatusCode()
	{
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(37) > a:nth-child(1)').click()");
		
	}
	public void clickOn404Link()
	{
		js.executeScript("return document.querySelector('#content > div > ul > li:nth-child(3) > a').click()");
		
	}
	
	/*public  int getStatusCode() throws IOException {
	    WebClient webClient = new WebClient();
	    int code = webClient.getPage(
	            "http://http://10.0.31.161:9292/status_codes/404/"
	    ).getWebResponse().getStatusCode();
	    webClient.close();
	    return code;
	}
*/
	
	 public int httpResponseCodeViaGet() 
	 {
		return RestAssured.get("http://10.0.31.161:9292/status_codes/404").statusCode();
       
	 }

}
