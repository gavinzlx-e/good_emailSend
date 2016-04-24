package main;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
	
	/**
	 * @param args
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		send2();
	}
	public static void  send2() throws AddressException, MessagingException{
		Properties prop=new Properties();
		prop.setProperty("mail.transport.protocol","smtp");
		prop.setProperty("mail.smtp.auth","true");	
		
		Session session = Session.getDefaultInstance(prop); // 返回 单例对象 , 如果收和发在一起，容易出错的话
		//Session session = Session.getInstance(prop); // 返回新的对象，
		session.setDebug(true);
		
		
		
		Message msg=new MimeMessage(session);
		//邮件中显示的 发件人		//说没有权限发送邮件
		//msg.setFrom(new InternetAddress("lili@163.com"));     
		msg.setFrom(new InternetAddress("xxxx@163.com"));
		msg.setText("this is tet");
		msg.setSubject("test");
		//邮件中显示的收件人
		//（如果群发,则不在这里写，人多不好写
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("hh@163.com"));
		/*
		static Message.RecipientType	BCC
		The "Bcc" (blind carbon copy) recipients.
		static Message.RecipientType	CC
		The "Cc" (carbon copy) recipients.
		static Message.RecipientType	TO
		The "To" (primary) recipients.
		*/
		Transport trans= session.getTransport();
		trans.connect("smtp.163.com",25,"xxxx","xxxx");
		// 发送到下列地址。也就是收件人
		trans.sendMessage(msg, new Address[]{new InternetAddress("xxxxx@163.com")} ); 
		trans.close();
	}
}
