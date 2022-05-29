package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HotelMyCampPage {

    public HotelMyCampPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//a[text()='Log in']")
    public WebElement loginButton;

    @FindBy (xpath = "//input[@class='form-control required']")
    public WebElement usernameBox;

    @FindBy (xpath = "//span[text()='ListOfUsers']")
    public WebElement listOfUsersText;

    @FindBy (xpath = "//span[text()='Try again please']")
    public WebElement tryAgainText;





}
