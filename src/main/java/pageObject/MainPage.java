package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class MainPage {

    //локатор кнопки «Личный Кабинет»
    @FindBy(how = How.XPATH, using = ".//a[starts-with(@class, 'AppHeader_header')]/p[text()='Личный Кабинет']")
    private static SelenideElement personalAreaButton;

    //локатор кнопки «Войти в аккаунт»
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private static SelenideElement signInButton;

    //локатор ссылки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private static SelenideElement constructorLink;

    // Раздел «Конструктор»
    //локатор кнопки «Булки»
    @FindBy(how = How.XPATH, using = "//*[text()='Булки']")
    private static SelenideElement bunButton;
    //локатор кнопки «Соусы»
    @FindBy(how = How.XPATH, using = "//*[text()='Соусы']")
    private static SelenideElement saucesButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Начинки']")
    private static SelenideElement fillingButton;

    //локатор раздел «Булки»
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private static SelenideElement bunSection;
    //локатор раздел «Соусы»
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private static SelenideElement sauceSection;
    //локатор раздел «Начинки»
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private static SelenideElement fillingSection;



    @Step("Клик по кнопке «Личный Кабинет»")
    public static void clickPersonalAreaButton() {
        personalAreaButton.shouldBe(enabled).click();
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public static void clickSignInButton() {
        signInButton.click();
    }

    @Step("проверка адреса url")
    public void checkAssertEqualsUrlTrue(String url) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Ошибка в url", url, currentUrl);
    }

    @Step("проверка что на странице есть определенный текст")
    public void checkTextVisible(String text) {
        $(byText(text)).shouldBe(Condition.visible);
    }

    @Step("Проверка отображения страницы.")
    public void checkPageDisplay(String text, String Url) {
        checkTextVisible(text);
        checkAssertEqualsUrlTrue(Url);
    }

    // Раздел «Конструктор»
    @Step("Клик по разделу «Булки»")
    public static void clickBunButton() {
        bunButton.shouldBe(enabled).click();
    }
    @Step("Клик по разделу «Соусы»")
    public static void clickSauceButton() {
        saucesButton.click();
    }
    @Step("Клик по разделу «Начинки»")
    public static void clickFillingButton() {
        fillingButton.shouldBe(enabled).click();
    }

    @Step("Проверка корректности отображения раздела Булки")
    public void checkBunSectionText() {
        bunSection.shouldHave(exactText("Булки"));
    }
    @Step("Проверка корректности отображения раздела Соусы")
    public void checkSaucesSectionText() {
        sauceSection.shouldHave(exactText("Соусы"));
    }
    @Step("Проверка корректности отображения раздела Начинки")
    public void checkFillingSectionText() {
        fillingSection.shouldHave(exactText("Начинки"));
    }
}
