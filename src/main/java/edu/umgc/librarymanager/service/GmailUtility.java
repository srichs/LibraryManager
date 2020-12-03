/*
 * Filename: GmailUtility.java
 * Author: Scott
 * Date Created: 11/20/2020
 */

package edu.umgc.librarymanager.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utility class that is used to send email notifications from the application. It requires a google token
 * to work. A token is already generated and can be included with the application to allow it to send
 * emails from the umgc.library.manager@gmail.com email account.
 */
public final class GmailUtility {

    private static final String APPLICATION_NAME = "LibraryManager";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "./src/main/resources/google/tokens/";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
    private static final String CREDENTIALS_FILE_PATH = "./src/main/resources/google/credentials.json";
    private static final String APPLICATION_EMAIL = "LibraryManager <umgc.library.manager@gmail.com>";

    private GmailUtility() { }

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     * @see <a href="https://developers.google.com/gmail/api/quickstart/java">Gmail API - Java Quickstart</a>
     */
    // This method is from the Google API.
    private static Credential getCredentials(final NetHttpTransport transport) throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new FileReader(CREDENTIALS_FILE_PATH));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                transport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Constructs an email mime message.
     * @param to The to block of the email.
     * @param cc The cc block of the email.
     * @param from The from block of the email.
     * @param subject The subject block of the email.
     * @param bodyText The body of the email.
     * @return A MimeMessage Email Object.
     * @throws MessagingException
     * @see <a href="https://stackoverflow.com/questions/3649014/send-email-using-java">Stackoverflow
     * - send email using java</a>
     */
    private static MimeMessage createEmail(String to, String cc, String from, String subject,
            String bodyText) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        InternetAddress tAddress = new InternetAddress(to);
        InternetAddress cAddress = cc.isEmpty() ? null : new InternetAddress(cc);
        InternetAddress fAddress = new InternetAddress(from);

        email.setFrom(fAddress);
        if (cAddress != null) {
            email.addRecipient(javax.mail.Message.RecipientType.CC, cAddress);
        }
        email.addRecipient(javax.mail.Message.RecipientType.TO, tAddress);
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    /**
     * Creates a message with a MimeMessage Object.
     * @param email A MimeMessage Object for the email.
     * @return The Message to be sent.
     * @throws MessagingException
     * @throws IOException
     * @see <a href="https://stackoverflow.com/questions/3649014/send-email-using-java">Stackoverflow
     * - send email using java</a>
     */
    private static Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        email.writeTo(baos);
        String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    /**
     * Sends an email for the given parameters.
     * @param recipientEmail The recipient's email address.
     * @param title The subject of the email.
     * @param message The body of the email.
     * @throws IOException
     * @throws MessagingException
     * @throws GeneralSecurityException
     * @see <a href="https://stackoverflow.com/questions/3649014/send-email-using-java">Stackoverflow
     * - send email using java</a>
     */
    public static void sendEmail(String recipientEmail, String title, String message)
            throws IOException, MessagingException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
        Message m = createMessageWithEmail(createEmail(recipientEmail, "", APPLICATION_EMAIL, title, message));
        service.users().messages().send("me", m).execute();
    }

    /*public static void main(String[] args) {
        try {
            sendEmail("scott.l.richards@email.com", "Hello", "From the LibraryManager Application.");
        } catch (IOException | MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }*/

}
