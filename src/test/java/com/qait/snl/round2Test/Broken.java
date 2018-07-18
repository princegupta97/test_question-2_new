package com.qait.snl.round2Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class Broken 
{
	WebDriver driver;
	JavascriptExecutor js;

	public Broken(WebDriver driver,JavascriptExecutor js)
	{
		this.driver = driver;
		this.js = js;
		
	}
	/* String source;
	public Boolean checkImageValidity(WebElement Im) throws MalformedURLException, IOException{
	    source = Im.getAttribute("src");
	    try{
	    
	    	BufferedImage img=ImageIO.read(new URL(source));
	    	return true;
	    }
	    catch(IllegalArgumentException e){
	        return false;
	    }

	}
	
	public void imageIsLoadedOrNot() throws MalformedURLException, IOException
	{
		Assert.assertTrue(checkImageValidity((WebElement) js.executeScript("return document.querySelector('#content > div > img:nth-child(2)')")));
	}*/
	int invalidImageCount = 0;
	public void validateInvalidImages() {
		try {
			
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) 
				{
					verifyimageActive(imgElement);
					
						
				}
			}
			System.out.println("Total no. of invalid images are "	+ invalidImageCount);
			Assert.assertEquals(invalidImageCount,2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = (HttpResponse) client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode()!=200)
			{
				invalidImageCount++;
				 System.out.println(imgElement.getAttribute("src"));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
		
	
	

}
