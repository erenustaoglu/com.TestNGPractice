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

public class PositiveTest {

    @Test
    public void positiveLoginTest() throws IOException {
        //1 ) Bir Class olustur : PositiveTest
        //2) Bir test method olustur positiveLoginTest()
        //https://www.hotelmycamp.com/ adresine git
        HotelMyCampPage hotelMyCampPage = new HotelMyCampPage();
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCampUrl"));
        //login butonuna bas
        hotelMyCampPage.loginButton.click();
        //test data username: manager ,
        //test data password : Manager1!
        //Degerleri girildiginde sayfaya basarili sekilde girilebildigini test et
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(Driver.getDriver());
        hotelMyCampPage.usernameBox.sendKeys(ConfigReader.getProperty("correctUsername"));
        actions.sendKeys(Keys.TAB)
                .sendKeys(ConfigReader.getProperty("correctPassword"))
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();
        softAssert.assertTrue(hotelMyCampPage.listOfUsersText.isDisplayed(),"Giris yapilamadi");
        softAssert.assertAll();

        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        String tarih = date.format(dtf);

        File tumSayfaResim = new File("target/ekranGoruntuleri/anaSayfa" + tarih + ".jpeg");

        File geciciDosya = ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(geciciDosya, tumSayfaResim);

        Driver.getDriver().close();
    }
}
