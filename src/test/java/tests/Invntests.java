package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.InventoryPage;
import pageobjects.ItemPage;
import pageobjects.LoginPage;

public class Invntests extends TestBase {

	LoginPage loginp;
	InventoryPage invp;
	CartPage cartp;

	@BeforeClass
	public void loadTheInventoryPage() {
		loginp = new LoginPage(driver);
		loginp.validLogin();
	}

	@Test(description = "Checks if there are six items in the inventory page")
	public void tc01_areThereSixItems() {
		invp = new InventoryPage(driver);
		int expected_ItemsNum = invp.getItemsNum();
		int actual_ItemsNum = invp.getItems().size();
		assertTrue(actual_ItemsNum == expected_ItemsNum);
	}

	@Test(description = "Check if clicking on every add button changes it to a remove button with the right text")
	public void tc02_switchingAddBtn() {
		invp = new InventoryPage(driver);
		invp.verifyElementFullyLoaded(invp.getItems().get(5));
		invp.turnAllBtnsToAdd();
		boolean conditionToAssert = true; // It will be false if the number of remove buttons didn't grow by one
		for (WebElement el : invp.getAddItemBtns()) { // checks that by every click on a add-to-cart button, list of													// remove buttons increases by 1
			int sizeOfRemoveButtonListBefore = invp.getRemoveItemBtns().size();
			el.click();
			int sizeOfRemoveButtonListAfter = invp.getRemoveItemBtns().size();
			if (!(sizeOfRemoveButtonListAfter == sizeOfRemoveButtonListBefore + 1)) {
				conditionToAssert = false;
				break;
			}
		}
		assertTrue(conditionToAssert);
	}

	@Test(description = "Check if clicking on every remove button changes it to an add button with the right text")
	public void tc03_switchingRemoveBtn() {
		// Same logic as the test case before
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToRemove();
		boolean conditionToAssert = true;
		for (WebElement el : invp.getRemoveItemBtns()) {
			int sizeOfRemoveButtonListBefore = invp.getAddItemBtns().size();
			el.click();
			int sizeOfRemoveButtonListAfter = invp.getAddItemBtns().size();
			if (!(sizeOfRemoveButtonListAfter == sizeOfRemoveButtonListBefore + 1)) {
				conditionToAssert = false;
				break;
			}
		}
		assertTrue(conditionToAssert);
	}

	@Test(description = "Testing the cart of added products, when adding two products")
	public void tc04_cartTesting_Adding2() {
		// Checks if by adding 2 products to an empty cart, its quantity grows by 2
		invp = new InventoryPage(driver);
		boolean doesCounterfits = true;
		InventoryPage invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.clickByIndex(0);
		invp.clickByIndex(3);
		try {
			int counter = Integer.parseInt(invp.getQuantityBadge().getText());
			doesCounterfits = counter == 2;
		} catch (NoSuchElementException e) {
			doesCounterfits = false;
		} finally {
			assert (doesCounterfits);
		}
	}

	@Test(description = "Testing the cart of added products, when subtracting one product")
	public void tc05_cartTesting_Subtract1() {
		// Same logic as the test case before, now erasing an item from the cart
		invp = new InventoryPage(driver);
		boolean doesCounterfits = true;
		invp.getRemoveItemBtns().get(0).click();
		try {
			int counter = Integer.parseInt(invp.getQuantityBadge().getText());
			doesCounterfits = counter == 1;
		} catch (NoSuchElementException e) {
			doesCounterfits = false;
		} finally {
			assert (doesCounterfits);
		}
	}

	@Test(description = "Check if the inventory is sorted from Z to A")
	public void tc06_isSortedZtoA() {
		invp = new InventoryPage(driver);
		boolean isSorted = true;
		invp.getSortSlc().selectByVisibleText("Name (A to Z)"); // Pre condition for validation of the relevant sort
		invp.getSortSlc().selectByVisibleText("Name (Z to A)");
		int finalIndex = invp.getItems().size() - 2; // index to stop checking
		for (int i = 0; i <= finalIndex; i++) {
			String s1 = invp.getItems().get(i).getText(); // Current item's text
			String s2 = invp.getItems().get(i + 1).getText(); // Next item's text
			int compare = s1.compareTo(s2);
			if (!(compare > 0)) {
				isSorted = false;
				break;
			}
		}
		assertTrue(isSorted);
	}

	@Test(description = "Check if inventory is sorted by price, from low to high")
	public void tc07_isSortedLToH() { // check if products are sorted by price from low to high
		invp = new InventoryPage(driver);
		boolean isSorted = true;
		invp.getSortSlc().selectByVisibleText("Price (high to low)");
		invp.getSortSlc().selectByVisibleText("Price (low to high)");
		int finalIndex = invp.getItems().size() - 2; // index to stop checking
		for (int i = 0; i <= finalIndex; i++) {
			String s1 = invp.getAllPriceTags().get(i).getText().substring(1);
			s1.replace("$", "");
			String s2 = invp.getAllPriceTags().get(i + 1).getText().substring(1); // Next item's text
			s2.replace("$", "");
			double p1 = Double.parseDouble(s1);
			double p2 = Double.parseDouble(s2);
			if (p2 < p1) {
				isSorted = false;
				break;
			}
		}
		assertTrue(isSorted);
	}

	@Test(description = "Log-out method")
	public void tc08_checkLogOutMethod() {
		invp = new InventoryPage(driver);
		invp.getBurgerBtn().click();
		invp.verifyElementFullyLoaded(invp.getLogOutBtn());
		invp.getLogOutBtn().click();
		LoginPage loginp = new LoginPage(driver);
		loginp.verifyElementFullyLoaded(loginp.getLoginBtn());
		assert (loginp.getLoginBtn().isDisplayed());
	}

	@Test(description = "Check if selected items remain selected after log-out")
	public void tc09_chosenProductsAreSelected() {
		loginp = new LoginPage(driver);
		loginp.validLogin();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.getSortSlc().selectByVisibleText("Name (A to Z)");
		invp.clickByIndex(0);
		invp.clickByIndex(4);
		invp.logOut();
		LoginPage lp = new LoginPage(driver);
		lp.loginMethod("standard_user", "secret_sauce");
		invp = new InventoryPage(driver);
		assertTrue(invp.getAddItemBtns().size() == 4);
	}

	@Test(description = "Compare details of chosen products, between inventory page and cart page")
	public void tc10_selectedProductsInCart() {
		driver.manage().window().maximize();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.sleep(2000);
		invp.getCartBtn().click();
		CartPage cartp = new CartPage(driver);
		cartp.emptyCart();
		cartp.getBackToInventoryPageBtn().click();
		invp = new InventoryPage(driver);
		invp.clickByIndex(0);
		int lastProductIndex = invp.getAddItemBtns().size() - 1;
		invp.clickByIndex(lastProductIndex);
		HashMap<String, String> h = invp.getTwoProducts(); // Two products - key = name, price = value
		invp.sleep(2000);
		cartp = new CartPage(driver);
		invp.getCartBtn().click();
		cartp = new CartPage(driver);
		assertTrue(cartp.compareItems(h));
	}

	@Test(description = "Clicking on a specific product's title will direct to its page, with the same details:"
			+ "Name, description, price and status")
	public void tc11_ItempgInteraction() {
		cartp = new CartPage(driver);
		cartp.getBackToInventoryPageBtn();
		invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		String itemName = invp.getItems().get(0).getText();
		String itemDesc = invp.getAllItemsDescription().get(0).getText();
		String itemPrice = invp.getAllPriceTags().get(0).getText();
		String itemStatus = invp.getFirstBtn().getText();
		String[] relevantDetails = { itemName, itemDesc, itemPrice, itemStatus };
		invp.clickOnItemByIndex(0);
		ItemPage itemp = new ItemPage(driver);
		assertTrue(itemp.compareItemDetails(relevantDetails));
	}

}
