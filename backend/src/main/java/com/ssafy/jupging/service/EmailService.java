package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.handler.EmailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    private final UserService userService;

    private boolean lowerCheck;
    private int size;

    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return makeAuthKey();
    }

    private String makeAuthKey() {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do {
            num = ran.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < size);
        if (lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }

    public String sendAuthEmail(String email){
        String auth = getKey(5, true);

        try{
            EmailHandler mailHandler = new EmailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setSubject("[Jubging] 줍깅 회원가입 인증번호 안내");
            String message = makeContent(0, "메일인증",email, auth);
            mailHandler.setText(message, true);
            mailHandler.send();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return auth;
    }

    public void sendTempPwEmail(User user){
        String tempKey = getKey(10, true);

        try{
            EmailHandler mailHandler = new EmailHandler(mailSender);
            mailHandler.setTo(user.getEmail());
            mailHandler.setSubject("[Jubging] 줍깅 임시비밀번호 안내");
            String message = makeContent(1,"임시비밀번호 발급",user.getNickname(), tempKey);
            mailHandler.setText(message, true);
            mailHandler.send();

            userService.changePassword(user.getUserId(), tempKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeContent(int n, String title, String nickname, String key) {
        StringBuffer sb = new StringBuffer();

        sb.append(
                        "<!DOCTYPE html><html><body>"
                        + "<div style=\"font-family: 'Apple SD Gothic Neo' , 'sans-serif'; width:600px; height:600px; border-top:5px solid #00bfa5; margin:10px auto; padding:30px 0; box-sizing:border-box;\">"
                        + "<h1 style=\"marginx:0; padding:0 5px; font-size:28px; font-weight:400;\">"
                        + "<span style=\"font-size:15px; margin: 0 0 10px 3px;\">JUBGING</span><br />"
                        + "<span style=\"color:00bfa5\">"
                        + title
                        + " 안내입니다.</h1>"
                        + "<p style=\"font-size:16px; line-height:26px; margin-top:50px; padding:0 5px;\">"
                        +  nickname
                        + " 님 안녕하세요. <br />"
        );

        if(n==0){ //회원가입
            sb.append(
                    "저희 줍깅에 가입해주셔서 진심으로 감사합니다. <br />"
                    + "아래 <b style=\"color:#00bfa5\"> '인증번호'</b>를 입력하여 회원가입을 완료해 주세요.<br />"
                    + "감사합니다</p>"
            );
        }
        else{ //임시비밀번호
            sb.append(
                    "임시 비밀번호를 발급해 드립니다. <br />"
                    + "아래 <b style=\"color:#00bfa5\"> '임시비밀번호'</b>로 로그인하여 비밀번호 변경을 진행해 주세요.<br />"
                    + "감사합니다</p>"
            );
        }

        sb.append(
                "<p style=\"display:inline-block; width:210px; height:45px; margin:30px auto; background: #00bfa5; vertical-align:middle; font-size:25px; text-align:center; color:#FFFFFF\">"
                + key
                + "</p>"

                + "<div style=\"border-top:1px solid #DDD; padding:5px;\"></div>"
                + "</div>"
                + "</body></html>"
        );



        return sb.toString();
    }
}
