package com.manu3038.geekTrust;

import com.manu3038.geekTrust.controller.MyController;
import com.manu3038.geekTrust.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

@SpringBootApplication
public class GeekTrustApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(GeekTrustApplication.class, args);
		MyController ctr = (MyController) ctx.getBean("myController");
		String filePath = args[0].replace("\\","\\\\");
		System.out.println("command line file path is "+filePath);
		try {
			File file = ResourceUtils.getFile(filePath);
			System.out.println("Does file exists? => "+file.exists());
			String content = new String(Files.readAllBytes(file.toPath()));
			String fileDataLines[] = content.split("\n");
			for(String line : fileDataLines ){
				String[] listOfSplits = line.split(" ");
				String operation = listOfSplits[0].trim();
				if(ctr.getPersonByName(listOfSplits[1].trim().toLowerCase()) != null) {
					switch (operation) {
						case "GET_RELATIONSHIP":
							if (listOfSplits.length > 3) {
								System.err.println("ENTER_A_VALID_INPUT");
							} else {
								Set<Person> res = ctr.findRelationShip(listOfSplits[1].trim().toLowerCase(), listOfSplits[2].trim().toLowerCase());
								if (res.size() > 0) {
									for (Person p : res) {
										System.out.print(p.getName() + " ");
									}
								} else {
									System.out.print("NONE");
								}
								System.out.println();
							}
							break;
						case "ADD_CHILD":
							if (listOfSplits.length > 4) {
								System.err.println("ENTER_A_VALID_INPUT");
							} else {
								Boolean addRes = ctr.addChildFromMother(listOfSplits[1].toLowerCase().trim(),
										listOfSplits[2].toLowerCase().trim(), listOfSplits[3].toLowerCase().trim());
								if (addRes) {
									System.out.println("CHILD_ADDITION_SUCCEEDED");
								} else {
									System.out.println("CHILD_ADDITION_FAILED");
								}
							}
							break;
						default:
							System.err.println("ENTER_A_VALID_INPUT");
							break;
					}
				} else {
					System.err.println("PERSON_NOT_FOUND");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
