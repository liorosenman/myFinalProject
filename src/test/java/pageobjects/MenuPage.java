package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends Basepage {

	@FindBy(css = "#react-burger-menu-btn")
	private WebElement burgerBtn;
	@FindBy(css = "#inventory_sidebar_link")
	private WebElement allItemsBtn;
	@FindBy(css = "#logout_sidebar_link")
	private WebElement logOutBtn;	
	@FindBy(css = ".shopping_cart_link")
	private WebElement cartBtn;
	
	
	public WebElement getBurgerBtn() {
		return burgerBtn;
	}


	public WebElement getLogOutBtn() {
		return logOutBtn;
	}


	public WebElement getCartBtn() {
		return cartBtn;
	}


	public MenuPage(WebDriver driver)
	{
		super(driver);
	}
}
