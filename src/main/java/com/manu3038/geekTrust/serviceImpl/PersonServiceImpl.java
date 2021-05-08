package com.manu3038.geekTrust.serviceImpl;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.repository.ParentRepository;
import com.manu3038.geekTrust.repository.PersonRepository;
import com.manu3038.geekTrust.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ParentRepository parentRepository;

    @Override
    public Person getByName(String name) {
        Optional<Person> result = personRepository.findByName(name);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Person getById(Long id) {
        Optional<Person> result = personRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Set<Person> getBrothers(Person person) {
        Set<Person> brothers = new HashSet<>();
        if(person != null && person.getParent() != null) {
            Long parentId = person.getParent().getId();
            Set<Person> siblings = personRepository.findAllByParentId(parentId);
            brothers = siblings.stream().filter(e -> e.getId() != person.getId() && e.getGender().equalsIgnoreCase("M")).collect(Collectors.toSet());
        }
        return brothers;
    }

    @Override
    public Set<Person> getSisters(Person person) {
        Set<Person> sisters = new HashSet<>();
        if(person != null && person.getParent() !=null){
            Long parentId = person.getParent().getId();
            Set<Person> siblings = personRepository.findAllByParentId(parentId);
            sisters  = siblings.stream().filter(e->e.getId() != person.getId() && e.getGender().equalsIgnoreCase("F")).collect(Collectors.toSet());
        }
        return sisters;
    }

    @Override
    public Set<Person> getSon(Person person) {
        Optional<Parent> parentEntry = null;
        Set<Person> res = new HashSet<>();
        if(person!=null) {
            if (person.getGender().equalsIgnoreCase("m")) {
                parentEntry = parentRepository.findByFather(person);
            } else if (person.getGender().equalsIgnoreCase("f")) {
                parentEntry = parentRepository.findByMother(person);
            }
            if (parentEntry.isPresent()) {
                res = personRepository.findAllByParentId(parentEntry.get().getId()).stream()
                        .filter(e -> e.getGender().equalsIgnoreCase("m")).collect(Collectors.toSet());
            }
        }
        return res;
    }

    @Override
    public Set<Person>  getDaughter(Person person) {
        Optional<Parent> parentEntry = null;
        Set<Person> res = new HashSet<>();
        if(person!= null) {
            if (person.getGender().equalsIgnoreCase("m")) {
                parentEntry = parentRepository.findByFather(person);
            } else if (person.getGender().equalsIgnoreCase("f")) {
                parentEntry = parentRepository.findByMother(person);
            }
            if (parentEntry.isPresent()) {
                res = personRepository.findAllByParentId(parentEntry.get().getId()).stream()
                        .filter(e -> e.getGender().equalsIgnoreCase("f")).collect(Collectors.toSet());
            }
        }
        return res;
    }


}
