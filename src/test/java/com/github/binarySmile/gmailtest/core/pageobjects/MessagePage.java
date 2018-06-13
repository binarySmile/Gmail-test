package com.github.binarySmile.gmailtest.core.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.binarySmile.gmailtest.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MessagePage extends BaseTest {

    @FindBy(name = "to")
    private SelenideElement recipientInput;

    @FindBy(name = "subjectbox")
    private SelenideElement subjectBoxInput;

    @FindBy(xpath = ".//div[@role='textbox']")
    private SelenideElement areaOfLetter;

    @FindBy(xpath = ".//div[@command='Files']")
    private SelenideElement attachButton;

    @FindBy(xpath = "//div[@class='J-J5-Ji btA']")
    private SelenideElement sendButton;

    @FindBy(xpath = "//*[@class='Hm']/img[@class='Ha']")
    private SelenideElement closeButton;

    public void enterRecipient() {
        recipientInput.setValue(DATA.getProperty("email2"));
    }

    public SelenideElement getByName(String recipient) {
        return recipientInput.should(Condition.value(recipient));
    }

    public void enterTopic(String name) {
        subjectBoxInput.setValue(name);
    }

    public SelenideElement getByNameTopic(String name) {
        return subjectBoxInput.should(Condition.value(name));
    }

    public void enterTextLetter(String document) {
        areaOfLetter.setValue(document);
    }

    public SelenideElement getAreaOfLetter(String string) {
        return areaOfLetter.shouldHave(text(string));
    }

    public void sendLetter() throws InterruptedException {
        sendButton.click();
        $(By.xpath("//div[@class='vh']")).shouldBe(visible).waitUntil(appears, 3000);
    }

    public void attachmentFile() throws InterruptedException, AWTException {
        attachButton.click();
        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        StringSelection stringSelection = new StringSelection(DATA.getProperty("file"));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.setAutoDelay(1000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void closeMessagePage() {
        closeButton.click();
    }
}
