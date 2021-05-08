package com.manu3038.geekTrust.serviceImpl;

import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.repository.ParentRepository;
import com.manu3038.geekTrust.repository.PersonRepository;
import com.manu3038.geekTrust.service.HusbandService;
import com.manu3038.geekTrust.service.PersonService;
import com.manu3038.geekTrust.service.RelationService;
import com.manu3038.geekTrust.service.WifeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RelationServiceImpl implements RelationService {
    private static PersonRepository personRepo;
    private static ParentRepository parentRepo;
    private static PersonService personService;
    private static WifeService wifeService;
    private static HusbandService husbandService;
    public RelationServiceImpl(PersonRepository personRepo,ParentRepository parentRepo,
                               @Qualifier("personServiceImpl") PersonService personService,
                               @Qualifier("wifeServiceImpl") WifeService wifeService,
                               @Qualifier("husbandServiceImpl") HusbandService husbandService) {
        this.personRepo = personRepo;
        this.parentRepo = parentRepo;
        this.personService = personService;
        this.wifeService = wifeService;
        this.husbandService = husbandService;
    }

    @Override
    public Set<Person> getPaternalUncles(Person person) {
        return personService.getBrothers(person.getParent().getFather());
    }

    @Override
    public Set<Person> getPaternalAunties(Person person) {
        return personService.getSisters(person.getParent().getFather());
    }

    @Override
    public Set<Person> getMaternalUncles(Person person) {
        return personService.getBrothers(person.getParent().getMother());
    }

    @Override
    public Set<Person> getMaternalAunties(Person person) {
        return personService.getSisters(person.getParent().getMother());
    }

    @Override
    public Set<Person> getBotherInLaw(Person person) {
        Set<Person> res = new HashSet<>();
        // get sister's husbands
        res.addAll(personService.getSisters(person).stream().map(e -> wifeService.getHusband(e)).collect(Collectors.toSet()));
        if(person.getGender().equalsIgnoreCase("m")){
            // wife's brothers
            Person wife = husbandService.getWife(person);
            if(wife!=null) {
                res.addAll(personService.getBrothers(wife));
            }
        } else if(person.getGender().equalsIgnoreCase("f")){
            // get husband's brothers
            Person husband = wifeService.getHusband(person);
            if(husband != null){
                res.addAll(personService.getBrothers(husband));
            }
        }
        return res.stream().filter(e -> e!=null).collect(Collectors.toSet());
    }

    @Override
    public Set<Person> getSisterInLaw(Person person) {
        Set<Person> res = new HashSet<>();
        if (person!=null) {
            // get brothers' wifes
            res.addAll(personService.getBrothers(person).stream().map(bro->husbandService.getWife(bro)).collect(Collectors.toSet()));
            if (person.getGender().equalsIgnoreCase("f")) {
                // get husband's sisters
                Person husband = wifeService.getHusband(person);
                if(husband != null) {
                    res.addAll(personService.getSisters(husband));
                }
            } else if (person.getGender().equalsIgnoreCase("m")) {
                // get wife's sisters
                Person wife = husbandService.getWife(person);
                if (wife != null) {
                    res.addAll(personService.getSisters(wife));
                }
            }
        }
        return res.stream().filter(e -> e!=null).collect(Collectors.toSet());
    }
}
