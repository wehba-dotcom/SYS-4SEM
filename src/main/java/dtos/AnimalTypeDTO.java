package dtos;

import entities.AnimalType;

public class AnimalTypeDTO {


    private String type;


    public AnimalTypeDTO(AnimalType animalType) {


        this.type = animalType.getType();
    }

    public AnimalTypeDTO(String type) {

        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
