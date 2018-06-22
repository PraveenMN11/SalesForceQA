package com.test.salesforce.resources;

import java.util.HashMap;

import com.test.salesforce.dataobjects.LoginUserData;
import com.test.webdriver.utils.PropertyFileReader;


public class User {

	private static HashMap<String, LoginUserData> users = new HashMap<String, LoginUserData>();

	public static LoginUserData SYSADMIN = initUser("SysAdmin");
	public static LoginUserData ANSELLSALESMED= initUser("AnsellSalesMed");
	public static LoginUserData ANSELLSALESIND = initUser("AnsellSalesInd");
	public static LoginUserData ANSELLSALESMED_IND = initUser("AnsellSalesMed_Ind");
	public static LoginUserData ANSELLCUSTOMERSERVICE = initUser("AnsellCustomerService");
	public static LoginUserData ANSELLINTEGRATION = initUser("AnsellIntegration");
	public static LoginUserData ANSELLMARKETING = initUser("AnsellMarketing");

	private static LoginUserData initUser(String propertyRef) {
		LoginUserData userData = new LoginUserData();
		userData.setUserName(PropertyFileReader.getProperty(propertyRef + "UserName"));
		userData.setPassword(PropertyFileReader.getProperty(propertyRef + "Password"));
		users.put(propertyRef, userData);
		return userData;
	}

	public static LoginUserData getUser(String userType) {
		LoginUserData user = null;
		user = users.get(userType);
		return user;
	}

}
