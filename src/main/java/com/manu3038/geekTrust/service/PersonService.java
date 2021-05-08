package com.manu3038.geekTrust.service;

import com.manu3038.geekTrust.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface PersonService {
    Person getByName(String name);
    Person getById(Long id);
    Set<Person> getBrothers(Person person);
    Set<Person> getSisters(Person person);
    Set<Person> getSon(Person person);
    Set<Person>  getDaughter(Person person);
}
