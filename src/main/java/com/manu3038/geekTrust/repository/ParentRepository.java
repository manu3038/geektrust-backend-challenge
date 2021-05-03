package com.manu3038.geekTrust.repository;

import com.manu3038.geekTrust.domain.Parent;
import com.manu3038.geekTrust.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByMother(Person wife);

    Optional<Parent> findByFather(Person husband);
}
