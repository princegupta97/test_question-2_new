package com.qait.snl.round2Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.asserts.*;
import org.testng.annotations.*;
public class Test1 
{
	WebDriver driver;
	JavascriptExecutor js;
	LoginPage loginPage;
	StatusCOdes statusCOdes;
	wysigCLass wysigCLass;
	Hover hover;
	SortedClass sortedClass;
	Broken broken;
	public Test1() 
	{
		// TODO Auto-generated constructor stub
		System.setProperty("webdriver.chrome.driver","/home/princegupta/Downloads/chromedriver");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		loginPage = new LoginPage(driver, js);
		statusCOdes = new StatusCOdes(driver, js);
		wysigCLass = new wysigCLass(driver, js);
		hover = new Hover(driver, js);
		sortedClass = new SortedClass(driver, js);
		broken = new Broken(driver, js);
	}
	
	public void getBasicPage()
	{
		driver.get("http://10.0.31.161:9292/");
	}
	
	@Test
	public void basicAuth()
	{
		getBasicPage();
		driver.get("http://admin:admin@10.0.31.161:9292/basic_auth");
	}
	
	@Test
	public void loginPage1() throws InterruptedException
	{
		getBasicPage();
		loginPage.openFormAuthentication();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('h2').innerHTML"), "Login Page");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//login
		Thread.sleep(500);
		loginPage.enterCorrectCredentials();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('h2').innerHTML.substring(26);"),"Secure Area");
		Thread.sleep(2000);
//logout		
		js.executeScript("return document.querySelector('#content > div > a > i').click()");
		Thread.sleep(2000);
		Assert.assertEquals((String)js.executeScript("return document.getElementById('flash').innerHTML.substring(13,47);"),"You logged out of the secure area!" );

	
	}
	
	@Test//(dependsOnMethods = {"logOut"})
	public void enterIncorrectCredentials() throws InterruptedException
	{
		getBasicPage();
		loginPage.openFormAuthentication();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('h2').innerHTML"), "Login Page");
		Thread.sleep(500);
		loginPage.enterInCorrectCredentials();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('#flash').innerHTML.substring(13,37);"),"Your username is invalid");
	}
	
/*	@Test(dependsOnMethods= {"loginPage1"})
	public void enterCorrectCredentials() throws InterruptedException
	{
		//loginPage1();
		Thread.sleep(500);
		loginPage.enterCorrectCredentials();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('h2').innerHTML.substring(26);"),"Secure Area");
		Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods = {"enterCorrectCredentials"})
	public void logOut() throws InterruptedException
	{
		js.executeScript("return document.querySelector('#content > div > a > i').click()");
		Thread.sleep(2000);
		Assert.assertEquals((String)js.executeScript("return document.getElementById('flash').innerHTML.substring(13,47);"),"You logged out of the secure area!" );
		
	}
*/	
	@Test
	public void statusCode()
	{
		getBasicPage();
		statusCOdes.clickOnStatusCode();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('h3').innerHTML"),"Status Codes");
		
		//clik on 404
		statusCOdes.clickOn404Link();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('p').innerHTML.substring(5,41)"),"This page returned a 404 status code");
		int code = statusCOdes.httpResponseCodeViaGet();
		System.out.println(code);
		Assert.assertEquals(404, code);
	}
	
/*	@Test(dependsOnMethods = {"statusCode"})
	public void clicOn404() throws IOException
	{
		statusCOdes.clickOn404Link();
		Assert.assertEquals((String)js.executeScript("return document.querySelector('p').innerHTML.substring(5,41)"),"This page returned a 404 status code");
		int code = statusCOdes.httpResponseCodeViaGet();
		System.out.println(code);
		Assert.assertEquals(404, code);
	}
*/	
	@Test
	public void WYSIWYG()
	{
		getBasicPage();
		wysigCLass.wysigEditor();
		Assert.assertEquals(js.executeScript("return document.querySelector('h3').innerHTML.substring(25)"),"TinyMCE WYSIWYG Editor" );
		Assert.assertEquals(wysigCLass.changeTextOfEditor(),"QAInfotech");
		
	}
	
	@Test
	public void hover() throws InterruptedException
	{
		getBasicPage();
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(22) > a:nth-child(1)').click()");
	
		Assert.assertEquals(hover.getNoOfImages(),3);
		hover.getMouseHoverOutText();
		hover.getMouseHoverText();
		//Thread.sleep(3000);
		
	
	}
	@Test
	public void SortedTables()
	{
		getBasicPage();
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(36) > a:nth-child(1)').click()");
		Assert.assertEquals(js.executeScript("return document.querySelector('h3').textContent"), "Data Tables");
		sortedClass.checkThatTableIsSortedORNot();
		
		
	}
	
	@Test
	public void brokenImages() throws MalformedURLException, IOException
	{
		getBasicPage();
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(3) > a:nth-child(1)').click()");
		broken.validateInvalidImages();;

		
	}
	@Test
	public void exitIntent() throws AWTException
	{
		getBasicPage();
		driver.manage().window().maximize();
		js.executeScript("return document.querySelector('#content > ul:nth-child(4) > li:nth-child(13) > a:nth-child(1)').click()");
		Robot robot = new Robot();
		robot.delay(1500);

		robot.mouseMove(500, 400);
		robot.mouseMove(400, 300);
		robot.mouseMove(300, 100);
		robot.delay(2000);
		robot.mouseMove(300, 200);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	@AfterTest
	public void close()
	{
		driver.close();
	}
}