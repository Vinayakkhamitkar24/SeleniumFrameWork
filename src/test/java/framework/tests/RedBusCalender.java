package framework.tests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusCalender {

	static String month="August 2024";
	static WebDriver driver;
	static String calMonth;

	public static void navigateToMonth() throws InterruptedException
	{
		String[] parts = month.split(" ");
		String monthValue = parts[0].substring(0,3);
		String monthyearPart= monthValue+" "+parts[1];
		

		while(!driver.findElement(By.cssSelector("[style*='flex-grow: 2']")).getText().contains(monthyearPart))
		{
			driver.findElement(By.cssSelector("[style*='flex-grow: 2']+div[class*='Day']")).click();
			Thread.sleep(200);
			getHolidaysForMonth();
		}


	}
	
	public static void getHolidaysForMonth()
	{
		calMonth= driver.findElement(By.cssSelector("[style*='flex-grow: 2']")).getText().split(" ")[0];
		List<WebElement> noHoliday=driver.findElements(By.cssSelector(".holiday_count"));
		if(!noHoliday.isEmpty())
			System.out.println(noHoliday.get(0).getText()+" Present for month: "+ calMonth);
		else 
			System.out.println("No holiday there for  month: "+calMonth);
	}

	public static void getWeekOff()
	{
		List<String> WO = new ArrayList<String>();
		List<WebElement> currWeekOff = driver.findElements(By.cssSelector(".fgdqFw"));
		if(!currWeekOff.isEmpty())
		{
			String currentWO=currWeekOff.get(0).getText();
			WO.add(currentWO);
		}
		List<WebElement> weekOff = driver.findElements(By.cssSelector(".bwoYtA"));
		for(WebElement ele :weekOff)
		{
			String monthWeekoffs = ele.getText();
			WO.add(monthWeekoffs);
		}

		System.out.println(WO);		

	}

	public static boolean isCurrentMonthOrFutureMonth()
	{

		LocalDate date = LocalDate.now();
		int currentYear = date.getYear();
		int currentMonth= date.getMonthValue();

		int providedYear = Integer.parseInt(month.split(" ")[1]);
		String providedMonth= month.split(" ")[0].toUpperCase();
		int providedMonthValue= Month.valueOf(providedMonth).getValue();

		if(providedYear>currentYear)
			return true;
		else if(providedYear==currentYear)
		{
			if(providedMonthValue>=currentMonth)
			{
				return true;
			}
		}

		return false;

	}

	public static void main(String[] args) throws InterruptedException {

		if (isCurrentMonthOrFutureMonth())
		{
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(ops);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://www.redbus.in");
			driver.manage().window().maximize();
			driver.findElement(By.cssSelector(".dateText")).click();
			getHolidaysForMonth();
			navigateToMonth();
			getWeekOff();
			driver.quit();

		}
		else
		{
			System.out.println("Can not go back to previous month....");
		}

	}

}
