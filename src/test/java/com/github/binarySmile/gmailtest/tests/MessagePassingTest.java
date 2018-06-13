package com.github.binarySmile.gmailtest.tests;

import com.github.binarySmile.gmailtest.core.BaseTest;
import com.github.binarySmile.gmailtest.core.pageobjects.AccountPage;
import com.github.binarySmile.gmailtest.core.pageobjects.GmailPage;
import com.github.binarySmile.gmailtest.core.pageobjects.MessagePage;
import com.github.binarySmile.gmailtest.core.pageobjects.RecipientsGmailPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

public class MessagePassingTest extends BaseTest {

    private static final String topicName = "Request message";
    private static final String textOfLetter = "Sending the first message";
    private static final String responseText = "Reply to the received letter";

    @Test
    public void sendingAndCheckingMessagesTest() throws InterruptedException {
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
        messagePage.sendLetter();
        dispose();
        init();
        AccountPage accountPage1 = page(AccountPage.class);
        accountPage1.inputUserName(DATA.getProperty("email2"));
        assertTrue(accountPage1.getProfileNameField(DATA.getProperty("email2")).isDisplayed());
        accountPage1.inputPassword(DATA.getProperty("password2"));
        GmailPage gmailPage1 = page(GmailPage.class);
        assertTrue(gmailPage1.getTitleEmail().exists());
        gmailPage1.searchingForMessageByParameters(DATA.getProperty("email"), topicName);
        RecipientsGmailPage recipientsGmailPage = page(RecipientsGmailPage.class);
        assertTrue(recipientsGmailPage.getTitleLetter(topicName).isDisplayed());
        recipientsGmailPage.responseToLetter(responseText);
        assertTrue(recipientsGmailPage.getMessageInputField(responseText).isDisplayed());
        recipientsGmailPage.sendReplyMessage();
        dispose();
        init();
        AccountPage accountPage2 = page(AccountPage.class);
        accountPage2.inputUserName(DATA.getProperty("email"));
        assertTrue(accountPage2.getProfileNameField(DATA.getProperty("email")).isDisplayed());
        accountPage2.inputPassword(DATA.getProperty("password1"));
        GmailPage gmailPage2 = page(GmailPage.class);
        assertTrue(gmailPage2.getTitleEmail().exists());
        gmailPage2.searchingForMessageByParameters(DATA.getProperty("email"), topicName);
        assertTrue(gmailPage2.getTextOfIncomingLetter(responseText).isDisplayed());
    }
}
