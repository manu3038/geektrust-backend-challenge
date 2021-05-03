package com.manu3038.geekTrust.domain;

import javax.persistence.*;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true,nullable = false)
    private Person mother;

    @OneToOne
    @JoinColumn(unique = true)
    private Person father;

    public Long getId() {
        return id;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", mother=" + mother +
                ", father=" + father +
                '}';
    }
}
