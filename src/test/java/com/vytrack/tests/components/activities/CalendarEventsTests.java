package com.vytrack.tests.components.activities;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VytrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalendarEventsPage;
import pages.LoginPage;

public class CalendarEventsTests extends TestBase {

	@Test
	public void verifyDateAutoJustTest() {
		LoginPage loginPage = new LoginPage();

		CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
		loginPage.login("user4", "UserUser123");

		VytrackUtils.selectMenuOption(Driver.get(), "Activities", "Calendar Events");
		calendarEventsPage.createCalendarEventBtn.click();
		VytrackUtils.waitForUIOverlay();

		calendarEventsPage.selectNextday();
		Assert.assertEquals(calendarEventsPage.startDate.getAttribute("value"),
				calendarEventsPage.endDate.getAttribute("value"));

		calendarEventsPage.selectTodayday();
		Assert.assertEquals(calendarEventsPage.startDate.getAttribute("value"),
				calendarEventsPage.endDate.getAttribute("value"));

	}

	@Test
	public void verifyDateAutoJustTest2() {
		LoginPage loginPage = new LoginPage();
		CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
		loginPage.login("user4", "UserUser123");
		VytrackUtils.selectMenuOption(Driver.get(), "Activities", "Calendar Events");
		calendarEventsPage.createCalendarEventBtn.click();
		VytrackUtils.waitForUIOverlay();
		calendarEventsPage.selectStartTime(0);

		VytrackUtils.waitForUIOverlay();
		Assert.assertEquals(1, calendarEventsPage.diffrenceBetweenStartTimeAndEndTime());

	}
}
