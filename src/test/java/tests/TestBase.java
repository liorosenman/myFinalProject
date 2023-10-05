package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.base.Verify;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.CartPage;
import pageobjects.CheckOutStepOne;
import pageobjects.CheckOutStepTwo;
import pageobjects.InventoryPage;
import pageobjects.ItemPage;
import pageobjects.LoginPage;
import pageobjects.OrderCompletePage;
import utils.Utils;

public class TestBase {

	WebDriver driver;
	LoginPage loginp;

	@BeforeClass
	public void Setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		driver.get("https://www.saucedemo.com/");
		driver.get(Utils.readProperty("url"));
		LoginPage loginp = new LoginPage(driver);

	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
