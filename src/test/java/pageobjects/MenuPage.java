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
	
	//////////////////////////////////////////////////////

	@FindBy(css = ".shopping_cart_badge")
	private WebElement quantityBadge;
	
	public WebElement getQuantityBadge() {
		return quantityBadge;
	}

	public WebElement getBurgerBtn() {
		return burgerBtn;
	}

	public WebElement getLogOutBtn() {
		return logOutBtn;
	}

	public WebElement getCartBtn() {
		return cartBtn;
	}

	public MenuPage(WebDriver driver) {
		super(driver);
	}
	
	public void logOut() {
		burgerBtn.click();
		sleep(2000);
		logOutBtn.click();
	}

	public boolean isThisTheQuantityInCart(int expectedNumber) {
		int actual = Integer.parseInt(getText(quantityBadge));
		return expectedNumber == actual;
	}
}
