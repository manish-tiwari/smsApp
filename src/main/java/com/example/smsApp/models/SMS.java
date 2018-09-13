package com.example.smsApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Manish Tiwari on 9/13/2018.
 */
@Data
@AllArgsConstructor
public class SMS {
    String accountNumber;
    String password;
    String phoneNumber;
    String message;
}
