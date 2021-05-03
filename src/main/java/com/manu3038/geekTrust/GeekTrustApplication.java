package com.manu3038.geekTrust;

import com.manu3038.geekTrust.controller.MyController;
import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GeekTrustApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(GeekTrustApplication.class, args);
		MyController ctr = (MyController) ctx.getBean("myController");
		System.out.println(ctr.getChildrenFromMother(ctr.getPersonById(Long.parseLong("13"))));
		System.out.println(ctr.getLengthOfPersonTable());
	}

}
