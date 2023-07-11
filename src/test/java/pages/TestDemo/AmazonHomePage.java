/*
 *  © [2020] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pages.TestDemo;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;
import org.openqa.selenium.By;
import pages.FlightFinderPage;
//import pages.MasterPage;

/**
 * SignOnPage class
 * 
 * @author Cognizant
 */
public class AmazonHomePage extends ReusableLibrary {
	// UI Map object definitions

	// Text boxes
	private final By txtUsername = By.name("userName");

	private final By txtLogin = By.name("password");

	private final By txtSearchBox = By.id("twotabsearchtextbox");
 //Label

	private final By langLabel = By.xpath("//a[@id='icp-nav-flyout']//div");

//	private final By SamsungLabel = By.xpath("(//span[contains(text(),'samsung')])[1]");

	// links

	private final By langChangeDropDown = By.id("icp-nav-flyout");
private final By langEngRadioBtn = By.xpath("//a[@href='#switch-lang=en_AE']");


	// Buttons
	 private final By btnLogin = By.name("login");
	private final By btnSearch = By.id("nav-search-submit-button");

	/**
	 * Constructor to initialize the page
	 *
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AmazonHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		driver.get(properties.getProperty("ApplicationUrl"));
driverUtil.waitUntilPageReadyStateComplete(60);
		//driver.findElement(langChangeDropDown);
		if(driver.findElement(langLabel).getText().equalsIgnoreCase("ar"))
		{
			driverUtil.mouseOver(langChangeDropDown);
			driverUtil.waitUntilElementVisible(langEngRadioBtn,60);
			driver.findElement(langEngRadioBtn).click();
			driverUtil.waitUntilPageReadyStateComplete(60);
		}

		/*driverUtil.mouseOver(langChangeDropDown);

		driverUtil.waitUntilElementVisible(langEngRadioBtn,60);
		driver.findElement(langEngRadioBtn).click();
		driverUtil.waitUntilPageReadyStateComplete(60);*/
		if (!driver.getTitle().contains("Welcome") && !driver.getTitle().contains("Amazon.sa is here| Welcome to Amazon.sa in Saudi Online Shop")) {
		report.updateTestLog("Verify page title", "Amazon.sa home page expected, but not displayed!", Status.WARNING);

	}
}





public void BasicSearchValidate(){
String productName=dataTable.getData("General_Data","ProductName");
	driver.findElement(txtSearchBox).sendKeys(productName);
		driver.findElement(btnSearch).click();
driverUtil.waitUntilPageReadyStateComplete(60);
//driver.findElement(By.xpath("(//span[contains(text(),'samsung')])[1]))"));
if(driverUtil.objectExists(By.xpath("(//span[contains(text(),'"+productName+"')])[1]")))
{
	report.updateTestLog("Validating searchText",productName,Status.PASS);
}
else
{
	report.updateTestLog("Validating searchText",productName,Status.FAIL);
}
	}



}