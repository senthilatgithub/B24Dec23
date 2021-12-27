package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1 {

	static {
		WebDriverManager.chromedriver().setup();
	}
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("file:///C:/Users/senthilkumar/Desktop/Sample1.html");
		Thread.sleep(500);
		driver.switchTo().frame(0);
		Thread.sleep(500);
		driver.findElement(By.id("t2")).sendKeys("A");
		Thread.sleep(500);
		driver.switchTo().defaultContent();
		Thread.sleep(500);
		driver.findElement(By.id("t1")).sendKeys("A");
		Thread.sleep(500);
		driver.switchTo().frame("f1");
		Thread.sleep(500);
		driver.findElement(By.id("t2")).sendKeys("B");
		Thread.sleep(500);
		driver.switchTo().defaultContent();
		Thread.sleep(500);
		driver.findElement(By.id("t1")).sendKeys("B");
		Thread.sleep(500);
		WebElement frameElement = driver.findElement(By.xpath("//iframe"));
		Thread.sleep(500);
		driver.switchTo().frame(frameElement);
		Thread.sleep(500);
		driver.findElement(By.id("t2")).sendKeys("C");
		Thread.sleep(500);
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		driver.findElement(By.id("t1")).sendKeys("C");

		
	}

}
