package bg.vivacom.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestVivacomPage {

    private WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    @Test
    public void pageVivacom() throws InterruptedException {


        driver.manage().window().maximize();

        //1. Зареди URL https://www.vivacom.bg/bg/
        driver.get("https://www.vivacom.bg/bg");

        //2. Избери „Устройства“ от главното меню и „Мобилни телефони“ от подменюто
        driver.findElement(By.xpath("//a[@role = 'button' and contains(text(),'Устройства')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Мобилни телефони')]")).click();
        Thread.sleep(1000);

        //3. Филтрирай устройствата по Производител = APPLE
        driver.findElement(By.xpath("//label/span[contains(text(),'APPLE')]")).click();
        Thread.sleep(1000);

        //4. Филтрирай устройствата по Цвят = GOLD
        driver.findElement(By.xpath("//label/span[contains(text(),'GOLD')]")).click();
        Thread.sleep(1000);

        //5. Избери устройство APPLE IPHONE 12 PRO MAX 128GB
        driver.findElement(By.xpath("//div/h3[contains(text(),'APPLE IPHONE 12 PRO MAX 128GB')]")).click();
        Thread.sleep(3000);

        //6. Избери цена на устройство с план Unlimited 50 и еднократно плащане.
        driver.findElement(By.xpath("//h3[contains(text(),'Unlimited 50')]/../../../../..//span[@class = 'e-care-home-big-bill-price-digits js-related-offer-cash-price-span']")).click();
        Thread.sleep(1000);

        //7. Добави устройството в кошницата
        driver.findElement(By.xpath("//button[@type='submit'  and contains(.,'Купи')]")).click();
        Thread.sleep(1000);

        //8. Верифицирай, че си в кошницата.
        boolean textCartOne = driver.getPageSource().contains("Моята кошница");
        if (textCartOne) {
            System.out.println("Кошницата се визуализира");
        } else {
            System.out.println("Кошницата не се визуализира");
        }
        Thread.sleep(1000);

        //9. Продължи с пазаруването.
        driver.findElement(By.xpath("//a[@title = 'Продължи с пазаруването']")).click();
        Thread.sleep(1000);

        //10. Избери „Устройства“ от главното меню и „Аксесоари“ от подменюто.
        driver.findElement(By.xpath("//a[@role = 'button' and contains(text(),'Устройства')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Аксесоари')]")).click();
        Thread.sleep(1000);

        //11. Филтрирай аксесоарите по Производител = APPLE и Цена = между 5 и 40 лв.
        driver.findElement(By.xpath("//label/span[contains(text(),'APPLE')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(text(),'между 5 и 40 лв.')]")).click();
        Thread.sleep(1000);

        //12. Избери „APPLE СЛУШАЛКИ с LIGHTNING“
        driver.findElement(By.xpath("//h3[contains(text(),'APPLE СЛУШАЛКИ с LIGHTNING CONNECTOR')]")).click();
        Thread.sleep(1000);

        //13. Добави слушалките в кошницата.
        driver.findElement(By.xpath("//button[@type='submit'  and contains(.,'Купи')]")).click();
        Thread.sleep(2000);

        //14. Верифицирай, че си в кошницата.
        boolean textCartTwo = driver.getPageSource().contains("Моята кошница");
        if (textCartTwo) {
            System.out.println("Кошницата се визуализира");
        } else {
            System.out.println("Кошницата не се визуализира");
        }
        Thread.sleep(1000);

        //15. Провери общата сума за всички добавени устройства. Ако надвишава 2000 лв. премахни слушалките.
        WebElement finalPrice = driver.findElement(By.xpath("//div[@class = 'row final-price']/span[2]"));
        String priceText = finalPrice.getText();
        double price = Double.parseDouble(priceText.substring(0, priceText.length() - 4));
        if (price > 2000) {
            driver.findElement(By.xpath("//h3[contains(text(),'СЛУШАЛКИ')]/../../../../form/button")).click();
        }
        Thread.sleep(1000);

        //16. Верифицирай, че checkbox-a “Общи условия за мобилни услуги” е наличен.
        try {
            driver.findElement(By.xpath("//input[@id='mobile-termes-and-conditions']/.."));
            System.out.println("Checkbox-a \"Общи условия за мобилни услуги\" е наличен");
        } catch (Exception e) {
            System.out.println("Checkbox-a \"Общи условия за мобилни услуги\" не е наличен");
        }

        //17. Верифицирай, че бутоните „Продължи като клиент“ и „Продължи като гост“ са неактивни.
        try {
            driver.findElement(By.xpath("//button[@class = 'btn btn-success js-checkout-btn disable-elm']"));
            System.out.println("Бутона \"Продължи като клиент\" не е активен");
        } catch (Exception e) {
            System.out.println("Бутона \"Продължи като клиент\" е активен");
        }
        Thread.sleep(1000);

        try {
            driver.findElement(By.xpath("//button[@class = 'btn btn-success js-support-checkout-btn disable-elm']"));
            System.out.println("Бутона \"Продължи като гост\" не е активен");
        } catch (Exception e) {
            System.out.println("Бутона \"Продължи като гост\" е активен");
        }
        Thread.sleep(1000);

        //18. Избери checkbox-a „ Общи условия за мобилни услуги “ и верифицирай, че след избирането му бутоните „Продължи като клиент“ и „Продължи като гост“ стават активни.
        driver.findElement(By.xpath("//input[@id = 'mobile-termes-and-conditions']/..")).click();
        Thread.sleep(2000);

        try {
            driver.findElement(By.xpath("//button[@class = 'btn btn-success js-checkout-btn']"));
            System.out.println("Бутона \"Продължи като клиент\" е активен");
        } catch (Exception e) {
            System.out.println("Бутона \"Продължи като клиент\" не е активен");
        }
        Thread.sleep(1000);

        try {
            driver.findElement(By.xpath("//button[@class = 'btn btn-success js-support-checkout-btn']"));
            System.out.println("Бутона \"Продължи като гост\" е активен");
        } catch (Exception e) {
            System.out.println("Бутона \"Продължи като гост\" не е активен");
        }
        Thread.sleep(1000);

        //19. Премахни добавените в кошницата продукти.
        driver.findElement(By.xpath("//div[@class='products-container']//em[@class='vivacom-icons icon-close_x']")).click();
        Thread.sleep(1000);

        //20. Верифицирай, че следното съобщение излиза на екрана – „В момента кошницата ви е празна. Вижте актуалните ни оферти и изберете най-подходящата за вас. Ако искате да разгледате предходно добавени продукти, натиснете "Вход".“
        boolean textOne = driver.getPageSource().contains("В момента кошницата ви е празна");
        boolean textTwo = driver.getPageSource().contains("Вижте актуалните ни оферти и изберете най-подходящата за вас. Ако искате да разгледате предходно добавени продукти, натиснете \"Вход\".");
        if (textOne == textTwo) {
            System.out.println("Съобщението \"В момента кошницата ви...\" се показва");
        } else {
            System.out.println("Съобщението \"В момента кошницата ви...\" не се показва");
        }


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

