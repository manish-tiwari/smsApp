package com.example.smsApp.services;

import com.example.smsApp.models.SMS;
import com.example.smsApp.utils.SMSUtility;
import org.springframework.stereotype.Service;

/**
 * Created by Manish Tiwari on 9/13/2018.
 */
@Service
public class SmsService {



    public void sendSMS(SMS sms) {
        try {
            new SMSUtility(sms.getAccountNumber(),sms.getPassword()).sendSMS(sms.getPhoneNumber(),sms.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
