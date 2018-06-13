package com.github.binarySmile.gmailtest.tests;

import com.github.binarySmile.gmailtest.core.BaseTest;
import com.github.binarySmile.gmailtest.core.pageobjects.AccountPage;
import com.github.binarySmile.gmailtest.core.pageobjects.GmailPage;
import com.github.binarySmile.gmailtest.core.pageobjects.MessagePage;
import com.github.binarySmile.gmailtest.core.pageobjects.RecipientsGmailPage;
import org.testng.annotations.Test;

import java.awt.*;

import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

public class SendingMessageWithFile extends BaseTest {

    private static final String topicName = "Autotest letter";
    private static final String textOfLetter = "I attached the file to the letter";
    private static final String textFromFile = "This is my file that I sent in a letter.";

    @Test
    public void sendingFileInLetterAndCheckTest() throws AWTException, InterruptedException {
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
        messagePage.attachmentFile();
        messagePage.sendLetter();
        dispose();
        init();
        AccountPage accountPageRecipient = page(AccountPage.class);
        accountPageRecipient.inputUserName(DATA.getProperty("email2"));
        assertTrue(accountPageRecipient.getProfileNameField(DATA.getProperty("email2")).isDisplayed());
        accountPageRecipient.inputPassword(DATA.getProperty("password2"));
        GmailPage gmailPageRecipient = page(GmailPage.class);
        assertTrue(gmailPageRecipient.getTitleEmail().exists());
        gmailPageRecipient.searchingForMessageByParameters(DATA.getProperty("email"), topicName);
        RecipientsGmailPage recipientsGmailPage = page(RecipientsGmailPage.class);
        assertTrue(recipientsGmailPage.getTitleLetter(topicName).isDisplayed());
        recipientsGmailPage.openAttachmentFile();
        assertTrue(recipientsGmailPage.getBodyFile().getText().equals(textFromFile));
    }
}
