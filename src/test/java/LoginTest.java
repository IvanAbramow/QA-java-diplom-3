import com.UserOperations;
import com.codeborne.selenide.Configuration;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import pageObject.PasswordRestorePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static pageObject.UrlLinks.*;

public class LoginTest {
    private UserOperations userOperations;

    @After
    public void deleteUser() {
        userOperations.delete();
    }

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

    @Test
    @DisplayName("Проверка входа через кнопку Личный кабинет")
    public void checkLoginOnPersonalAreaButton () {
        Map<String, String> credentials = userOperations.register();

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа по кнопке Войти в аккаунт на главной странице")
    public void checkLoginOnSignInButton() {
        Map<String, String> credentials = userOperations.register();

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickSignInButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void checkLoginOnFormRegister () {
        Map<String, String> credentials = userOperations.register();

        RegistrationPage registrationPage = open(REGISTER_PAGE_URL, RegistrationPage.class);
        registrationPage.clickSignInLink();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void checkLoginOnFormPasswordRecovery () {
        Map<String, String> credentials = userOperations.register();

        PasswordRestorePage passwordRestorePage = open(RESTORE_PAGE_URL, PasswordRestorePage.class);
        passwordRestorePage.clickSignInLinkFormRestore();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }
}
