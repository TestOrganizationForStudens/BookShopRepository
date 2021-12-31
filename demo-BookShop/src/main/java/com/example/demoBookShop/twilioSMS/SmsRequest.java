package com.example.demoBookShop.twilioSMS;

public class SmsRequest {
    private final String phoneNumber;
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("+4").append(phoneNumber);
        this.phoneNumber = stringBuilder.toString();
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
