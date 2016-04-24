package org.zlx.mail;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class createMail {

	/**
	 * @param args
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		// TODO Auto-generated method stub
		createMsg();
	}
	public static void  createMsg() throws AddressException, MessagingException, IOException{
		Properties prop=new Properties();
		prop.setProperty("mail.transport.protocol","smtp");
		prop.setProperty("mail.smtp.auth","true");	
		
		Session session = Session.getDefaultInstance(prop); // 返回 单例对象 , 如果收和发在一起，容易出错的话
		session.setDebug(true);
		
		Message msg=new MimeMessage(session);
		     
		msg.setFrom(new InternetAddress("xxx@163.com"));
		msg.setSubject("test");
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("hh@163.com"));
		
		
		//构造复杂邮件
		Multipart msgMulPart=new MimeMultipart("mixed");
		msg.setContent(msgMulPart);
		
		MimeBodyPart bodypart=new MimeBodyPart();
		MimeBodyPart attpart=new MimeBodyPart();
		msgMulPart.addBodyPart(bodypart);   //邮件体
		msgMulPart.addBodyPart(attpart);    //附件
		
		 //附件
		attpart.attachFile("D:\\softwarebackup\\java\\javamail-1.4.7\\demo\\README.txt");
		attpart.setFileName("demoreadme.txt");
		
		Multipart bodyMutlPart=new MimeMultipart("related");
		//bodypart.setText("this email is crate myself");
		bodypart.setContent(bodyMutlPart);
		BodyPart htmlPart = new MimeBodyPart();
		BodyPart gifPart = new MimeBodyPart();
		bodyMutlPart.addBodyPart(htmlPart); //邮件体 的 html正文
		bodyMutlPart.addBodyPart(gifPart);  //邮件体 的 图片
		
		                                                            
		htmlPart.setContent("<html><head></head><body><h1>gogo</h1><br><img src='a'/></body></html>", "text/html");
		
		DataSource ds=new FileDataSource("D:\\bg.jpg");
		DataHandler dh=new DataHandler(ds);
		gifPart.setDataHandler(dh);
		gifPart.setHeader("Content-Location", "a"); // a 对应html正文中的内容
		
		
		
		OutputStream osf=new FileOutputStream("D:\\11.eml");
		msg.writeTo(osf);
		osf.close();	
	    System.out.println("write the email file to D:\\11.eml ");
	}
}
