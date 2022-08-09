package pdp.com.appprojectexam.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Value("${twilio.accountSID}")
    private String twilioAccountSID;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;

    @Value("${twilio.trialNumber}")
    private String twilioTrialNumber;

    public boolean sendVerificationCode(String phoneNumber, String verificationCode) {
        try {
            Twilio.init(twilioAccountSID, twilioAuthToken);
            Message
                    .creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber(twilioTrialNumber),
                            "Your verification code: " + verificationCode + ". Buni hechkimga bermang aka!")
                    .create();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

