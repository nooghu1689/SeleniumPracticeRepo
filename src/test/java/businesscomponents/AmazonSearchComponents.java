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
package businesscomponents;

import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;
import org.openqa.selenium.WebElement;
import pages.BookFlightPage;
import pages.FlightConfirmationPage;
import pages.FlightFinderPage;
import pages.SelectFlightPage;
import pages.TestDemo.AmazonHomePage;

/**
 * Class for storing business components related to the flight reservation
 * functionality
 * 
 * @author Cognizant
 */
public class AmazonSearchComponents extends ReusableLibrary {
	private static final String FLIGHTS_DATA = "Flights_Data";

	/**
	 * Constructor to initialize the component library
	 *
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AmazonSearchComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void findFlights() {
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.findFlights();
	}

	public void selectFlights() {
		SelectFlightPage selectFlightPage = new SelectFlightPage(scriptHelper);
		selectFlightPage.selectFlights();
	}

	public void bookFlights() {
		BookFlightPage bookFlightPage = new BookFlightPage(scriptHelper);
		bookFlightPage.bookFlights();
	}

	public void verifyBooking() {
		if (driverUtil.isTextPresent("^[\\s\\S]*Your itinerary has been booked![\\s\\S]*$")) {
			report.updateTestLog("Verify Booking", "Tickets booked successfully", Status.PASS);

			WebElement flightConfirmation = driver.findElement(FlightConfirmationPage.lblConfirmationMessage);

			String flightConfirmationNumber = flightConfirmation.getText();
			flightConfirmationNumber = flightConfirmationNumber.split("#")[1].trim();
			dataTable.putData(FLIGHTS_DATA, "FlightConfirmationNumber", flightConfirmationNumber);
			report.updateTestLog("Flight Confirmation", "The flight confirmation number is " + flightConfirmationNumber,
					Status.SCREENSHOT);
		} else {
			report.updateTestLog("Verify Booking", "Tickets booking failed", Status.FAIL);
		}
	}

	public void backToFlights() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(scriptHelper);
		flightConfirmationPage.backToFlights();
	}
	public void searchProduct(){

		AmazonHomePage AmazonHome = new  AmazonHomePage(scriptHelper);
		AmazonHome.BasicSearchValidate();
	}


}