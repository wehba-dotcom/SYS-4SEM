package dtos;

public class DogFactDTO {

    private String[] facts;
    private String dogFact;

    public DogFactDTO(DogFactDTO dogFactDTO) {
        this.dogFact = dogFactDTO.getFacts()[0];

    }

    public String[] getFacts() {
        return facts;
    }

    public void setFacts(String[] facts) {
        this.facts = facts;
    }

    public String getDogFact() {
        return dogFact;
    }

    public void setDogFact(String dogFact) {
        this.dogFact = dogFact;
    }
}
