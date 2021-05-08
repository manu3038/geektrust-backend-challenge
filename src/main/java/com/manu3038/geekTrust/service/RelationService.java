package com.manu3038.geekTrust.service;

import com.manu3038.geekTrust.domain.Person;

import java.util.Set;

public interface RelationService {
    public Set<Person> getPaternalUncles(Person person);
    public Set<Person> getPaternalAunties(Person person);
    public Set<Person> getMaternalUncles(Person person);
    public Set<Person> getMaternalAunties(Person person);
    public Set<Person> getBotherInLaw(Person person);
    public Set<Person> getSisterInLaw(Person person);
}
