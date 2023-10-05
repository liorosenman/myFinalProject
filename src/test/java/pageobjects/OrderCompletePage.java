package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderCompletePage extends MenuPage {

	@FindBy(css = ".complete-header")
	private WebElement orderCompleteLbl;

	public OrderCompletePage(WebDriver driver) {
		super(driver);

	}

	public WebElement getOrderCompleteLbl() {
		return orderCompleteLbl;
	}

}
