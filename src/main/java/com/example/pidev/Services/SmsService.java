package com.example.pidev.Services;

import com.example.pidev.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {


        @Autowired
        private TwilioConfig twilioConfig;

        //SMS
        public void SMSSend(String toNumber,String code){
            // Find your Account Sid and Token at twilio.com/user/account
            Twilio.init(twilioConfig.getAccount_sid(), twilioConfig.getAuth_token());

            Message message = Message.creator(new PhoneNumber("+216" + toNumber),
                    new PhoneNumber(twilioConfig.getSender_number()),"Hello, this is your verification code  to update your password account :"+
                    code).create();

            System.out.println(message.getSid());
        }
    public Message sendSMS(String toNumbre) {
        Twilio.init(twilioConfig.getAccount_sid(), twilioConfig.getAuth_token());

        Message msg = Message.creator(new PhoneNumber("+216" + toNumbre),
                new PhoneNumber(twilioConfig.getSender_number()),"Transaction Submited:  ").create();
        System.out.println(msg.getSid());
        return msg ;
    }


    }

