import com.UserOperations;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static pageObject.UrlLinks.LOGIN_PAGE_URL;

public class SignOutTest {
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
    @DisplayName("Проверка выхода из аккаунта.")
    @Description("Проверка выхода по кнопке Выйти в личном кабинете")
    public void checkSignOutClickExitButtonTest() {
        Map<String, String> credentials = userOperations.register();

        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickExitButton();

        mainPage.checkPageDisplay("Вход", LOGIN_PAGE_URL);
    }
}

