package com.manu3038.geekTrust.serviceImpl;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.service.HusbandService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HusbandServiceImpl extends PersonServiceImpl implements HusbandService  {
    @Override
    public Person getWife(Person husband) {
        Optional<Parent> res =  super.parentRepository.findByFather(husband);
        return res.isPresent() ? res.get().getMother() : null;
    }
}
