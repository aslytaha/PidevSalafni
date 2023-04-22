package com.example.pidev.Services;

import com.example.pidev.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service



public class SMSServiceTransaction {
    @Autowired
    TwilioConfig twilioConfig;


    public Message sendSMS(String toNumbre) {
        Twilio.init(twilioConfig.getAccount_sid(), twilioConfig.getAuth_token());

        Message msg = Message.creator(new PhoneNumber("+216" + toNumbre),
                new PhoneNumber(twilioConfig.getSender_number()),"Transaction Submited:  ").create();
        System.out.println(msg.getSid());
        return msg ;
    }


}
