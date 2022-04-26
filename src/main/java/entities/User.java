package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.deleteAllRows", query = " DELETE FROM User ")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList<>();

    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    @JoinTable(name = "factHistory")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<AnimalFact> factHistory;


    public List<AnimalFact> getFactHistory() {
        return factHistory;
    }

    @JoinTable(name = "savedFacts")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<AnimalFact> savedFacts;

    public List<AnimalFact> getSavedFacts() {
        return savedFacts;
    }

    public void setSavedFacts(List<AnimalFact> savedFacts) {
        this.savedFacts = savedFacts;
    }

    public void addFactToSavedFacts(AnimalFact animalFact) {

        savedFacts.add(animalFact);

    }

    public User() {
    }

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw) {

        return (BCrypt.checkpw(pw, userPass));


    }

    public User(String userName, String userPass) {
        this.userName = userName;

        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();


    }

    public User(String userName, String userPass, List<AnimalFact> factHistory) {
        this.userName = userName;
        this.userPass = userPass;
        this.factHistory = factHistory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return this.userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role userRole) {
        roleList.add(userRole);
    }


    public void setFactHistory(List<AnimalFact> factHistory) {
        this.factHistory = factHistory;
    }

    public void addFactToHistory(AnimalFact animalFact) {

        factHistory.add(animalFact);

    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roleList=" + roleList +
                ", factHistory=" + factHistory +
                '}';
    }
}
