package com.manu3038.geekTrust.service;

import com.manu3038.geekTrust.domain.Person;
import org.springframework.stereotype.Service;

@Service
public interface HusbandService extends PersonService {
    Person getWife(Person husband);
}
