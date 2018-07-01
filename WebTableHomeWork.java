package com.cybertek.TestNG;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableHomeWork {

	String url = "https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8";
	WebDriver driver;

	@BeforeMethod
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);
	}

	@Test
	public void first() throws InterruptedException {
		//=====Selecting 100 per page=====
		Select sel = new Select(driver.findElement(By.id("recPerPage")));
		sel.selectByIndex(3);
		Thread.sleep(2000);
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		//======Accessing all pages, table cells and putting in the Map====
	 for (int pages = 1; pages <= 22; pages++) {
			Thread.sleep(2000);
			int sizeOfRow = driver.findElements(By.xpath("//div[@id='viewDiv']//table//tbody//tr")).size();
			Thread.sleep(2000);
		for (int i = 1; i <= sizeOfRow; i++) {
				int uniqueID = 0;
				String info = "";
			for (int j = 1; j <= 5; j++) {
					String xpath = "//div[@id='viewDiv']//table//tbody//tr[" + i + "]/td[" + j + "]";
					String s = driver.findElement(By.xpath(xpath)).getText();
					if (j == 1) {
						uniqueID = Integer.parseInt(s);
					} else {
						info = info + s + "-";
					}
				}
				map.put(uniqueID, info);

			}
			driver.findElement(By.id("showNext")).click();
		}
	//=====Assertion Part=====	
			System.out.println(map.size());
			int expected=2127;
			int actual=map.size();
			Assert.assertEquals(actual, expected);

	 
	//=====Printing out key and the value of the Map=====	
//		for (Entry<Integer, String> each : map.entrySet()) {
//			System.out.println(each.getKey());
//			System.out.println(each.getValue());
//		}
	
	}

}
