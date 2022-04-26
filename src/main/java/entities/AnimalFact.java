package entities;


import dtos.AnimalFactDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name = "AnimalFact.deleteAllRows", query = "DELETE FROM AnimalFact ")
public class AnimalFact implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AnimalFact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Edit below here

    @Column(length=1000)
    private String fact;



    @ManyToOne(cascade = CascadeType.ALL)
    private AnimalType animalType;



    public AnimalFact(String fact) {
        this.fact = fact;
        this.animalType = getAnimalType();
    }

    public AnimalFact(AnimalFactDTO animalFactDTO){
        this.fact = animalFactDTO.getFact();
        this.animalType = new AnimalType(animalFactDTO.getType());


    }

    public AnimalFact(String fact, AnimalType animalType) {
        this.fact = fact;
        this.animalType = animalType;
    }

    public AnimalFact(Long id, String fact, AnimalType animalType) {
        this.id = id;
        this.fact = fact;
        this.animalType = animalType;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }


}
