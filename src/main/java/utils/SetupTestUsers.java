package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("Hans", "test1");
    User admin = new User("admin", "test2");
    User both = new User("user_admin", "test3");


    Customer customer = new Customer("Simon@Lukas", "HejMedDig");
    Goal goal = new Goal(5, "1-05-2022");
    Goal goal2 = new Goal(10, "1-06-2022");

    CustomerCourses cc = new CustomerCourses("Cappuccino");
    CustomerCourses cc2 = new CustomerCourses("Caffe-latte");
    CustomerCourses cc3 = new CustomerCourses("Moccachino");





    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(customer);
    em.persist(cc);
    em.persist(cc2);
    em.persist(cc3);
    cc.setCustomer(customer);
    cc3.setCustomer(customer);
    cc2.setCustomer(customer);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
