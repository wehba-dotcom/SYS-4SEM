package facades;

import dtos.CustomerCoursesDTO;
import dtos.GoalDTO;
import entities.CustomerCourses;
import entities.Goal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade
{
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade()
    {
    }


    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }


    public GoalDTO getGoalById(int id)
    {
        EntityManager em = getEntityManager();
        return new GoalDTO(em.find(Goal.class, id));
    }

    public List<CustomerCoursesDTO> getCustomerCourses(int id)
    {
        EntityManager em = getEntityManager();
        TypedQuery<CustomerCourses> query = em.createQuery("SELECT c FROM CustomerCourses c where c.customer.id=:id", CustomerCourses.class);
        query.setParameter("id", id);
        List<CustomerCourses> customerCoursesList = query.getResultList();
        List<CustomerCoursesDTO> customerCoursesDTOList = new ArrayList<>();

        for (int i = 0; i < customerCoursesList.size(); i++)
        {
            CustomerCoursesDTO coursesDTO = new CustomerCoursesDTO(customerCoursesList.get(i));
            customerCoursesDTOList.add(coursesDTO);
        }
        return customerCoursesDTOList;
    }


    public String createGoalOnCustomerCourse(GoalDTO goalDTO, int id)
    {
        EntityManager em = getEntityManager();
        CustomerCourses cc = em.find(CustomerCourses.class, id);
        Goal goal = new Goal(goalDTO);

        cc.setGoal(goal);

        em.getTransaction().begin();
        em.persist(cc);
        em.getTransaction().commit();

        return "Goal er oprettet";

    }

}
