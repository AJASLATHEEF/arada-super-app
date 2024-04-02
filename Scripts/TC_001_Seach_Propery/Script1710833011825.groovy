import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import java.time.LocalDate as LocalDate
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import org.openqa.selenium.By as By
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
}

println('next day is ' + next_day)

println(formattedDate)

Mobile.startApplication('C:\\Users\\ajas\\Downloads\\app-staging-release.apk', true)

WebUI.delay(25)

Mobile.takeScreenshotAsCheckpoint('Launch Screen')

Mobile.takeScreenshot()

Mobile.sendKeys(findTestObject('Search_Property/Email Text Field'), GlobalVariable.Username)

WebUI.delay(5)

Mobile.sendKeys(findTestObject('Search_Property/Password Text Field'), GlobalVariable.Valid_Password)

Mobile.delay(5)

Mobile.tap(findTestObject('Invalid Password/Login Button'), 0)

Mobile.delay(30)

Mobile.takeScreenshotAsCheckpoint('After Login')

Mobile.takeScreenshot()

Mobile.delay(13)

Mobile.getText(findTestObject('Search_Property/Welcome title'), 0)

Mobile.delay(10)

Mobile.tap(findTestObject('Search_Property/Explore Property Tile'), 0)

Mobile.delay(10)

Mobile.tap(findTestObject('Search_Property/Sort Icon'), 0)

Mobile.delay(8)

Mobile.takeScreenshotAsCheckpoint('After Click Sort Icon')

Mobile.takeScreenshot()

Mobile.delay(8)

Mobile.tap(findTestObject('Search_Property/Masaar Tile'), 0)

Mobile.delay(8)

Mobile.switchToNative()

Mobile.tap(findTestObject('Search_Property/Select Property Type'), 0)

Mobile.delay(7)

Mobile.switchToNative()

Mobile.tap(findTestObject('Search_Property/Select Cluster Drop'), 0)

Mobile.delay(7)

Mobile.tap(findTestObject('Search_Property/Robinia Cluster Type Drop Value'), 0)

Mobile.delay(7)

Mobile.tap(findTestObject('Search_Property/Select Project Drop'), 0)

Mobile.delay(3)

Mobile.tap(findTestObject('Search_Property/Robinia Project Type Drop Value'), 0)

Mobile.scrollToText('Select Bedroom')

Mobile.tap(findTestObject('Search_Property/Click Bedroom Count 4'), 0)

Mobile.scrollToText('Community Type')

Mobile.scrollToText('View Result')

Mobile.delay(8)

String bounds = Mobile.getAttribute(findTestObject('Object Repository/Search_Property/View Result Button'), 'bounds', 10)


// Extract integers from the string
List<Integer> integers = bounds.findAll(/\d+/).collect { it as Integer }

// Print the extracted integers
println("Extracted Integers: " + integers)

String x1 = integers[2]  
String y1 = integers[-1] 

int x2 = Integer.parseInt(x1)
int y2 = Integer.parseInt(y1)

int x = x2-79
int y =y2-18

println('x value is '+x)
println('y value is '+y)

Mobile.tapAtPosition(364, 1299)

Mobile.takeScreenshotAsCheckpoint('After Click Search button')

Mobile.takeScreenshot()

//Click First search result
Mobile.tap(findTestObject('Search_Property/Click First Search Result'), 0)

//Sroll to 'Book a Visit' buttton
Mobile.scrollToText('Book a Visit')

Mobile.switchToNative()

WebUI.delay(5)

Mobile.tapAtPosition(368, 1353)

//Mobile.tap(findTestObject('Object Repository/Search_Property/Book a Visit button'), 4)

WebUI.delay(8)

Mobile.takeScreenshotAsCheckpoint('Select date')

Mobile.takeScreenshot()


AppiumDriver<?> driver = MobileDriverFactory.getDriver()
MobileElement el = driver.findElement(By.xpath(('//android.widget.TextView[@text=\'' + tomorw) + '\']'))

if (el.getText().equals(tomorw)) {
    el.click()
}

Mobile.tap(findTestObject('Search_Property/Click Time'), 0)

WebUI.delay(5)

Mobile.switchToNative()

WebUI.delay(8)

//Mobile.tap(findTestObject('Object Repository/Search_Property/Schedule Button'), 0)
Mobile.tapAtPosition(367, 1353)

String alert = Mobile.getText(findTestObject('Search_Property/Booking Successful alert message'), 0)

Mobile.takeScreenshotAsCheckpoint('After Schedule button click')

Mobile.takeScreenshot()

if (alert.contains(gTomorrowDate)) {
    println('Alert message is matching' // Log a failure message and stop the execution
        )
} else {
    throw new RuntimeException('Automation stopped because the condition is not satisfied.')
}

Mobile.closeApplication()



