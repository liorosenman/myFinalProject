package pageobjects;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basepage {

	WebDriver driver;
	WebDriverWait wait;
	
	
	@FindBy(css="#react-burger-menu-btn")
	private WebElement burgerMenuBtn;
	@FindBy(css="#logout_sidebar_link")
	private WebElement logOutBtn;
	@FindBy(css=".shopping_cart_link")
	private WebElement cartBtn; //appears in every page except for log-in page.
	@FindBy(css = ".shopping_cart_badge")
	private WebElement quantityBadge;
	
	
	public Basepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getBurgerMenuBtn() {
		return burgerMenuBtn;
	}

	public WebElement getLogOutBtn() {
		return logOutBtn;
	}

	
	public WebElement getCartBtn() {
		return cartBtn;
	}

	public double convertStrToDouble(String p)
	{
		String p1 = p.substring(1);
		p1.replace("$", "");
		double price = Double.parseDouble(p1);
		return price;
	}
	

	public void fillText(WebElement el,String text) {
		el.clear();
		el.sendKeys(text);
	}

	public void click(WebElement el) {
		el.click();
	}

	public String getText(WebElement el) {
		return el.getText();
	}

	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyElementFullyLoaded(WebElement el)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(el));
				
	}
	
	public WebElement getQuantityBadge() {
		return quantityBadge;
	}

	public void logOut()
	{
		burgerMenuBtn.click();
		sleep(2000);
		logOutBtn.click();	
	}
	
	public boolean isThisTheQuantityInCart(int expectedNumber)
	{
		int actual = Integer.parseInt(getText(quantityBadge));
		return expectedNumber == actual ;
	}
	
}
