package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long personid;
    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public Set<Pet> pets;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

}
