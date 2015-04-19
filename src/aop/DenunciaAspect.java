package aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import actions.Mail;

public class DenunciaAspect {
	public void enviarMail(JoinPoint jp) throws Throwable {
		System.out.println("Preparando el envio del mail");
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");	 
	    Mail md = (Mail) context.getBean("mailMail");		
        md.sendMail("infopool2015@gmail.com",
    		   "jpq.1987@gmail.com",
    		   "Probando Aspectos", 
    		   "Testeando Aspectos \n\n TTPS 2015");
        System.out.println("Mail enviado");
	}
}