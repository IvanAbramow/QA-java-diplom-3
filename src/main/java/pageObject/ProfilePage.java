package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;


public class ProfilePage {

    //локатор кнопки «Выход»
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private static SelenideElement exitButton;

    @Step("Клик по кнопке «Выход»")
    public static void clickExitButton() {
        exitButton.shouldBe(enabled).click();
    }

}
