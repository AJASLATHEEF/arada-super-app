import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import java.time.LocalDate as LocalDate
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.MobileElement as MobileElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testdata.TestDataFactory


String tomorw

Date todaysDate = new Date()

def formattedDate = todaysDate.format('yyyy/MM/dd')

//Fetch current date
DateTimeFormatter screenFormat = DateTimeFormatter.ofPattern('yyyy/MM/dd')

DateTimeFormatter screenFormat1 = DateTimeFormatter.ofPattern('dd')

LocalDate localDate = LocalDate.now()

def gTomorrowDate = localDate.plusDays(1).format(screenFormat)

def next_day = localDate.plusDays(1).format(screenFormat1)

String tomorw1 = next_day.toString()

if (tomorw1.charAt(0) == '0') {
	// If the first digit contains zero, remove the zero
	tomorw = tomorw1.substring(1)
} else {
	println("First digit does not contain zero")
	tomorw = tomorw1
}

println('next day is ' + tomorw)

println(formattedDate)

Mobile.startApplication('C:\\Users\\ajas\\Downloads\\app-staging-release.apk', true)

WebUI.delay(30)

Mobile.takeScreenshotAsCheckpoint('Launch Screen')

Mobile.takeScreenshot()

//Enter username
Mobile.sendKeys(findTestObject('Search_Property/Email Text Field'), GlobalVariable.Username)

WebUI.delay(5)

//Enter Password
Mobile.sendKeys(findTestObject('Search_Property/Password Text Field'), GlobalVariable.Valid_Password)

Mobile.delay(5)

//Click login Button
Mobile.tap(findTestObject('Invalid Password/Login Button'), 0)

Mobile.delay(30)

Mobile.takeScreenshotAsCheckpoint('After Login')

Mobile.takeScreenshot()

Mobile.delay(20)

Mobile.getText(findTestObject('Search_Property/Welcome title'), 0)

Mobile.delay(10)

Mobile.tap(findTestObject('Search_Property/Explore Property Tile'), 0)

Mobile.delay(10)

Mobile.tap(findTestObject('Search_Property/Masaar Tile'), 5)

//Click First search result
Mobile.tap(findTestObject('Search_Property/Click First Search Result 1'), 0)

//Sroll to 'Book a Visit' buttton
Mobile.scrollToText('Book a Visit')

Mobile.switchToNative()

WebUI.delay(5)

//Click book a visit button
Mobile.tap(findTestObject('Object Repository/Search_Property/Book a Visit button'), 0)

WebUI.delay(8)

//Select visit date
Mobile.takeScreenshotAsCheckpoint('Select date')

Mobile.takeScreenshot()

Mobile.delay(15)

AppiumDriver<?> driver = MobileDriverFactory.getDriver()
MobileElement el = driver.findElement(By.xpath(('//android.widget.TextView[@text=\'' + tomorw) + '\']'))

if (el.getText().equals(tomorw)) {
    el.click()
}


Mobile.delay(15)

//Click time

List<MobileElement> times = driver.findElements(By.xpath(("//android.widget.TextView[@text='Pick a time']/following-sibling::android.view.ViewGroup")))

int time = times.size()

Random random = new Random()
int randomNumber = random.nextInt(time-1)

String Selected_date= times.get(randomNumber).getText()
times.get(randomNumber).click()

WebUI.delay(5)

Mobile.switchToNative()

Mobile.delay(10)

//Click schedule button
Mobile.tap(findTestObject('Object Repository/Search_Property/Schedule Button'), 0)

Mobile.delay(20)

String alert = Mobile.getText(findTestObject('Search_Property/Booking Successful alert message'), 0)

Mobile.takeScreenshotAsCheckpoint('After Schedule button click')

Mobile.takeScreenshot()

if (alert.contains(gTomorrowDate)&& alert.contains(Selected_date)) {
    println('Alert message is matching' // Log a failure message and stop the execution
        )
} else {
    throw new RuntimeException('Automation stopped because the condition is not satisfied.')
}

Mobile.closeApplication()



