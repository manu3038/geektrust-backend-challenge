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
		Person p = new Person();
		p.setGender("F");
		p.setName("chitra");
		p.setParent(null);
		Person savedPerson  = ctr.addPerson(p);
		Person father = ctr.getPersonByName("aras");
		Parent parent = new Parent();
		parent.setFather(father);
		parent.setMother(savedPerson);
		Parent savedParent = ctr.addParent(parent);
		System.out.println(ctr.getChildrenFromMother(ctr.getPersonById(Long.parseLong("13"))));
		System.out.println(ctr.getLengthOfPersonTable());
	}

}
