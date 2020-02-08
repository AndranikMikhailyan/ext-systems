package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {

    @Test
    public void findPersons() {
        PersonDao personDao = new PersonDao();
        List<Person> persons = personDao.findPersons();

        persons.forEach(person -> {
            System.out.println("Name: " + person.getFirstName());
            System.out.println("Class for sex: " + person.getClass().getName());
            System.out.println("Passports size: " + person.getPassports().size());
            System.out.println("Birth: " + person.getBirthCertificate());
            if (person instanceof PersonMale) {
                System.out.println("Birth cert: " + ((PersonMale) person).getBirthCertificates().size());
                System.out.println("Marriage cert:" + ((PersonMale) person).getMarriageCertificates().size());
            } else {
                System.out.println("Birth cert: " + ((PersonFemale) person).getBirthCertificates().size());
                System.out.println("Marriage cert:" + ((PersonFemale) person).getMarriageCertificates().size());
            }
        });
    }
}