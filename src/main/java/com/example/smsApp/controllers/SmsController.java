package com.example.smsApp.controllers;

import com.example.smsApp.models.SMS;
import com.example.smsApp.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Manish Tiwari on 9/13/2018.
 */
@Controller
@RequestMapping("/api")
public class SmsController {

    @Autowired
    SmsService smsService;

    @PutMapping(value = "/sendSMS")
    @ResponseStatus(HttpStatus.OK)
    public void sendSMS(@RequestBody SMS sms) {
        smsService.sendSMS(sms);
    }
}
