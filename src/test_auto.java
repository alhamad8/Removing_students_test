import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class test_auto {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.edge.driver", "F:\\Microsoft edge webdriver\\msedgedriver.exe");

		WebDriver driver = new EdgeDriver();
		String url = "http://127.0.0.1:5500/index.html";
		driver.get(url);

		List<WebElement> studentsName = driver.findElements(By.tagName("option"));
		int totalNumberOfStudents = studentsName.size();
		System.out.println(totalNumberOfStudents + "this is the original number of students");

		for (int i = 0; i <= studentsName.size(); i++) {
			if (i % 2 != 0) {
				studentsName.get(i).click();
				driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();

			}

		}

		List<WebElement> studentsAfterRemove = driver.findElements(By.tagName("option"));

		int actualNumberOfStudents = studentsAfterRemove.size();
		int expectedNumber = totalNumberOfStudents - actualNumberOfStudents;
		System.out.println(actualNumberOfStudents + " this is the number of students after removing");
		Assert.assertEquals(actualNumberOfStudents, expectedNumber);
		driver.findElement(By.xpath("//*[@id=\"selectNow\"]")).click();
		Thread.sleep(1000);
		Date cuurentDate = new Date();
		String actualDate = cuurentDate.toString().replace(":", "_");
		TakesScreenshot src = ((TakesScreenshot) driver);
		File srcFile = src.getScreenshotAs(OutputType.FILE);
		File Dest = new File(".//mypics/" + actualDate + ".png");
		FileUtils.copyFile(srcFile, Dest);

	}

}
