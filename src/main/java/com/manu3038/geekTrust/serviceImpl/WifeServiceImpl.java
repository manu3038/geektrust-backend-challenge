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
    public Person addChild(Person mother, Person newChild) {
        Person newlyAddedChild = new Person();
        if(super.personRepository.existsById(mother.getId())){ // mother is present in the tree
            Optional<Parent> isPresentInParent = super.parentRepository.findByMother(mother);
            if (isPresentInParent.isPresent()){ // if mother is already a parent use that
                newChild.setParent(isPresentInParent.get());
            } else { // else add her to parent first then make her parent for the child
                Parent newParentAdd = new Parent();
                newParentAdd.setMother(mother);
                Parent newlyAddedParent = super.parentRepository.save(newParentAdd);
                newChild.setParent(newlyAddedParent);
            }
            newlyAddedChild = super.personRepository.save(newChild);
        } else {
            System.out.println("PERSON_NOT_FOUND");
        }
        return newlyAddedChild;
    }
}
