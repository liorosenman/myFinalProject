package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class LoginPage extends Basepage {

	@FindBy(css = "#user-name")
	private WebElement userNameFld;
	@FindBy(css = "#password")
	private WebElement passFld;
	@FindBy(css = "#login-button")
	private WebElement loginBtn;
	@FindBy(css = ".error-message-container.error > h3")
	private WebElement errorMsg;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getErrorMsg() {
		return errorMsg;
	}

	public WebElement getUserNameFld() {
		return userNameFld;
	}

	public WebElement getPassFld() {
		return passFld;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void loginMethod(String username, String password) {
		fillText(userNameFld, username);
		fillText(passFld, password);
		loginBtn.click();
	}

	public void validLogin() {
		fillText(userNameFld, Utils.readProperty("username"));
		fillText(passFld, Utils.readProperty("password"));
		loginBtn.click();
	}

}
