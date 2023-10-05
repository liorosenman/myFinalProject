package pageobjects;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends MenuPage {

	@FindBy(css = ".inventory_details_name.large_size") // Item's name
	private WebElement itemName;
	@FindBy(css = ".inventory_details_desc.large_size") // Item's description
	private WebElement itemDesc;
	@FindBy(css = ".inventory_details_price") // Item's price
	private WebElement itemPrice;
	@FindBy(css = "#add-to-cart-sauce-labs-backpack") // Add button of the specific item
	private WebElement addButton;
	@FindBy(xpath = "//div[@class='inventory_details_desc_container']//button") // The Add-to-cart\Remove button, no
																				// difference of status
	private WebElement addOrRemoveBtn;
	@FindBy(css = "#back-to-products") // link back to inventory page
	private WebElement backToProductsBtn;

	public WebElement getBackToProductsBtn() {
		return backToProductsBtn;
	}

	public ItemPage(WebDriver driver) {
		super(driver);

	}

	public WebElement getAddButton() {
		return addButton;
	}

	public boolean compareItemDetails(String[] relevantDetails) // Compare Item's details between inventory and Item //
																// // page
	{
		String[] itemPgDetails = { itemName.getText(), itemDesc.getText(), itemPrice.getText(),
				addOrRemoveBtn.getText() };
		return Arrays.equals(relevantDetails, itemPgDetails);
	}

}
