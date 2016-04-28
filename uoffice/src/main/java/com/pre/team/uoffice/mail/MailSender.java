/**
 * 
 */
package com.pre.team.uoffice.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.SimpleMail;
import com.pre.team.uoffice.util.MailConfigureUtil;

/**
 * Description:邮件发送
 * @author xuejiahao  2015年7月17日
 * 
 */
public class MailSender {
	/**
     * 发送邮件的props文件
     */
    public final static Properties props = MailConfigureUtil.init();
    /**
     * 邮件服务器登录验证
     */
    private  transient MailAuthenticator authenticator;
 
    /**
     * 邮箱session
     */
    private  transient Session session;
 
    /**
     * 初始化邮件发送
     */
    public MailSender() {
    	init(Constants.MAIL_USERNAME, Constants.MAIL_PASSWORD, Constants.MAIl_SMTP_ADDRESS);
    }
 
    /**
     * 
     * @param username
     * @param password
     */
    public MailSender(final String username, final String password) {
    //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
    	final String smtpHostName = "smtp." + username.split("@")[1];
    	init(username, password, smtpHostName);
 
    }
 
    /**
     * 
     * Description:初始化  2015年7月17日
     * 
     * @author xuejiahao
     * @param username
     * @param password
     * @param smtpHostName
     */
    private void init(String username, String password, String smtpHostName) {
	    // 初始化props
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.host", smtpHostName);
//	    props.put("mail.smtp.auth", "true");
	    // 验证
	    authenticator = new MailAuthenticator(username, password);
	    // 创建session
	    session = Session.getDefaultInstance(props, authenticator);
    }
 
    /**
     * 
     * Description:发送邮件  2015年7月17日
     * 
     * @author xuejiahao
     * @param recipient
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException
     */
    private  void send(String recipient, String subject, Object content)
        throws AddressException, MessagingException {
	    // 创建mime类型邮件
	    MimeMessage message = new MimeMessage(session);
	    // 设置发信人
	    message.setFrom(new InternetAddress(authenticator.getUsername()));
	    // 设置收件人
	    message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
	    // 设置主题
	    message.setSubject(subject);
	    // 设置邮件内容
	    message.setContent(content.toString(), "text/html;charset=utf-8");
	    // 设置发送时间
	    message.setSentDate(new Date());
	    //从session中取mail.smtp.protocol指定协议的Transport  
        Transport transport=session.getTransport();  
        //建立与指定的SMTP服务器的连接  
        transport.connect();//此时不需要任务参数  
       //发给所有指定的收件人,若使用message.getAllRecipients()则还包括抄送和暗送的人  
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));  
        //关闭连接  
        transport.close();
    }
 
    /**
     * 
     * Description:群发邮件  2015年7月17日
     * 
     * @author xuejiahao
     * @param recipients
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException
     */
    private  void send(List<String> recipients, String subject, Object content)
        throws AddressException, MessagingException {
	    // 创建mime类型邮件
	    final MimeMessage message = new MimeMessage(session);
	    // 设置发信人
	    message.setFrom(new InternetAddress(authenticator.getUsername()));
	    // 设置收件人们
	    final int num = recipients.size();
	    InternetAddress[] addresses = new InternetAddress[num];
	    for (int i = 0; i < num; i++) {
	        addresses[i] = new InternetAddress(recipients.get(i));
	    }
	    message.setRecipients(RecipientType.TO, addresses);
	    // 设置主题
	    message.setSubject(subject);
	    // 设置邮件内容
	    message.setContent(content.toString(), "text/html;charset=utf-8");
	    // 发送
	 // 设置发送时间
	    message.setSentDate(new Date());
	    //从session中取mail.smtp.protocol指定协议的Transport  
        Transport transport=session.getTransport();  
        //建立与指定的SMTP服务器的连接  
        transport.connect();//此时不需要任务参数  
       //发给所有指定的收件人,若使用message.getAllRecipients()则还包括抄送和暗送的人  
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));  
        //关闭连接  
        transport.close();
    }
 
    /**
     * 
     * Description:单发邮件  2015年7月17日
     * 
     * @author xuejiahao
     * @param recipient 收件人邮箱地址
     * @param mail 邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public  void send(String recipient, SimpleMail mail)
        throws AddressException, MessagingException {
    	send(recipient, mail.getSubject(), mail.getContent());
    }
 
    /**
     * 
     * Description:群发邮件  2015年7月17日
     * 
     * @author xuejiahao
     * @param recipients
     * @param mail
     * @throws AddressException
     * @throws MessagingException
     */
    public  void send(List<String> recipients, SimpleMail mail)
        throws AddressException, MessagingException {
    	send(recipients, mail.getSubject(), mail.getContent());
    }
}
