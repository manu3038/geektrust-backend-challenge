package com.manu3038.geekTrust.serviceImpl;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import com.manu3038.geekTrust.service.WifeService;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class WifeServiceImpl extends PersonServiceImpl implements WifeService {
    @Override
    public Person getHusband(@NotNull Person wife) {
        Optional<Parent> res =  super.parentRepository.findByMother(wife);
        return res.isPresent() ? res.get().getFather() : null;
    }

    @Override
    public Set<Person> getChildren(@NotNull Person mother) {
        Optional<Parent> res =  super.parentRepository.findByMother(mother);
        return res.isPresent() ? super.personRepository.findAllByParentId(res.get().getId()) : null;
    }

    @Override
    public Person addChild(Person mother, String newPersonName, String newPersonGender) {
        Person newlyAddedChild = null;
        Person child = new Person();
        if(mother.getGender().equalsIgnoreCase("f")) {
            Optional<Parent> isPresentInParent = super.parentRepository.findByMother(mother);
            if (isPresentInParent.isPresent()) { // if mother is already a parent use that
                child.setParent(isPresentInParent.get());
            } else { // else add her to parent first then make her parent for the child
                Parent newParentAddObj = new Parent();
                newParentAddObj.setMother(mother);
                Parent newlyAddedParent = super.parentRepository.save(newParentAddObj);
                child.setParent(newlyAddedParent);
            }
            child.setName(newPersonName);
            child.setGender(newPersonGender);
            newlyAddedChild = super.personRepository.save(child);
        }
        return newlyAddedChild;
    }
}
