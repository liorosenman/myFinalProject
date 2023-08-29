package tests;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import freemarker.template.Configuration;


public class Data {
	
	
	String goodPass = "secret_sauce";
	Object[][] validValues = {{"Avi", "Levi", "123456"}};
	
	@DataProvider(name = "allValuesOk")
	public Object[][] allValuesOk()
	{
		
		return new Object[][] {
			{"standard_user", goodPass},
			{"problem_user", goodPass},
			{"performance_glitch_user", goodPass}};
	}
	

	@DataProvider(name = "wrongValuesNoEntry")
	public Object[][] wrongValuesNoEntry()
	{
//		Properties props = new Properties();
//		props.load(new FileInputStream("Configuration.properties"));
//		String goodPass = props.getProperty("onlyGoodPass");
		
		String goodUserName = "standard_user";
		String lockedUser = "locked_out_user";
		String badUserName = "abc123";
		String goodPass = "secret_sauce";
		String badPass = "xyz123";
		
		return new Object[][] {
			{"", goodPass},
//			{goodUserName, ""},
			{goodUserName, badPass},
			{badUserName,goodPass},
			{lockedUser, goodPass}
		};
			
		}
	
	@DataProvider (name = "ValidPersonalDetails")
	public Object[][] validValuesForCO1()
	{
		return validValues;
	}
	
	@DataProvider (name = "AtLeastOneFieldIsEmpty")
	public Object[][] atLeastOneValueIsEmpty()
	{
		Object[][] invalidValues = {
				{"", "Levi", "123456"},
				{"Avi", "", "123456"},
				{"Avi", "Levi", ""},
				{"","","123456"},
				{"Avi", "", ""},
				{"", "Levi", ""},
				{"","",""}
		
		};
		
		return invalidValues;
	}

	/*
	
	@DataProvider (name = "InValidPersonalDetails")
	public List<Object[]> invalidValuesForCO1()
	{
		
		List<Object[]> dataToSend = new ArrayList<>();
		List<Object[]> l = new ArrayList<>();
		Object[] arrayofInValidFirstNames = {"avi3", "_avi", ""};
		Object[] arrayofInValidLastNames = {"levi3", "_levi", ""};
		Object[] arrayofInValidZip = {"a123", "123@", ""};
		l.add(arrayofInValidFirstNames);
		l.add(arrayofInValidLastNames);
		l.add(arrayofInValidZip);
		
		for (int i = 0; i < l.size(); i++)
		{
			for (Object ob : l.get(i))
			{
				Object[] a = new Object[l.size()];
				a[i] = ob.toString();
				for (int j = 0; j < l.size(); i++)
				{
					if (j != i)
						a[j] = validValues[j];
				}
				
				dataToSend.add(a);
			}
			
			
		}
			
		return dataToSend; 

	}
	*/
//	@DataProvider (name = "someDetailsAreEmpty")
//	public List<Object[]> emptyFieldsOptions()
//	{
//		List<Object[]> dataToSend = new ArrayList<>();
//		
//	}

}
	
	
	

