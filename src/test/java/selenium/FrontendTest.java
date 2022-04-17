package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class FrontendTest {
	
	@Test
	public void testSearch() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions ffo = new FirefoxOptions();
		//fission.webContentIsolationStrategy"to 0 and "fission.bfcacheInParent" to false
		ffo.addArguments("--no-sandbox");
		ffo.addArguments("--enable-javascript");

		ffo.addArguments("--headless");
		ffo.addPreference("fission.webContentIsolationStrategy", 0);
		ffo.addPreference("fission.bfcacheInParent", false);

		WebDriver driver = new FirefoxDriver(ffo);
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		//driver.get("https://ikman.lk");
		//URL launch
		driver.get("http://localhost:4200");
		WebElement originput =    driver.findElement(By.name("originput"));

		WebElement destinput =    driver.findElement(By.name("destinput"));
		WebElement btnsearch =    driver.findElement(By.name("btnsearch"));
		WebElement result =    driver.findElement(By.name("result"));
		WebElement inputDate = driver.findElement(By.name("inputdate"));
		WebElement inputTime = driver.findElement(By.name("inputtime"));

		originput.sendKeys("Berlin Hbf");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		waitUntilVisible(inputDate, driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//how to interact with date picker???
		//important: correct format for date
		inputDate.sendKeys(createFrontendDate());
		inputTime.sendKeys("13:00");
		destinput.sendKeys("Stuttgart Hbf");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		waitUntilVisible(btnsearch, driver);
		btnsearch.click();
		waitUntilVisible(result, driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement listRows = driver.findElement(By.xpath("//*[@name='result']"));
		List<WebElement> cardList = listRows.findElements(By.className("card"));
		//List<WebElement> allDivChildren = listRows.findElements(By.tagName("div"));
		//should find 3 connections
		assertEquals(cardList.size(),3);

		driver.close();
		
	}
	
	private static void waitUntilVisible(WebElement element,WebDriver driver) {
		waitForCondition(ExpectedConditions.visibilityOf(element), Duration.ofSeconds(10), driver);
	}

	private static void waitForCondition(ExpectedCondition<WebElement> condition, Duration timeout, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(condition);
	}
	
	/*
	 * make sure to have a date in the future
	 */
	private static String createFrontendDate() {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate ld = LocalDate.now();
		ld = ld.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		SimpleDateFormat targetSdf = new SimpleDateFormat("EEE MMM dd yyyy", Locale.UK);
		Date now = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
	        
		String dateString = targetSdf.format(now);
		dateString = dateString + " 00:00:00 GMT+0";
		return dateString;
	}

}
