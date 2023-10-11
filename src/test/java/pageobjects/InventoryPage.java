package pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Description;

public class InventoryPage extends MenuPage {

	
	@FindBy(xpath="//span[@class='title']") //Title of page
	private WebElement productsLbl;
	@FindBy(xpath = "(//div[@class='inventory_item_name'])[1]") // Title and link of the first item
	private WebElement product1Title;
	@FindBy(xpath = "(//div[@class='inventory_item_price'])[1]") // First item in the inventory
	private WebElement product1Price;
	@FindBy(xpath="//div[@class='inventory_item_name']") // All products' labels
	private List<WebElement> items;
	@FindBy(xpath="//select[@class='product_sort_container']") // Sort options
	private WebElement sortSlc;
	@FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']") //All add-item buttons
	private List<WebElement> addItemBtns;
	@FindBy(xpath = "//button[@class='btn btn_secondary btn_small btn_inventory']") //All remove-item buttons
	private List<WebElement> removeItemBtns;
	@FindBy(xpath = "//div[@class='inventory_item_price']") //All price tags
	private List<WebElement> allPriceTags;
	@FindBy(xpath = "//button[@class=\"btn btn_secondary btn_small btn_inventory\"]/parent::div/div") // Prices of all selected items
	private List<WebElement> selectedItemsPrices;
	@FindBy(xpath = "//button[@class=\"btn btn_secondary btn_small btn_inventory\"]/ancestor::div[2]/child::div[1]/a") // Names of all selected items
	private List<WebElement> selectedItemsNames;
	@FindBy(xpath = "//div[@class='inventory_item_desc']")
	private List<WebElement> allItemsDescription; // List of the descriptions of the products
	@FindBy(xpath = "(//div[@class='inventory_item_description']//button)[1]")
	private WebElement firstBtn;

	private static String addBtnText = "Add to cart"; // What is written on the product when it is not chosen
	private static String removeBtnText = "Remove"; // What is written on the product when it is chosen
	private static int itemsNum = 6; // Constant number of products in the shop

	public InventoryPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getFirstBtn() { // Get the first button (Add to cart/Remove button)
		return firstBtn;
	}

	public List<WebElement> getAllItemsDescription() { // List of all items' descriptions
		return allItemsDescription;
	}

	public HashMap<String, String> getTwoProducts(){ // return a hashmap of two selected items, key - name, value - price													// // tag
		HashMap<String, String> h = new HashMap<String, String>();
		h.put(getText(selectedItemsNames.get(0)), getText(selectedItemsPrices.get(0)));
		h.put(getText(selectedItemsNames.get(1)), getText(selectedItemsPrices.get(1)));
		return h;
	}

	public List<WebElement> getRemoveItemBtns() {
		return removeItemBtns;
	}

	public List<WebElement> getAllPriceTags() {
		return allPriceTags;
	}

	public void clickOnItemByIndex(int index) { // Click on item's name by index
		items.get(index).click();
	}

	public WebElement getProduct1Title() {
		return product1Title;
	}

	public String getTextFromTitle() {
		return getText(product1Title);
	}

	public WebElement getProduct1Price() {
		return product1Price;
	}

	public void turnAllBtnsToAdd() { // Every product will have a "Add to cart" status
		for (WebElement el : removeItemBtns)
			el.click();
	}

	public void turnAllBtnsToRemove() {// Every product will have a "Remove" status
		for (WebElement el : removeItemBtns)
			if (getText(el).equals(addBtnText))
				click(el);
	}

	public String getAddBtnText() {
		return addBtnText;
	}

	public String getRemoveBtnText() {
		return removeBtnText;
	}

	public List<WebElement> getItems() {
		return items;
	}

	public List<WebElement> getAddItemBtns() {
		return addItemBtns;
	}

	public static int getItemsNum() {
		return itemsNum;
	}

	public WebElement getProductsLbl() {
		return productsLbl;
	}

	public Select getSortSlc() {
		Select s = new Select(sortSlc);
		return s;
	}

	public void clickByIndex(int index) { // An "Add to cart" button will be clicked by its index in the addItemBtns list
		addItemBtns.get(index).click();
	}

}
