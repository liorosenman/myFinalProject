package tests;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.zip.X000A_NTFS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutStepOne;
import pageobjects.CheckOutStepTwo;
import pageobjects.InventoryPage;
import pageobjects.ItemPage;
import pageobjects.LoginPage;
import pageobjects.OrderCompletePage;

public class Carttest extends TestBase {

	LoginPage loginp;
	InventoryPage invp;
	CartPage cartp;
	CheckOutStepOne check1pg;

	@BeforeClass
	public void load_cartpage() {

		loginp = new LoginPage(driver);
		loginp.validLogin();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.clickByIndex(0);
		invp.clickByIndex(4); // 4th index is the last one since list of add-to-cart buttons was reduced in
								// the line before
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.verifyElementFullyLoaded(cartp.getYourCartTitle());

	}

	@Test(description = "Continue-shopping-link directs back to inventory page")
	public void tc01_continueShoppingLink() {

		cartp = new CartPage(driver);
		cartp.sleep(2000);
		cartp.getContinueShopLink().click();
		invp = new InventoryPage(driver);
		invp.verifyElementFullyLoaded(invp.getItems().get(5));
		assertTrue(invp.getItems().get(5).isDisplayed());

	}

	@Test(description = "Does quantity update when removing a product from the cart")
	public void tc02_isRemoveButtonOk() {
		invp = new InventoryPage(driver);
		invp.click(invp.getCartBtn());
		cartp = new CartPage(driver);
		cartp.click(cartp.getAllRemoveBtns().get(0));
		assertTrue(cartp.isThisTheQuantityInCart(1));

	}

	@Test(description = "Proceed to check-out after choosing products")
	public void tc03_clickProceedWithItems() // At least one item was chosen
	{
		cartp = new CartPage(driver);
		cartp.clickOnCheckOutBtn();
		check1pg = new CheckOutStepOne(driver);
		check1pg.verifyElementFullyLoaded(check1pg.getContinueBtn());
		assertTrue(check1pg.getContinueBtn().isDisplayed());
	}

	@Test(description = "You cannot proceed to check-out with no products picked... Stay on cart page")
	public void tc04_clickProceedWithNoItems() {
		check1pg = new CheckOutStepOne(driver);
		check1pg.click(check1pg.getCancelBtn());
		cartp = new CartPage(driver);
		cartp.emptyCart();
		cartp.clickOnCheckOutBtn();
		assertTrue(cartp.getCheckOutBtn().isDisplayed());
	}

}
