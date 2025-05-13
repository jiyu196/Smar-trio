package app;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static util.TrioUtils.*;


public class JavaMailSender extends App{
	
	
	public JavaMailSender(int appNo) {
		super(appNo, "메일 발송", true);
	}

	public static void main(String[] args) throws AddressException, MessagingException {
		
		final String id = "tiger2ys98@gmail.com";
		final String pw = "httyqnfhvnpepmvi";
		
		
		
		
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, pw);
			}
		});
		System.out.println(session);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(id));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("imtaehoonkim@gmail.com"));
		
		message.setSubject("자바 메일 발송 테스트");
		message.setText("java mail 1.6기반 내용");
		Transport.send(message);
		
		System.out.println("done.");
		
	}

	@Override
	public void run() {
		while(true) {
			
			int no = nextInt("사용할 기능을 선택해주세요. 1. 주소록에서 메모 선택 2.메모직접 입력 0.되돌아가기");
			switch (no) {
			case 1: 
				
				break;
			case 2:	
			
				break;
			case 0:
				
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}
			
		}
	}
	
	public void viewList() {
		
	}
	
}
