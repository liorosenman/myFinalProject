package pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutStepTwo extends MenuPage {

	@FindBy(css = ".title")
	private WebElement finalOkLbl;
	@FindBy(css = "#finish")
	private WebElement finishBtn;
	@FindBy(css = "#cancel")
	private WebElement cancelBtn;
	@FindBy(css = ".inventory_item_price") // All items prices
	private List<WebElement> allPriceTags;
	@FindBy(css = ".inventory_item_name") // All products' labels
	private List<WebElement> productsLbl;
	@FindBy(css=".summary_subtotal_label") // Sum of chosen Items
	private WebElement itemTotalPrice;
	@FindBy(css=".summary_tax_label") // Tax
	private WebElement tax;
	@FindBy(css = ".summary_info_label.summary_total_label") // Total sum with tax
	private WebElement totalWithTax;

	public CheckOutStepTwo(WebDriver driver) {
		super(driver);
	}

	public WebElement getItemTotalPrice() {
		return itemTotalPrice;
	}

	public WebElement getTax() {
		return tax;
	}

	public WebElement getTotalWithTax() {
		return totalWithTax;
	}

	public Double getActualPrice_InDouble() { // Actual sum with no taxF
		String sum = getText(getItemTotalPrice());
		sum = sum.replace("Item total: $", "");
		Double sumInDouble = Double.parseDouble(sum);
		int x = 5;
		return sumInDouble;
	}

	public Double getTax_InDouble() {
		String tax = getText(getTax());
		tax = tax.replace("Tax: $", "");
		Double taxInDouble = Double.parseDouble(tax);
		return taxInDouble;
	}

	public Double getTotalWithTax_InDouble() {
		String sumWithTax = getText(getTotalWithTax());
		sumWithTax = sumWithTax.replace("Total: $", "");
		Double sumWithTax_InDouble = Double.parseDouble(sumWithTax);
		return sumWithTax_InDouble;
	}

	public Double expectedSumNoTax() {
		Double sum = 0.0;
		for (WebElement el : allPriceTags) {
			String s = getText(el);
			s = s.replace("$", "");
			Double price = Double.parseDouble(s);
			sum += price;
		}
		double z = sum;
		return sum;
	}

	public WebElement getCancelBtn() {
		return cancelBtn;
	}

	public WebElement getFinalOkLbl() {
		return finalOkLbl;
	}

	public WebElement getFinishBtn() {
		return finishBtn;
	}

	public HashMap<String, String> getTwoProducts() {
		HashMap<String, String> h = new HashMap<String, String>();
		h.put(getText(productsLbl.get(0)), getText(allPriceTags.get(0)));
		h.put(getText(productsLbl.get(1)), getText(allPriceTags.get(1)));
		return h;
	}

}
