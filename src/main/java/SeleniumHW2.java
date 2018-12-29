import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class SeleniumHW2 {
    protected static WebDriver driver;

 @BeforeMethod

    public static void beforemethod () {
     System.setProperty("webdriver.chrome.driver", "src\\browserdriver\\chromedriver.exe");
     driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
     driver.manage().window().fullscreen();
     driver.get("https://demo.nopcommerce.com/");
 }
  @Test
    public void Register() {
      driver.findElement(By.linkText("Register")).click();  //click on register
      driver.findElement(By.xpath("//input[@id=\"gender-female\"]")).click();  //select gender
      driver.findElement(By.name("FirstName")).sendKeys("lotus");  // first name
      driver.findElement(By.id("LastName")).sendKeys("rana");  //last name
      driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]")).sendKeys("25");
      driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")).sendKeys("January");
      driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]")).sendKeys("1990");
      DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
      Date date = new Date();
      String date1 = dateFormat.format(date);
      driver.findElement(By.name("Email")).sendKeys("lotusrana" + date1 + "12@mail.com");
      driver.findElement(By.id("Company")).sendKeys("Inspiring next");
      driver.findElement(By.id("Newsletter")).click();
      driver.findElement(By.name("Password")).sendKeys("refugee");
      driver.findElement(By.name("ConfirmPassword")).sendKeys("refugee");
      driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();
      String actualresult1 = driver.findElement(By.xpath("//div[@class='result']")).getText();
      Assert.assertEquals("Registration completed", "Your registration completed", actualresult1);
     // driver.close();
  }
 @Test
 public void sendmail(){  // registred user should send a mail successfully
     driver.findElement(By.linkText("Log in")).click();
     driver.findElement(By.xpath("//input[@class=\"email\"]")).sendKeys("lotusrana12@mail.com");
     driver.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("refugee");
     driver.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
     driver.findElement(By.linkText("Electronics")).click();
     driver.findElement(By.linkText("Cell phones")).click();
     driver.findElement(By.linkText("Nokia Lumia 1020")).click();
     driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
     driver.findElement(By.xpath("//input[@id=\"FriendEmail\"]")).sendKeys("kusum.thakur@outlook.com");
     //driver.findElement(By.xpath("//input[@id=\"YourEmailAddress\"]")).sendKeys("lotusrana12@mail.com");
     driver.findElement(By.xpath("//textarea[@id=\"PersonalMessage\"]")).sendKeys("check out this");
     driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
     String expectedresult ="Your message has been sent.";
     String actualmessage = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
     Assert.assertEquals("Comparing result",expectedresult,actualmessage);
    // driver.findElement(By.xpath("//*[@href=\"/logout\"]")).click();
  //   driver.close();
 }

 @Test
    public void MailToFriend(){ //unregistred user should not be able to send a mail
     driver.findElement(By.linkText("Jewelry")).click();
     driver.findElement(By.linkText("Flower Girl Bracelet")).click();
     driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
     driver.findElement(By.xpath("//*[@id=\"FriendEmail\"]")).sendKeys("rajpatel@mail.com");
     driver.findElement(By.xpath("//*[@id=\"YourEmailAddress\"]")).sendKeys("kajpatel@mail.com");
     driver.findElement(By.xpath("//textarea[@id=\"PersonalMessage\"]")).sendKeys("hi");
     driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
     String expectedresult ="Only registered customers can use email a friend feature";
     String actualresult = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]")).getText();
     Assert.assertEquals("comparing result",expectedresult,actualresult);
 }
@Test
    public void TermAndConditiom(){  //user should accepet the term and condition
    driver.findElement(By.linkText("Books")).click();
    driver.findElement(By.linkText("First Prize Pies")).click();
    driver.findElement(By.xpath("//input[@id=\"add-to-cart-button-38\"]")).click();
    driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"terms-of-service-warning-box\"]/p")).click();
    driver.findElement(By.xpath("//button[@title=\"Close\"]")).click();
    driver.findElement(By.xpath("//input[@id=\"termsofservice\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    boolean expectedresult = true;
    boolean actualresult = false;
    if (driver.findElement(By.tagName("termsofservice")).isSelected()){
    //(driver.findElement(By.xpath(//input[@id="termsofservice"])).isSelected()){
        actualresult=true;
    }
    Assert.assertEquals("comparing",expectedresult,actualresult);

    }

    @Test
    public void buyproduct(){ // user should able to buy product successfully
   // driver.findElement(By.linkText("Log in")).click();
    click_element(By.linkText("Log in"));
    driver.findElement(By.xpath("//input[@class=\"email\"]")).sendKeys("lotusrana12@mail.com");
    driver.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("refugee");
    driver.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
    driver.findElement(By.linkText("Electronics")).click();
    driver.findElement(By.linkText("Cell phones")).click();
    driver.findElement(By.partialLinkText("HTC One M8")).click();
    driver.findElement(By.xpath("//*[@id=\"add-to-cart-button-18\"]")).click();
    driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).click();
    driver.findElement(By.xpath("//input[@class=\"qty-input\"]")).clear();
    driver.findElement(By.xpath("//input[@class=\"qty-input\"]")).sendKeys("1");
    driver.findElement(By.xpath("//input[@class=\"button-2 update-cart-button\"]")).click();
    driver.findElement(By.xpath("//input[@id=\"termsofservice\"]")).click();
    driver.findElement(By.xpath("//button[@id=\"checkout\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/input")).click();
    driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]")).click();
    driver.findElement(By.xpath("//input[@class=\"button-1 shipping-method-next-step-button\"]")).click();
    driver.findElement(By.xpath("//input[@id=\"paymentmethod_1\"]")).click();
    driver.findElement(By.xpath("//input[@class=\"button-1 payment-method-next-step-button\"]")).click();
    driver.findElement(By.xpath("//input[@id=\"CardholderName\"]")).sendKeys("suresh");
    driver.findElement(By.xpath("//input[@id=\"CardNumber\"]")).sendKeys("4111 1111 1111 1111");
    driver.findElement(By.xpath("//select[@id=\"ExpireMonth\"]")).sendKeys("20");
    driver.findElement(By.xpath("//select[@id=\"ExpireYear\"]")).sendKeys("2011");
    driver.findElement(By.xpath("//input[@id=\"CardCode\"]")).sendKeys("737");
    driver.findElement(By.xpath("//input[@class=\"button-1 payment-info-next-step-button\"]")).click();
    driver.findElement(By.xpath("//input[@value=\"Confirm\"]")).click();
   // driver.findElement(By.partialLinkText("\"Your order has been successfully processed!\"")).getText();
 //   String expectedresult ="Your order has been successfully processed!";
  // String actualresult = driver.findElement(By.xpath("//div[2]/div/div[@class=\"title\"][strong]]")).getText();
        String actualresult = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[1]/strong")).getText();
    Assert.assertEquals("comparing","Your order has been successfully processed!",actualresult);

 }
/*@Test
    public void Sortby(){
     driver.findElement(By.linkText("Books")).click(); //select books
    //  driver.findElement(By.xpath("//select[@name=\"products-orderby\"]")).click();
     driver.findElement(By.xpath("//select[@id=\"products-orderby\"]")).click();
     driver.findElement(By.linkText("Price: High to Low")).isSelected();

}*/



    @AfterMethod
    public void aftermethod(){
        driver.close();
    }

    public void click_element(By by){
    driver.findElement(by).click();

}
    public void enter_Element(By by,String text)
{
     driver.findElement(by).sendKeys(text);
}

}




