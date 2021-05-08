package com.manu3038.geekTrust.controller;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.repository.ParentRepository;
import com.manu3038.geekTrust.repository.PersonRepository;
import com.manu3038.geekTrust.service.HusbandService;
import com.manu3038.geekTrust.service.PersonService;
import com.manu3038.geekTrust.service.RelationService;
import com.manu3038.geekTrust.service.WifeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MyController {
    private static PersonRepository personRepo;
    private static ParentRepository parentRepo;
    private static PersonService personService;
    private static WifeService wifeService;
    private static HusbandService husbandService;
    private static RelationService  relationService;
    public MyController(PersonRepository personRepo,ParentRepository parentRepo, RelationService relationService,
                        @Qualifier("personServiceImpl") PersonService personService,
                        @Qualifier("wifeServiceImpl") WifeService wifeService,
                        @Qualifier("husbandServiceImpl") HusbandService husbandService) {
        this.personRepo = personRepo;
        this.parentRepo = parentRepo;
        this.personService = personService;
        this.wifeService = wifeService;
        this.husbandService = husbandService;
        this.relationService = relationService;
    }
    public Integer getLengthOfPersonTable(){
        List<Person> objx = personRepo.findAll();
        return objx.size();
    }
    public Person addPerson(Person person){
        return personRepo.save(person);
    }
    public Parent addParent(Parent parent){
        return parentRepo.save(parent);
    }
    public Person getPersonByName(String name){
        return personService.getByName(name);
    }
    public Person getPersonById(Long id){
        return personService.getById(id);
    }
    public Set<Person> getBrothers(Person person){
        return personService.getBrothers(person);
    }
    public Person getHusbandFromWife(Person person){
        return wifeService.getHusband(person);
    }
    public Set<Person> getChildrenFromMother(Person mother){
        return wifeService.getChildren(mother);
    }
    public Person getWifeFromHusband(Person person){
        return husbandService.getWife(person);
    }
    public Set<Person> getSisters(Person person){
        return personService.getSisters(person);
    }
    public Set<Person> findRelationShip(String personName, String relationString){
        // get the person name.
        // get the relationship to be found.
        // based on the relationship send person to get the answer
        String relation = relationString.split("\\r")[0];
        Person enteredPerson = this.getPersonByName(personName);
        Set<Person> res = new HashSet<>();
        if(enteredPerson != null){
            if(relation.equals("son")){
                res = personService.getSon(enteredPerson);
            } else if(relation.equals("daughter")){
                res = personService.getDaughter(enteredPerson);
            } else if(relation.equalsIgnoreCase("Paternal-Uncle")){
                res = relationService.getPaternalUncles(enteredPerson);
            }else if(relation.equalsIgnoreCase("Paternal-aunt")){
                res = relationService.getPaternalAunties(enteredPerson);
            }else if(relation.equalsIgnoreCase("Maternal-Uncle")){
                res = relationService.getMaternalUncles(enteredPerson);
            }else if(relation.equalsIgnoreCase("Maternal-aunt")){
                res = relationService.getMaternalAunties(enteredPerson);
            }else if(relation.equalsIgnoreCase("brother-in-law")){
                res = relationService.getBotherInLaw(enteredPerson);
            }else if(relation.equalsIgnoreCase("sister-in-law")){
                res = relationService.getSisterInLaw(enteredPerson);
            }else if(relation.equalsIgnoreCase("siblings")){
                Set<Person> bros = personService.getBrothers(enteredPerson);
                Set<Person> sis = personService.getSisters(enteredPerson);
                res = Stream.concat(bros.stream(),sis.stream()).collect(Collectors.toSet());
            }
        }

        return res;
    }
    public Boolean addChildFromMother(String mothersName, String newPersonName, String newPersonGender){
        Person mother = getPersonByName(mothersName);
        if(mother != null && mother.getGender().equalsIgnoreCase("f")){
            String gender = newPersonGender.split("")[0].toUpperCase().equals("M") ? "M" : newPersonGender.split("")[0].toUpperCase().equals("F") ? "F": null;
            if( gender != null && newPersonName != null ){
                Person savedChild = wifeService.addChild(mother,newPersonName,gender);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
