package pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends MenuPage {

	@FindBy(css = "#checkout") // CheckOut Button
	private WebElement checkOutBtn;
	@FindBy(css = ".inventory_item_name") // All product name labels
	private List<WebElement> productsLbl;
	@FindBy(css = ".inventory_item_price") // All price tags
	private List<WebElement> productsPrice;
	@FindBy(css = ".btn.btn_secondary.btn_small.cart_button") // All Remove buttons
	private List<WebElement> allRemoveBtns;
	@FindBy(css = "#continue-shopping") // Back to inventory page
	private WebElement backToInventoryPageBtn;
	@FindBy(css = ".title") // Cart page title
	private WebElement yourCartTitle;
	@FindBy(css = ".cart_item") // Whole Panel of each product
	private List<WebElement> productPanel;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public boolean compareItems(HashMap<String, String> h) {
	// Compare the chosen items between inventory page and cart page, by
	// key - product label, value - price
		HashMap<String, String> productsInTheCartPage = new HashMap<String, String>();
		productsInTheCartPage.put(getText(productsLbl.get(0)), getText(productsPrice.get(0)));
		productsInTheCartPage.put(getText(productsLbl.get(1)), getText(productsPrice.get(1)));
		return (h.equals(productsInTheCartPage));
	}

	public List<WebElement> getProductPanel() {
		return productPanel;
	}

	public List<WebElement> getAllRemoveBtns() {
		return allRemoveBtns;
	}

	public WebElement getContinueShopLink() {
		return backToInventoryPageBtn;
	}

	public WebElement getYourCartTitle() {
		return yourCartTitle;
	}

	public WebElement getBackToInventoryPageBtn() {
		return backToInventoryPageBtn;
	}

	public List<WebElement> getProductsLbl() {
		return productsLbl;
	}

	public List<WebElement> getProductsPrice() {
		return productsPrice;
	}

	public void emptyCart() { // Remove all items from the cart	
		for (WebElement el : allRemoveBtns)
			el.click();
	}

	public WebElement getCheckOutBtn() {
		return checkOutBtn;
	}

	public void clickOnCheckOutBtn() { // Click on the check-out button
		checkOutBtn.click();
	}

}
