package entities;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_SEQ")
    @SequenceGenerator(name = "Customer_SEQ")
    @Column(name = "id", nullable = false)
    private int id;
    private String email;
    private String password;

    public Customer()
    {
    }

    public Customer(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}