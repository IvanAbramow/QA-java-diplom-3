import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pageObject.UrlLinks.BASE_URL;

public class ConstructorTest {

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для мака путь. Но на м1 у меня так и не получилось запустить их в яндекс.браузере.
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Переход к разделу Булки по клику на слово Булки")
    public void checkGoToTheBunSectionTest() {
        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickFillingButton();

        mainPage.clickBunButton();

        mainPage.checkBunSectionText();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Переход к разделу Соусы по клику на слово Соусы")
    public void checkGoToTheSauceSectionTest() {
        MainPage mainPage = open(BASE_URL, MainPage.class);

        mainPage.clickSauceButton();

        mainPage.checkSaucesSectionText();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Переход к разделу Начинки по клику на слово Начинки")
    public void checkGoToTheFillingSectionTest() {
        MainPage mainPage = open(BASE_URL, MainPage.class);

        mainPage.clickFillingButton();

        mainPage.checkFillingSectionText();
    }
}
