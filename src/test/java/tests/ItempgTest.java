package tests;

import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.InventoryPage;
import pageobjects.ItemPage;
import pageobjects.LoginPage;

public class ItempgTest extends TestBase {

	@BeforeClass
	public void loadItemPage() {
		LoginPage lp = new LoginPage(driver);
		lp.validLogin();
		InventoryPage invp = new InventoryPage(driver);
		invp.turnAllBtnsToAdd();
		invp.getItems().get(0).click();
	}

	@Test
	public void addBtnAddsToQuantity() {
		// After adding the item, the quantity in cart should be convertible to int
		ItemPage itemp = new ItemPage(driver);
		itemp.sleep(2000);
		itemp.getAddButton().click();
		boolean didPlusOneWorked = true;
		try {
			int quantityBefore = Integer.parseInt(itemp.getQuantityBadge().getText());
			if (quantityBefore != 1)
				didPlusOneWorked = false;
		} catch (NoSuchElementException e) {
			didPlusOneWorked = false;

		} finally {
			assertTrue(didPlusOneWorked);
		}
	}

}
