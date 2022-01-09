package com.example.demoBookShop.smsEmailSender;

import com.example.demoBookShop.models.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SendInformationToUserSmsEmailPublisher {

    private List<MessageSender> messageSenders= Arrays.asList(
            new EmailSender(),
            new TwilioSmsSender(new TwilioConfiguration())
    );

    public void sendMessageToUserSmsEmail(User user){
        messageSenders.forEach(sendMesageService-> sendMesageService.sendMessage(user));
    }
}
