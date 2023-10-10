package tests;

import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutStepOne;
import pageobjects.CheckOutStepTwo;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import pageobjects.OrderCompletePage;

public class OrderCompleteTest extends TestBase {

	LoginPage loginp;
	InventoryPage invp;
	CartPage cartp;
	CheckOutStepOne check1pg;
	CheckOutStepTwo check2pg;
	OrderCompletePage ocp;

	@BeforeClass
	public void load_OrderCompletePg() {
		loginp = new LoginPage(driver);
		loginp.validLogin();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.clickByIndex(0);
		invp.clickByIndex(4); // 4th index is the last one since list of add-to-cart buttons was reduce in the line before
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
		check1pg = new CheckOutStepOne(driver);
		check1pg.fillDtls("Avi", "Levi", "123456");
		check1pg.getContinueBtn().click();
		check2pg = new CheckOutStepTwo(driver);
		check2pg.getFinishBtn().click();
	}

	@Test(description = "Verifying that when the deal is closed, the cart is empty")
	public void tc01_isCartEmpty() {
		ocp = new OrderCompletePage(driver);
		ocp.getCartBtn().click();
		cartp = new CartPage(driver);
		assertTrue(cartp.getProductsLbl().size() == 0);
	}

}
