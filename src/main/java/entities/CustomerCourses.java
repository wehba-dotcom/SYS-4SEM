package entities;

import javax.persistence.*;

@Entity
@Table(name = "customer_courses")
public class CustomerCourses
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerCourses_SEQ")
    @SequenceGenerator(name = "CustomerCourses_SEQ")
    @Column(name = "id", nullable = false)
    private int id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "goal_id")
    private Goal goal;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer()
    {
        return customer;
    }

    public Goal getGoal()
    {
        return goal;
    }

    public CustomerCourses()
    {
    }

    public CustomerCourses(Goal goal)
    {
        this.goal = goal;
    }

    public CustomerCourses(Goal goal, Customer customer)
    {
        this.goal = goal;
        this.customer = customer;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setGoal(Goal goal)
    {
        this.goal = goal;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}