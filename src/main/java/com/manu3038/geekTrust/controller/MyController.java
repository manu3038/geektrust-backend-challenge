package com.manu3038.geekTrust.controller;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.repository.ParentRepository;
import com.manu3038.geekTrust.repository.PersonRepository;
import com.manu3038.geekTrust.service.HusbandService;
import com.manu3038.geekTrust.service.PersonService;
import com.manu3038.geekTrust.service.WifeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Controller
public class MyController {
    private static PersonRepository personRepo;
    private static ParentRepository parentRepo;
    private static PersonService personService;
    private static WifeService wifeService;
    private static HusbandService husbandService;
    public MyController(PersonRepository personRepo,ParentRepository parentRepo,
                        @Qualifier("personServiceImpl") PersonService personService,
                        @Qualifier("wifeServiceImpl") WifeService wifeService,
                        @Qualifier("husbandServiceImpl") HusbandService husbandService) {
        this.personRepo = personRepo;
        this.parentRepo = parentRepo;
        this.personService = personService;
        this.wifeService = wifeService;
        this.husbandService = husbandService;
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
}
