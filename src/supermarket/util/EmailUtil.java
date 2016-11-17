package supermarket.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by ssthouse on 17/11/2016.
 */
public class EmailUtil {

    private static final String HOST_NAME = "smtp.163.com";
    private static final String PROTOCOL_NAME = "smtp";

    public static class EmailBean {
        String fromUser;
        String fromPassword;
        String toUser;
        String subject;
        String content;

        public EmailBean(String fromUser, String fromPassword, String toUser, String subject, String content) {
            this.fromUser = fromUser;
            this.fromPassword = fromPassword;
            this.toUser = toUser;
            this.subject = subject;
            this.content = content;
        }
    }

    public static void sendMail(EmailBean emailBean) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.debug", "true");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.host", HOST_NAME);
            props.setProperty("mail.transport.protocol", PROTOCOL_NAME);
            Session session = Session.getInstance(props);

            // 创建邮件对象
            Message msg = new MimeMessage(session);
            msg.setSubject(emailBean.subject);
            // 设置邮件内容
            msg.setText(emailBean.content);
            // 设置发件人
            msg.setFrom(new InternetAddress(emailBean.fromUser));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(emailBean.fromUser.split("@")[0], emailBean.fromPassword);
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress(emailBean.toUser)});
            // 关闭连接
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
