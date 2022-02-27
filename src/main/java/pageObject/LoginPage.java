package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    // Поля формы "Вход"
    //локатор поля ввода Email
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;
    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;

    //локатор "Некорректный пароль"
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement incorrectPassword;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    //локатор ссылки 'Зарегистрироваться'
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    //локатор логотип Stellar Burgers
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'AppHeader_header__logo')]")
    private static SelenideElement logo;

    //локатор ссылки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private static SelenideElement constructorLink;

    //метод заполнения поля ввода Email
    public void setEmail (String email) {
        emailField.setValue(email);
    }
    //метод заполнения поля ввода Пароль
    public void setPassword(String password) {
        passwordField.setValue(password);
    }
    //метод клика по кнопке «Войти»
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по ссылки «Зарегистрироваться»")
    public void clickRegisterLink() {
        registerLink.scrollTo();
        registerLink.click();
    }

    //метод заполнения формы "Вход": объединяет ввод полей и клика по кнопке
    @Step("заполнения формы 'Вход': объединяет ввод полей и клика по кнопке 'Войти'")
    public void fillFormLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("проверка ошибки для некорректного пароля")
    public void сheckErrorPassword() {
        incorrectPassword.shouldBe(Condition.visible);
        $(byText("Некорректный пароль")).shouldBe(Condition.visible);
        System.out.println("отображается ошибка поля пароля");
    }

    @Step("Проверка отображения заголовка 'Вход'")
    public void headingSearchLogin() {
        $(byText("Вход")).shouldBe(Condition.visible);
    }

    @Step("Клик по кнопке «Конструктор»")
    public static void clickConstructorLink() {
        constructorLink.shouldBe(enabled).click();
        System.out.println("клик по ссылке Конструктор");
    }

    @Step("Клик по логотипу Stellar Burgers")
    public static void clickLogoLink() {
        logo.shouldBe(enabled).click();
        System.out.println("клик по логотипу Stellar Burgers");
    }
}
