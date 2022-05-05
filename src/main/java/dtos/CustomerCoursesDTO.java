package dtos;

import entities.Customer;
import entities.CustomerCourses;
import entities.Goal;

public class CustomerCoursesDTO
{
    private int dto_cCID;
    private Goal dto_goal;
    private Customer dto_Customer;

    public CustomerCoursesDTO()
    {
    }

    public CustomerCoursesDTO(int dto_cCID, Goal dto_goal, Customer dto_Customer)
    {
        this.dto_cCID = dto_cCID;
        this.dto_goal = dto_goal;
        this.dto_Customer = dto_Customer;
    }

    public CustomerCoursesDTO(Goal dto_goal, Customer dto_Customer)
    {
        this.dto_goal = dto_goal;
        this.dto_Customer = dto_Customer;
    }

    public CustomerCoursesDTO(CustomerCourses cc)
    {
        this.dto_cCID = cc.getId();
        this.dto_goal = cc.getGoal();
        this.dto_Customer = cc.getCustomer();
    }

    public int getDto_cCID()
    {
        return dto_cCID;
    }

    public void setDto_cCID(int dto_cCID)
    {
        this.dto_cCID = dto_cCID;
    }

    public Goal getDto_goal()
    {
        return dto_goal;
    }

    public void setDto_goal(Goal dto_goal)
    {
        this.dto_goal = dto_goal;
    }

    public Customer getDto_Customer()
    {
        return dto_Customer;
    }

    public void setDto_Customer(Customer dto_Customer)
    {
        this.dto_Customer = dto_Customer;
    }
}
