package dtos;

import entities.AnimalFact;

import java.util.ArrayList;
import java.util.List;

public class AnimalFactDTO {

    private int id;
    private AnimalTypeDTO type;

    private String fact;

    public AnimalFactDTO(AnimalFact animalFact) {

        if (animalFact.getId() != null)
            this.id = animalFact.getId().intValue();
        type = new AnimalTypeDTO(animalFact.getAnimalType());
        this.fact = animalFact.getFact();
    }

    public static List<AnimalFactDTO> getDtos(List<AnimalFact> animalFactList){
        List<AnimalFactDTO> afdtos = new ArrayList();
        animalFactList.forEach(af->afdtos.add(new AnimalFactDTO(af)));
        return afdtos;
    }

    public AnimalFactDTO(AnimalTypeDTO type, String fact) {
        this.type = type;
        this.fact = fact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimalTypeDTO getType() {
        return type;
    }

    public void setType(AnimalTypeDTO type) {
        type = type;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
