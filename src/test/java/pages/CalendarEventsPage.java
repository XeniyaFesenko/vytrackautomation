package pages;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.VytrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalendarEventsPage {
	public CalendarEventsPage() {
		PageFactory.initElements(Driver.get(), this);
	}

	@FindBy(css = "[title='Create Calendar event']")
	public WebElement createCalendarEventBtn;

	@FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
	public WebElement startDate;

	@FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
	public WebElement endDate;

	@FindBy(css = "select[class='ui-datepicker-month']")
	public WebElement monthDropdown;

	@FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
	public WebElement startTime;

	@FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
	public WebElement endTime;

	@FindBy(css = "select[class='ui-timepicker-wrapper']")
	public WebElement timeDropdown;

	public void selectNextday() {
		int day = LocalDate.now().plusDays(1).getDayOfMonth();
		int month = LocalDate.now().plusDays(1).getMonth().getValue();
		VytrackUtils.waitForUIOverlay();
		startDate.click();
		Select monthSelect = new Select(monthDropdown);
		monthSelect.selectByIndex(month - 1);
		String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
		Driver.get().findElement(By.xpath(dayLocator)).click();

	}

	public void selectTodayday() {
		int day = LocalDate.now().getDayOfMonth();
		startDate.click();
		String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
		try {
			Driver.get().findElement(By.xpath(dayLocator)).click();
		} catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			Driver.get().findElement(By.xpath(dayLocator)).click();
		}
	}

	public void selectStartTime(int plusHours) {
		String time = LocalDateTime.now().plusHours(plusHours).format(DateTimeFormatter.ofPattern("h:00 a"));
		VytrackUtils.waitForUIOverlay();
		String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
		startTime.click();
		new WebDriverWait(Driver.get(), 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
		Driver.get().findElement(By.xpath(startTimeToSelect)).click();
	}

	public void selectEndTime(int plusHours) {
		String time = LocalDateTime.now().plusHours(plusHours).format(DateTimeFormatter.ofPattern("h:00 a"));
		VytrackUtils.waitForUIOverlay();
		String endTimeToSelect = "(//li[text()='" + time + "'])[2]";
		startTime.click();
		Driver.get().findElement(By.xpath(endTimeToSelect)).click();
	}

	public long diffrenceBetweenStartTimeAndEndTime() {
		LocalTime actualStartTime = LocalTime.parse(startTime.getAttribute("value"),
				DateTimeFormatter.ofPattern("h:mm a"));
		try {
			new WebDriverWait(Driver.get(), 3).until(ExpectedConditions.invisibilityOf(startTime));
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e);
		}
		LocalTime actualEndTime = LocalTime.parse(endTime.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));
		return ChronoUnit.HOURS.between(actualStartTime, actualEndTime);


	}

}
