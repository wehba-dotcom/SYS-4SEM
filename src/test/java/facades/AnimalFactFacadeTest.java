package facades;

import dtos.AnimalFactDTO;
import dtos.AnimalTypeDTO;
import entities.AnimalFact;
import entities.AnimalType;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class AnimalFactFacadeTest {

    private static EntityManagerFactory emf;
    private static AnimalFactFacade facade;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AnimalFactFacade.getFacadeExample(emf);
    }


    @AfterAll
    public static void tearDownClass() {


    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            //Delete existing users and roles to get a "fresh" database
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
           em.createNamedQuery("AnimalFact.deleteAllRows").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            User user = new User("user", "test");
            user.addRole(userRole);
            User admin = new User("admin", "test");
            admin.addRole(adminRole);
            User both = new User("user_admin", "test");
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("AnimalFact.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAnimalFact() throws ExecutionException, InterruptedException {

        AnimalFactDTO animalFactDTO = facade.getAnimalFact("cat");

        if(animalFactDTO.getFact() != null) {

            equals(true);
        }

        else equals(fail("animalFactDTO is null"));

    }

    @Test
    void addFactToHistory() {
        AnimalTypeDTO typeDTO = new AnimalTypeDTO("cat");

        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO,"this is a test fact");
        facade.addFactToHistory("user",animalFactDTO);

        assertEquals(1,facade.getAnimalFactCount());

    }

    @Test
    void getFactHistory() {
        AnimalTypeDTO typeDTO = new AnimalTypeDTO("cat");
        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO,"fact1");
        AnimalFactDTO animalFactDTO2 = new AnimalFactDTO(typeDTO,"fact2");
        AnimalFactDTO animalFactDTO3 = new AnimalFactDTO(typeDTO,"fact3");
        AnimalFactDTO animalFactDTO4 = new AnimalFactDTO(typeDTO,"fact4");
        facade.addFactToHistory("user",animalFactDTO);
        facade.addFactToHistory("user",animalFactDTO2);
        facade.addFactToHistory("user",animalFactDTO3);
        facade.addFactToHistory("user",animalFactDTO4);


       List<AnimalFactDTO> factHistory = facade.getFactHistory("user");


       assertEquals(4,factHistory.size());


    }
}