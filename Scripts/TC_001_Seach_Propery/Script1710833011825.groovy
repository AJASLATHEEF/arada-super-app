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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile


Date todaysDate = new Date()

def formattedDate = todaysDate.format('yyyy/MM/dd')

DateTimeFormatter screenFormat = DateTimeFormatter.ofPattern('yyyy/MM/dd')

DateTimeFormatter screenFormat1 = DateTimeFormatter.ofPattern('dd')

LocalDate localDate = LocalDate.now()

def gTomorrowDate = localDate.plusDays(1).format(screenFormat)

def next_day = localDate.plusDays(1).format(screenFormat1)

String tomorw = next_day.toString()

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

Mobile.delay(13)

Mobile.takeScreenshotAsCheckpoint('After Login')

Mobile.takeScreenshot()

Mobile.getText(findTestObject('Search_Property/Welcome title'), 0)

Mobile.tap(findTestObject('Search_Property/Explore Property Tile'), 0)

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

WebUI.delay(5)


try {
	
	//Click View Result Button
	Mobile.tapAtPosition(364, 1299)
	Mobile.getText(findTestObject('Object Repository/Search_Property/Reset Filter Button'), 4)
	
} catch (Exception e) {
	try {
		Mobile.tapAtPosition(584, 1155)
		Mobile.getText(findTestObject('Object Repository/Search_Property/Reset Filter Button'), 4)
	} catch (Exception e1) {
		try {
			Mobile.tapAtPosition(367, 1391)
			Mobile.getText(findTestObject('Object Repository/Search_Property/Reset Filter Button'), 4)
		} catch (Exception e2) {s
			try {
				Mobile.tapAtPosition(361, 1068)
				Mobile.getText(findTestObject('Object Repository/Search_Property/Reset Filter Button'), 4)
			} catch (Exception e3) {
				
			}
		}
	}
}



Mobile.delay(5)

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

