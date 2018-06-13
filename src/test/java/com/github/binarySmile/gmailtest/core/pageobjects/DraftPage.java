package com.github.binarySmile.gmailtest.core.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.github.binarySmile.gmailtest.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class DraftPage extends BaseTest {

    @FindBy(xpath = ".//*[@class='a3E']")
    private SelenideElement titleOfMessage;

    public void searchMessageInDraft(String topic) {
        $$(By.xpath(".//td[@class='xY a4W']")).findBy(text(topic)).click();
    }

    public SelenideElement getTitleOfMessage(String name) {
        return titleOfMessage.shouldHave(text(name));
    }
}
