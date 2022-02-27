package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRestorePage {

    //локатор поля ввода Email
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailRestoreField;
    //локатор кнопки Восстановить
    @FindBy(how = How.XPATH, using = ".//button[text()='Восстановить']")
    private SelenideElement restoreButton;

    //локатор ссылка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement signInLink;

    //метод заполнения поля ввода Email
    public void setEmailRestore (String email) {
        emailRestoreField.setValue(email);
    }
    //метод клика по кнопке «Восстановить»
    public void clickRestoreButton() {
        restoreButton.click();
    }

    @Step("Клик по ссылке «Войти»")
    public void clickSignInLinkFormRestore() {
        signInLink.shouldBe(Condition.visible).click();
    }

    @Step("Заполнения формы «Восстановить пароль»: ввод полей и клика по кнопке «Восстановить»")
    public void fillFormRestorePassword(String email) {
        setEmailRestore(email);
        clickRestoreButton();
    }
}
