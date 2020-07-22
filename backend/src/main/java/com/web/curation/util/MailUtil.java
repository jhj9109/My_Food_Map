package com.web.curation.util;
import java.util.Random;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class MailUtil {
	public void sendMail(String userEmail, String subject, String msg) throws Exception{
		
		//mail server 설정 
 		String charSet ="utf-8";

		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "wkhhambatt@gmail.com";//id
		String hostSMTPpwd = "1024batt!";
		
		String fromEmail = "wkhhambatt@gmail.com";
		String fromName = "ssafysns";	


		
		HtmlEmail mail = new HtmlEmail();
		mail.setDebug(true);
		mail.setCharset(charSet);
		mail.setHostName(hostSMTP);
		mail.setSmtpPort(587);

		mail.setAuthenticator(new DefaultAuthenticator(hostSMTPid, hostSMTPpwd));
		mail.setStartTLSEnabled(true);
		mail.addTo(userEmail);
		mail.setFrom(fromEmail,fromName,charSet);
		mail.setSubject(subject);
		mail.setHtmlMsg(msg);
		mail.send();
		
		
	}

	
	public String CreateAuthCode(){
		StringBuffer sbuff = new StringBuffer();
		Random rnd = new Random();
		
		for(int i=0; i<10; i++) {
			int randomIdx = rnd.nextInt(3);//0~2중 뽑는다 . 이메일 인증 코드 같은경우 대소문자와 숫자로만 이루어지게 했다.
			
			if(randomIdx==0) {//0은 a-z
				sbuff.append((char)((int) rnd.nextInt(26) +97));
			}else if(randomIdx==1) {//1은 A-Z
				sbuff.append((char) ((int) rnd.nextInt(26) +65));
			}else if(randomIdx==2) {//2는 0-9
				sbuff.append(rnd.nextInt(26));
			}
		}
		
		return sbuff.toString();
	}
}
