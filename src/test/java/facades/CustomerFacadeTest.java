package facades;

import dtos.CustomerCoursesDTO;
import dtos.GoalDTO;
import entities.Customer;
import entities.CustomerCourses;
import entities.Goal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFacadeTest
{

    private static EntityManagerFactory emf;
    private static CustomerFacade facade;

    Goal goal1;
    Goal goal2;

    CustomerCourses cc1;
    CustomerCourses cc2;

    Customer customer1;
    Customer customer2;



    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CustomerFacade.getCustomerFacade(emf);


    }


    @BeforeEach
    void setUp()
    {
        EntityManager em = emf.createEntityManager();

        customer1 = new Customer("Simon", "Lukas");
        customer2 = new Customer("Lukas", "Simon");

        cc1 = new CustomerCourses();
        cc2 = new CustomerCourses();

        goal1 = new Goal(5, "1-5-2022");
        goal2 = new Goal(10, "20-6-2023");

        cc1.setCustomer(customer1);
        cc2.setCustomer(customer2);

        em.getTransaction().begin();
        em.createNamedQuery("customer_courses.deleteAllRows").executeUpdate();
        em.createNamedQuery("goal.deleteAllRows").executeUpdate();
        em.createNamedQuery("customer.deleteAllRows").executeUpdate();
        em.persist(customer1);
        em.persist(customer2);
        em.persist(cc1);
        em.persist(cc2);
        em.persist(goal1);
        em.persist(goal2);
        em.getTransaction().commit();

    }


    @AfterEach
    void tearDown()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("customer_courses.deleteAllRows").executeUpdate();
        em.createNamedQuery("goal.deleteAllRows").executeUpdate();
        em.createNamedQuery("customer.deleteAllRows").executeUpdate();
        em.getTransaction().commit();

    }


    @Test
    void getGoalById()
    {
        int goal_id = goal1.getId();
        String expected = "1-5-2022";
        String actual = facade.getGoalById(goal_id).getDto_finishDate();
        assertEquals(expected, actual);

    }

    @Test
    void getCustomerCourses()
    {
        int customer_id = customer1.getId();
        String expected = (cc1.getCustomer().getEmail());
        String actual = facade.getCustomerCourses(customer_id).get(0).getDto_Customer().getEmail();

        assertEquals(expected, actual);

    }

    @Test
    void createGoalOnCustomerCourse()
    {

        GoalDTO goal = new GoalDTO(15, "01-01-2023");
        String expected = "Goal er oprettet";
        String actual = facade.createGoalOnCustomerCourse(goal, cc2.getId());
        assertEquals(expected, actual);
    }
}