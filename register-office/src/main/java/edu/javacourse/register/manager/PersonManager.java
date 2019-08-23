package edu.javacourse.register.manager;

import edu.javacourse.register.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class PersonManager {

    public static void main(String[] args) {

        SessionFactory sf = buildSessionFactory();

        System.out.println();
        System.out.println();
        System.out.println();

        Session session = sf.openSession();

        session.getTransaction().begin();

        Person person = new Person();
        person.setFirstName("Василий");
        person.setLastName("Сидоров");

        Long id = (Long) session.save(person);
        System.out.println(id);
        session.getTransaction().commit();
        session.close();

        session = sf.openSession();
        Person person1 = session.get(Person.class, id);
        System.out.println(person1);
        session.close();

        session = sf.openSession();
        List<Person> list = session.createQuery("FROM Person", Person.class).list();
        list.forEach(person2 -> System.out.println(person2));
        session.close();

    }

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
