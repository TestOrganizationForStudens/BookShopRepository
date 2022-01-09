package com.example.demoBookShop.smsEmailSender;

import com.example.demoBookShop.models.User;

public interface MessageSender {
    void sendMessage(User user);
}
