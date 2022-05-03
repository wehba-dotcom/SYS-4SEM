package dtos;

import entities.Customer;

public class CustomerDTO
{

    private int dto_customerId;
    private String dto_email;
    private String dto_password;


    public CustomerDTO(int dto_customerId, String dto_email, String dto_password)
    {
        this.dto_customerId = dto_customerId;
        this.dto_email = dto_email;
        this.dto_password = dto_password;
    }

    public CustomerDTO(String dto_email, String dto_password)
    {
        this.dto_email = dto_email;
        this.dto_password = dto_password;
    }

    public CustomerDTO(Customer c)
    {
        this.dto_customerId = c.getId();
        this.dto_email = c.getEmail();
        this.dto_password = c.getPassword();
    }

    public int getDto_customerId()
    {
        return dto_customerId;
    }

    public void setDto_customerId(int dto_customerId)
    {
        this.dto_customerId = dto_customerId;
    }

    public String getDto_email()
    {
        return dto_email;
    }

    public void setDto_email(String dto_email)
    {
        this.dto_email = dto_email;
    }

    public String getDto_password()
    {
        return dto_password;
    }

    public void setDto_password(String dto_password)
    {
        this.dto_password = dto_password;
    }
}
