package com.example.demoBookShop.smsEmailSender;

import com.example.demoBookShop.models.User;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TwilioSmsSender implements MessageSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitiazer.class);
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendMessage(User user) {
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("+4").append(user.getPhone());
        String phoneNumber=stringBuilder.toString();
        PhoneNumber to = new PhoneNumber(phoneNumber);
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = getMessage();
        MessageCreator creator = Message.creator( to, from, message);
        creator.create();
        LOGGER.info("Send sms to {}, with message: {}", phoneNumber, message);
    }
    private String getMessage(){
        Random random = new Random();
        Integer randomInt=random.nextInt(100_000);
        return "Your BookShop activation code is: "+randomInt;
    }
}

