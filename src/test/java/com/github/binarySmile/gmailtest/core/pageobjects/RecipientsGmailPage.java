package com.github.binarySmile.gmailtest.core.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.github.binarySmile.gmailtest.core.BaseTest;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class RecipientsGmailPage extends BaseTest {

    @FindBy(xpath = "//div[@class='ha']/h2[@class='hP']")
    private SelenideElement titleLetter;

    @FindBy(className = "aSH")
    private SelenideElement attachmentFile;

    @FindBy(className = "aLF-aPX-aPF-aPE-a1J-Ji")
    private SelenideElement bodyFile;

    @FindBy(className = "amn")
    private SelenideElement openInputFieldButton;

    @FindBy(xpath = ".//div[@role='textbox']")
    private SelenideElement messageInputField;

    @FindBy(xpath = "//div[@class='J-J5-Ji btA']")
    private SelenideElement sendLetterButton;

    public void openAttachmentFile() {
        attachmentFile.click();
    }

    public SelenideElement getBodyFile() {
        return bodyFile;
    }

    public SelenideElement getTitleLetter(String topic) {
        return titleLetter.shouldHave(text(topic));
    }

    public void responseToLetter(String text) {
        openInputFieldButton.click();
        messageInputField.click();
        messageInputField.setValue(text);
    }

    public SelenideElement getMessageInputField(String string) {
        return messageInputField.shouldHave(text(string));
    }

    public void sendReplyMessage() {
        sendLetterButton.click();
    }
}