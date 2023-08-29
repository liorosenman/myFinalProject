package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutStepOne;
import pageobjects.CheckOutStepTwo;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;

public class CheckoutStepOneTest extends TestBase {
	
	LoginPage loginp;
	InventoryPage invp;
	CartPage cartp;
	CheckOutStepOne check1pg;
	CheckOutStepTwo check2pg;
	
	@BeforeClass (description = "2 products are chosen")
	public void load_CheckOutStepTwo()
	{
		loginp = new LoginPage(driver);
		loginp.validLogin();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.clickByIndex(0);
		invp.clickByIndex(4); // 4th index is the last one since list of add-to-cart buttons was reduce in the line before
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
	
	}
	
	@Test (dataProvider = "ValidPersonalDetails", dataProviderClass = Data.class, description = "First name, last name and zip are valid, continue to final checkout")
	public void tc01_proceedWithValidDetails(String firstName, String lastName, String zip) throws InterruptedException
	{
		check1pg = new CheckOutStepOne(driver);
		check1pg.fillDtls(firstName, lastName, zip);
		check1pg.getContinueBtn().click();
		check2pg = new CheckOutStepTwo(driver);
		check2pg.verifyElementFullyLoaded(check2pg.getFinishBtn());
		assert(check2pg.getFinishBtn().isDisplayed());
		check2pg.getCancelBtn().click();
		invp = new InventoryPage(driver);
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
		
		
	}
	
	
	@Test (dataProvider = "AtLeastOneFieldIsEmpty", dataProviderClass = Data.class, description = "At least one of the fields is empty, cannot proceed to final checkout")
	public void tc02_proceedWithInValidDetails(String firstName, String lastName, String zip) throws InterruptedException
	{
		CheckOutStepTwo check2pg = new CheckOutStepTwo(driver);
		check2pg.getCancelBtn().click();
		invp = new InventoryPage(driver);
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		cartp.getCheckOutBtn().click();
		check1pg = new CheckOutStepOne(driver);
		check1pg.fillDtls(firstName, lastName, zip);
		check1pg.getContinueBtn().click();
		String expectedErrMsg = "";
		boolean isThereAnEmptyField = false;
		
		// What is the first field that is empty
		if (firstName.equals(""))
		{
			expectedErrMsg = "Error: First Name is required";
			isThereAnEmptyField = true;
		}
		if (!isThereAnEmptyField && lastName.equals(""))
		{
			expectedErrMsg = "Error: Last Name is required";
			isThereAnEmptyField = true;
		}
		if (!isThereAnEmptyField && zip.equals(""))
		{
			expectedErrMsg = "Error: Postal Code is required";
			isThereAnEmptyField = true;
		}	
		
		String actualErrMsg = check1pg.getErrorMsg().getText();
		assertEquals(actualErrMsg, expectedErrMsg);
			
	}
	
	@Test (description = "Back to cart page link")
	public void tc_03_CancelBtn()
	{
		check1pg = new CheckOutStepOne(driver);
		check1pg.getCancelBtn().click();
		CartPage cartp = new CartPage(driver);
		assertTrue(cartp.getCheckOutBtn().isDisplayed());
	}
	

}
