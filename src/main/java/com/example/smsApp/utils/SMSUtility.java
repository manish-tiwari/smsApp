package com.example.smsApp.utils;

import com.mscience.InboundMessageResult;
import com.mscience.SendResult;
import com.mscience.SmsClient;
import com.mscience.StatusResult;

import javax.xml.parsers.ParserConfigurationException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

/**
 * Created by Manish Tiwari on 9/13/2018.
 */
public class SMSUtility { // implements Runnable {
    private SmsClient client;

    public SMSUtility(String accountNumber, String password){
        try {
            client = new SmsClient(accountNumber, password);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void sendSMS( String destinationContact, String sms) throws Exception {
         final Logger LOGGER = Logger.getLogger(SMSUtility.class.getName());

            SendResult result = client.send(destinationContact, "", sms, 0, true);

            if (result.HasError()) {
                LOGGER.info("");
                LOGGER.info("Code: %s, Error: %s" +result.getCode() +result.getErrorMessage());
            } else {
                LOGGER.info("Code: %s, Id: %d, Balance: %d" + result.getCode() +result.getMessageId()
                       +result.getMessageBalance());
                LOGGER.info("Pending: %d, Surcharge: %.6f" +result.getPendingMessages()
                        +result.getSurchargeBalance());
                LOGGER.info("");
            }

            int[] messages = new int[1];
            messages[0] = result.getMessageId();

            StatusResult[] result2 = client.getMessageStatus(messages);

            for (int i = 0; i < result2.length; i++) {
                LOGGER.info(result2[i].getStatus());
            }

            //Added Try catch because SMS is sent but we are getting below execption.
            //org.jboss.resteasy.spi.UnhandledException: java.text.ParseException: Unparseable date: &quot;11/09/2018 21:55:21&quot;
            try {
                InboundMessageResult[] inbound = client.getDeliveryReceipts();

                for (InboundMessageResult message : inbound) {
                    LOGGER.info("Code: %s, Id: %d, Source: %s, Destination: %s" + message.getCode() + message.getId() +
                            message.getSource() + message.getDestination());
                    LOGGER.info("Received: %s, SourceId: %d, IsReceipt: %s, text: %s" + message.getReceived()
                            + message.getSourceId() + message.getDeliveryReceipt() + message.getText());
                    LOGGER.info("");
                }
            }catch(Exception e){
                LOGGER.info("Exception occured while getting Delivery Receipts" + e.getMessage());
            }

    }

    /*@Override
    public void run() {
        try {
            new SMSUtility().sendSMS();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
