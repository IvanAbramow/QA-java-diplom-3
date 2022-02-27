import com.UserOperations;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static pageObject.UrlLinks.BASE_URL;
import static pageObject.UrlLinks.LOGIN_PAGE_URL;
import static pageObject.UrlLinks.*;

public class UserOperationsTest {
    private UserOperations userOperations;

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для мака путь. Но на м1 у меня так и не получилось запустить их в яндекс.браузере.
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
    }

    @Before
    public void setUp() {
        userOperations = new UserOperations();
    }

    @After
    public void deleteUser() {
        userOperations.delete();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет с авторизацией пользователя")
    @Description("Проверка перехода на страницу Профиль по клику на Личный кабинет")
    public void checkGoToPersonalAccountWithAuthorizationTest() {
        Map<String, String> credentials = userOperations.register();

        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        mainPage.checkPageDisplay("Профиль", PROFILE_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор с регистрацией и авторизацией пользователя")
    @Description("Проверка перехода по клику на Конструктор")
    public void checkGoToFromLoginPageInConstructorWithAuthorizationTest() {
        Map<String, String> credentials = userOperations.register();

        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        loginPage.clickConstructorLink();

        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип с регистрацией и авторизацией пользователя")
    @Description("Проверка перехода по клику на логотип Stellar Burgers")
    public void checkClickLogoGoToFromLoginPageInConstructorWithAuthorizationTest() {

        Map<String, String> credentials = userOperations.register();

        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        loginPage.clickLogoLink();

        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }
    @Test
    @DisplayName("Проверка перехода в личный кабинет без авторизации пользователя")
    @Description("Проверка перехода на страницу Вход по клику на кнопку Личный кабинет")
    public void checkGoToPersonalAccountWithoutAuthorizationTest() {
        MainPage mainPage = open(BASE_URL, MainPage.class);

        mainPage.clickPersonalAreaButton();

        mainPage.checkPageDisplay("Вход", LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор без авторизации пользователя")
    @Description("Проверка перехода по клику на кнопку Конструктор")
    public void checkGoToFromLoginPageInConstructorWithoutAuthorizationTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);

        loginPage.clickConstructorLink();

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип без авторизации пользователя")
    @Description("Проверка перехода по клику на логотип Stellar Burgers")
    public void checkClickLogoGoToFromLoginPageInConstructorWithoutAuthorizationTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);

        loginPage.clickLogoLink();

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }
}
