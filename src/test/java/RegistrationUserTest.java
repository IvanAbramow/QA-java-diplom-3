import com.codeborne.selenide.Configuration;
import org.junit.Before;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static pageObject.UrlLinks.BASE_URL;
import static com.codeborne.selenide.Selenide.*;
import static pageObject.UrlLinks.*;

public class RegistrationUserTest {

    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";
    String password = RandomStringUtils.randomAlphabetic(6);
    String name = RandomStringUtils.randomAlphabetic(6);

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для мака путь. Но на м1 у меня так и не получилось запустить их в яндекс.браузере.
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
    }

    @Test
    @DisplayName("Проверка успешной регистрации.")
    public void checkSuccessfulRegistration () {

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.fillFormRegister(name, email, password);

        loginPage.headingSearchLogin();
        mainPage.checkAssertEqualsUrlTrue(LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка, что отображается ошибка для некорректного пароля.")
    public void checkErrorRegistrationForIncorrectPassword () {
        RegistrationPage registrationPage = open(REGISTER_PAGE_URL, RegistrationPage.class);
        registrationPage.fillFormRegister(name, email, "123");

        registrationPage.checkErrorPasswordRegister();
    }
}
