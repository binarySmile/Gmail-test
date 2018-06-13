package com.github.binarySmile.gmailtest.core.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.binarySmile.gmailtest.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class GmailPage extends BaseTest {

    @FindBy(className = "gb_8b")
    private SelenideElement titleEmail;

    @FindBy(id = ":hd")
    private SelenideElement writeButon;

    @FindBy(xpath = "//div[@role='dialog']")
    private SelenideElement fieldsForEnteringNewMessage;

    @FindBy(className = "yE")
    private SelenideElement attachmentFileLabel;

    @FindBy(xpath = "//*[@class='ii gt adP adO']//div")
    private SelenideElement textOfIncomingLetter;

    @FindBy(xpath = "//div[@class='aio UKr6le']/span[@class='nU n1']/a")
    private SelenideElement draftLink;

    @FindBy(xpath = "//div[@role='main']")
    private SelenideElement letterListPanel;

    public SelenideElement getTitleEmail() {
        return titleEmail.shouldBe(visible);
    }

    public void createNewMessage() {
        writeButon.shouldBe(visible).click();
    }

    public SelenideElement getFieldsForEnteringNewMessage() {
        return fieldsForEnteringNewMessage.shouldBe(visible);
    }

    public SelenideElement getTextOfIncomingLetter(String string) {
        return textOfIncomingLetter.shouldHave(text(string));
    }

    public void searchingForMessageByParameters(String name, String topic) {
        SelenideElement letters = $(By.xpath(".//div[@class='Cp']//tr"));
        ElementsCollection list = $$(By.xpath(".//table[@class='F cf zt']//tr"));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equals(name) && list.get(i).getText().equals(topic)
                    && list.get(i).equals(getElement((By) attachmentFileLabel))) ;
            else if (list.get(i).getText().equals(name) && list.get(i).getText().equals(topic)) {
                letters = list.get(i);
            }
            letters.followLink();
            break;
        }
    }

    public DraftPage openDraftPage() {
        draftLink.click();
        return page(DraftPage.class);
    }
}
