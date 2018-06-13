package com.github.binarySmile.gmailtest.core.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.github.binarySmile.gmailtest.core.BaseTest;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class AccountPage extends BaseTest {

    @FindBy(id = "identifierId")
    private SelenideElement inputUserNameField;

    @FindBy(name = "password")
    private SelenideElement inputPasswordField;

    @FindBy(id = "profileIdentifier")
    private SelenideElement profileNameField;

    public void inputUserName(String userName) {
        inputUserNameField.click();
        inputUserNameField.setValue(userName).pressEnter();
    }

    public void inputPassword(String password) {
        inputPasswordField.click();
        inputPasswordField.setValue(password).pressEnter();
    }

    public SelenideElement getProfileNameField(String name) {
        return profileNameField.shouldHave(text(name));
    }
}

