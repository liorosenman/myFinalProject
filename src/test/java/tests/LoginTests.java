package tests;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import tests.Data;

public class LoginTests extends TestBase {

	LoginPage loginp;
	InventoryPage invp;

	@Test(dataProvider = "allValuesOk", dataProviderClass = Data.class)
	public void LoginOkValues(String username, String password) // Valid values for login
	{
		loginp = new LoginPage(driver);
		loginp.loginMethod(username, password);
		invp = new InventoryPage(driver);
		assertTrue(invp.getProductsLbl().isDisplayed());
		invp.logOut();
	}

	@Test(dataProvider = "wrongValuesNoEntry", dataProviderClass = Data.class) // Invalid values for login
	public void noEntryWrongValues(String username, String password) throws InterruptedException {
		loginp = new LoginPage(driver);
		loginp.sleep(3000);
		loginp.loginMethod(username, password);
		loginp.sleep(2000);
		assertTrue(loginp.getErrorMsg().isDisplayed());

	}

}
