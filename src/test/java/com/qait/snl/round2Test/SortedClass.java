package com.qait.snl.round2Test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SortedClass 
{
	WebDriver driver;
	JavascriptExecutor js;
	public SortedClass(WebDriver driver,JavascriptExecutor js)
	{
		this.driver = driver;
		this.js = js;
		
	}
	
	public void checkThatTableIsSortedORNot()
	{
		js.executeScript("return document.querySelector('#table1 > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(4) > span:nth-child(1)').click()");
		js.executeScript("return document.querySelector('#table1 > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(4) > span:nth-child(1)').click()");

		java.util.List<WebElement> elementList = new ArrayList<WebElement>();
		ArrayList<Double> obtainedList = new ArrayList<Double>();
		for(int i=1;i<=4;i++)
		{
			obtainedList.add(Double.parseDouble(js.executeScript("return document.querySelector('#table1 > tbody > tr:nth-child(" + i +") > td:nth-child(4)').textContent").toString().replaceAll("[^0-9\\.]+", "")));
		}

		ArrayList<Double> sortedList = new ArrayList<Double>();   
		for(Double s:obtainedList)
		{
			System.out.println(s);
			sortedList.add(s);
		}
		
		
		Collections.sort(sortedList, Collections.reverseOrder());
		
		for(Double s:sortedList)
		{
			System.out.println(s);;
		}

		Assert.assertTrue(sortedList.equals(obtainedList));
	}


}
