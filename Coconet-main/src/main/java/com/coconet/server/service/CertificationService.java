package com.coconet.server.service;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

@Service
public class CertificationService {

    public void certifiedPhoneNumber(String phoneNumber, String authNumber) {

        String api_key = "NCSXIABEDV7LOHIV";
        String api_secret = "A6A2LETYWEGDAOU3Z9MVL4REU9UZBBOO";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", phoneNumber);    // 수신자 번호
        params.put("from", "01026207411");    // 발신자 번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("text", "[COCONET] 인증번호 " + "["+authNumber+"]" + "를 입력해주세요."); // 내용
        params.put("type", "SMS");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

}
