package com.example.demoBookShop.twilioSMS;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {

    private String accountSid="AC5d262e10e439106c6039c011a6f5449e";
    private String authToken="a1bcc37782a1462cb96beaa6ea627ff5";
    private String trialNumber="+16672132313";

    public TwilioConfiguration() {
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(String trialNumber) {
        this.trialNumber = trialNumber;
    }
}
