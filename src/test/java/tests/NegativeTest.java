package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HotelMyCampPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NegativeTest {

    @Test
    public void negativeLoginTest() throws IOException {
        //1 ) Bir Class olustur : NegativeTest
        //2) Bir test method olustur negativeLoginTest()
        //https://www.hotelmycamp.com/ adresine git
        HotelMyCampPage hotelMyCampPage = new HotelMyCampPage();
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCampUrl"));
        //login butonuna bas
        hotelMyCampPage.loginButton.click();
        //test data username: manager1 ,
        //test data password : manager1!
        //Degerleri girildiginde sayfaya girilemedigini test et
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(Driver.getDriver());
        hotelMyCampPage.usernameBox.sendKeys(ConfigReader.getProperty("wrongUsername"));
        actions.sendKeys(Keys.TAB)
                .sendKeys(ConfigReader.getProperty("wrongPassword"))
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();
        softAssert.assertTrue(hotelMyCampPage.tryAgainText.isDisplayed(),"Tekrar deneyin yazisi gozukmuyor");
        softAssert.assertAll();

        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        String tarih = date.format(dtf);

        File tumSayfaResim = new File("target/ekranGoruntuleri/girisYapilamadiEkrani" + tarih + ".jpeg");

        File geciciDosya = ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(geciciDosya, tumSayfaResim);

        Driver.getDriver().close();
    }
}
