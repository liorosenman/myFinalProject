package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutStepOne;
import pageobjects.CheckOutStepTwo;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import pageobjects.OrderCompletePage;

public class CheckoutStepTwoTest extends TestBase {

	LoginPage loginp;
	InventoryPage invp;
	CartPage cartp;
	CheckOutStepOne check1pg;
	CheckOutStepTwo check2pg;
	OrderCompletePage ocp;

	@BeforeClass
	public void load_CheckOutStepTwoPage() {
		loginp = new LoginPage(driver);
		loginp.validLogin();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.clickByIndex(0);
		invp.clickByIndex(1); // 4th index is the last one since list of add-to-cart buttons was reduce in the	 line before
		invp.clickByIndex(2);
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
		check1pg = new CheckOutStepOne(driver);
		check1pg.fillDtls("Avi", "Levi", "123456");
		check1pg.getContinueBtn().click();
	}

	@Test(description = "Compare the items shown in checkout page to those in cart")
	public void tc01_areTheseProductsInCart() {
		driver.manage().window().maximize();
		check2pg = new CheckOutStepTwo(driver);
		HashMap<String, String> h = new HashMap<String, String>(); // Key=Label, value=price
		h = check2pg.getTwoProducts();
		check2pg.getCartBtn().click();
		cartp = new CartPage(driver);
		assertTrue(cartp.compareItems(h));
	}

	@Test(description = "Verifying the expected cost of products is shown (without tax)")
	public void tc02_SumComparison() {
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
		check1pg = new CheckOutStepOne(driver);
		check1pg.fillDtls("Avi", "Levi", "123456");
		check1pg.getContinueBtn().click();
		check2pg = new CheckOutStepTwo(driver);
		assertEquals(check2pg.getActualPrice_InDouble(), check2pg.expectedSumNoTax());
	}

	@Test(description = "Was the tax calculated properly")
	public void tc03_IsTheTaxAccurate() {
		check2pg = new CheckOutStepTwo(driver);
		int expected_TaxPercent = 8;
		check2pg = new CheckOutStepTwo(driver);
		double tax = check2pg.getTax_InDouble();
		double sum = check2pg.getActualPrice_InDouble();
		double tax_percent = (tax / sum) * 100;
		int actual_TaxPercent = (int) tax_percent;
		assertEquals(actual_TaxPercent, expected_TaxPercent);
	}

	@Test(description = "Checking the final sum (after tax) was calculated properly")
	public void tc04_TotalSumCalc() {
		check2pg = new CheckOutStepTwo(driver);
		double actual_sum = check2pg.getActualPrice_InDouble();
		double tax = check2pg.getTax_InDouble();
		double expected_TotalSum = actual_sum + tax;
		double actual_TotalSum = check2pg.getTotalWithTax_InDouble();
		assertEquals(actual_TotalSum, expected_TotalSum);
	}

	@Test(description = "Closing the deal")
	public void tc05_pressingFinish() {
		check2pg = new CheckOutStepTwo(driver);
		check2pg.getFinishBtn().click();
		ocp = new OrderCompletePage(driver);
		assertTrue(ocp.getOrderCompleteLbl().isDisplayed());
	}

}
