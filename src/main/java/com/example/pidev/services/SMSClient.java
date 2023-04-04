package com.example.pidev.services;


import com.example.pidev.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SMSClient {

  @Autowired
    private TwilioConfig twilioConfig;

    //SMS
    public void SendSMS(String toNumber){
        // Find your Account Sid and Token at twilio.com/user/account
        Twilio.init(twilioConfig.getAccount_sid(), twilioConfig.getAuth_token());

        Message message = Message.creator(new PhoneNumber("+216" + toNumber),
                new PhoneNumber(twilioConfig.getSender_number()),"Bonjour, votre projet a ete accepter felicitation :").create();

        System.out.println(message.getSid());
    }

    public void SendSMSs(String toNumber){
        // Find your Account Sid and Token at twilio.com/user/account
        Twilio.init(twilioConfig.getAccount_sid(), twilioConfig.getAuth_token());

        Message message = Message.creator(new PhoneNumber("+216" + toNumber),
                new PhoneNumber(twilioConfig.getSender_number()),"Bonjour, vous avez une nouvelle demande de partenaria payer ").create();

        System.out.println(message.getSid());
    }


}

