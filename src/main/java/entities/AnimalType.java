package entities;


import dtos.AnimalTypeDTO;
import org.eclipse.persistence.internal.oxm.schema.model.All;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "AnimalType.deleteAllRows", query = "DELETE from AnimalType")
public class AnimalType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String type;


    @OneToMany(mappedBy = "animalType")
    private Set<AnimalFact> animalFacts = new HashSet<>();

    public AnimalType() {
    }

    public AnimalType(AnimalTypeDTO animalTypeDTO) {
        this.type = animalTypeDTO.getType();

    }


    // edit below here.


    public AnimalType(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public Set<AnimalFact> getAnimalFacts() {
        return animalFacts;
    }

    public void setAnimalFacts(Set<AnimalFact> animalFacts) {
        this.animalFacts = animalFacts;
    }

    public void addFact(AnimalFact animalFact) {

        animalFacts.add(animalFact);

    }


}
