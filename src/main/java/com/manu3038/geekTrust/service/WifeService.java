package com.manu3038.geekTrust.service;

import com.manu3038.geekTrust.domain.Person;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface WifeService extends PersonService {
    Person getHusband(Person wife);
    Set<Person> getChildren(Person mother);
    Person addChild(Person mother, String newPersonName, String newPersonGender);
}
