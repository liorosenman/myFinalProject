package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutStepOne extends MenuPage {
	
	@FindBy(css="#first-name") //first name field
	private WebElement firstNameFld;
	@FindBy(css="#last-name")  //last name field
	private WebElement lastNameFld;
	@FindBy(css="#postal-code") //Zip field
	private WebElement zipFld;
	@FindBy(css="#continue") //continue button
	private WebElement continueBtn;
	@FindBy(css="#cancel") //cancel button
	private WebElement cancelBtn;
	@FindBy(css = ".error-message-container.error > h3") //error message of a specific empty field
	private WebElement errorMsg;
	

	public WebElement getErrorMsg() {
		return errorMsg;
	}

	public WebElement getCancelBtn() {
		return cancelBtn;
	}

	public CheckOutStepOne(WebDriver driver) {
		super(driver);

	}
	
	public WebElement getContinueBtn() {
		return continueBtn;
	}

	public void fillDtls(String firstname, String lastname, String zip)
	{
		fillText(firstNameFld, firstname);
//		sleep(1500);
		fillText(lastNameFld, lastname);
//		sleep(1500);
		fillText(zipFld, zip);
//		sleep(1500);
	}
	
}