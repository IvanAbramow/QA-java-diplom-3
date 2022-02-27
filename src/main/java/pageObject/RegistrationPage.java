package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // Поля формы "Регистрация"
    //локатор поля ввода Имя
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameRegisterField;
    //локатор поля ввода Email
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailRegisterField;
    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement passwordRegisterField;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //локатор "Некорректный пароль"
    @FindBy(how = How.CLASS_NAME, using = "input_status_error")
    private SelenideElement errorPassword;

    //локатор ссылка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement signInLink;

    //метод заполнения поля ввода Имя
    public void setNameRegister (String name) {
        nameRegisterField.setValue(name);
    }
    //метод заполнения поля ввода Email
    public void setEmailRegister (String email) {
        emailRegisterField.setValue(email);
    }
    //метод заполнения поля ввода Пароль
    public void setPasswordRegister(String password) {
        passwordRegisterField.setValue(password);
    }

    //метод клика по кнопке «Зарегистрироваться»
    public void clickRegisterButton() {
        registerButton.click();
    }

    //метод клика по ссылке "Войти"
    public void clickSignInLink() {
        signInLink.click();
        //System.out.println("клик по ссылке Войти");
    }

    //метод заполнения формы "Регистрация": объединяет ввод полей и клика по кнопке
    @Step("Заполнение формы 'Регистрация' и клик по кнопке 'Зарегистрироваться'")
    public void fillFormRegister(String name,String email, String password) {
        setNameRegister(name);
        setEmailRegister(email);
        setPasswordRegister(password);
        clickRegisterButton();
    }

    @Step("Проверка, что отображается ошибка для некорректного пароля")
    public void checkErrorPasswordRegister() {
        errorPassword.shouldBe(Condition.visible);
        $(byText("Некорректный пароль")).shouldBe(Condition.visible);
        System.out.println("отображается ошибка поля пароля");
    }
}
