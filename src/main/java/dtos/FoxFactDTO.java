package dtos;

public class FoxFactDTO {


    private String fact;


    public FoxFactDTO(FoxFactDTO foxFactDTO) {
        this.fact = foxFactDTO.getFact();
    }

    public FoxFactDTO(String foxFact) {
        this.fact = foxFact;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }


}
