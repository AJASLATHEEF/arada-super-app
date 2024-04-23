import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileAbstractKeyword as MobileAbstractKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


Mobile.startApplication('C:\\Users\\ajas\\Downloads\\app-staging-release.apk', true)

Mobile.delay(20)

Mobile.takeScreenshotAsCheckpoint('Launch Screen')

Mobile.takeScreenshot()

//Enter username
Mobile.sendKeys(findTestObject('Invalid Password/Email Text Field'), GlobalVariable.Username)
//Enter invalid password
Mobile.sendKeys(findTestObject('Invalid Password/Password Field'), GlobalVariable.InValid)

Mobile.delay(5)

//click login button
Mobile.tap(findTestObject('Invalid Password/Login Button'), 0)


//Get alert message
String alert = Mobile.getText(findTestObject('Invalid Password/Invalid Alert Message'), 0)

Mobile.takeScreenshot()

Mobile.takeScreenshotAsCheckpoint('Alert Screen')

//Verify alert message

if (alert.equals('Invalid username or password')) {
    println('Error is matching' // Log a failure message and stop the execution
        )
} else {
    throw new RuntimeException('Automation stopped because the condition is not satisfied.')
}


Mobile.closeApplication()

