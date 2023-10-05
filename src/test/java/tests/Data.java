package tests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import freemarker.template.Configuration;

public class Data {

	String goodPass = "secret_sauce";
	Object[][] validValues = { { "Avi", "Levi", "123456" } };

	@DataProvider(name = "allValuesOk")
	public Object[][] allValuesOk() {

		return new Object[][] { { "standard_user", goodPass }, { "problem_user", goodPass },
				{ "performance_glitch_user", goodPass } };
	}

	@DataProvider(name = "wrongValuesNoEntry")
	public Object[][] wrongValuesNoEntry() {

		String goodUserName = "standard_user";
		String lockedUser = "locked_out_user";
		String badUserName = "abc123";
		String goodPass = "secret_sauce";
		String badPass = "xyz123";

		return new Object[][] { { "", goodPass },
//			{goodUserName, ""},
				{ goodUserName, badPass }, { badUserName, goodPass }, { lockedUser, goodPass } };
	}

	@DataProvider(name = "ValidPersonalDetails")
	public Object[][] validValuesForCO1() {
		return validValues;
	}

	@DataProvider(name = "AtLeastOneFieldIsEmpty")
	public Object[][] atLeastOneValueIsEmpty() {
		Object[][] invalidValues = { { "", "Levi", "123456" }, { "Avi", "", "123456" }, { "Avi", "Levi", "" },
				{ "", "", "123456" }, { "Avi", "", "" }, { "", "Levi", "" }, { "", "", "" }

		};

		return invalidValues;
	}

}
