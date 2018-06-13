package com.github.binarySmile.gmailtest.tests;

import com.github.binarySmile.gmailtest.core.BaseTest;
import com.github.binarySmile.gmailtest.core.pageobjects.AccountPage;
import com.github.binarySmile.gmailtest.core.pageobjects.DraftPage;
import com.github.binarySmile.gmailtest.core.pageobjects.GmailPage;
import com.github.binarySmile.gmailtest.core.pageobjects.MessagePage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

public class DraftTest extends BaseTest {

    private static final String topicName = "Autotest of a draft";
    private static final String textOfLetter = "My letter";

    @Test
    public void checkingDraftLetterTest() {
        AccountPage accountPage = page(AccountPage.class);
        accountPage.inputUserName(DATA.getProperty("email"));
        assertTrue(accountPage.getProfileNameField(DATA.getProperty("email")).isDisplayed());
        accountPage.inputPassword(DATA.getProperty("password1"));
        GmailPage gmailPage = page(GmailPage.class);
        assertTrue(gmailPage.getTitleEmail().exists());
        gmailPage.createNewMessage();
        assertTrue(gmailPage.getFieldsForEnteringNewMessage().isDisplayed());
        MessagePage messagePage = page(MessagePage.class);
        messagePage.enterRecipient();
        assertTrue(messagePage.getByName(DATA.getProperty("email2")).isDisplayed());
        messagePage.enterTopic(topicName);
        assertTrue(messagePage.getByNameTopic(topicName).isDisplayed());
        messagePage.enterTextLetter(textOfLetter);
        assertTrue(messagePage.getAreaOfLetter(textOfLetter).isDisplayed());
        messagePage.closeMessagePage();
        assertTrue(gmailPage.getTitleEmail().exists());
        DraftPage draftPage = gmailPage.openDraftPage();
        draftPage.searchMessageInDraft(topicName);
        assertTrue(draftPage.getTitleOfMessage(topicName).isDisplayed());
    }
}
