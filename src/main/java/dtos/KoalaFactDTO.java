package dtos;

public class KoalaFactDTO {


    private String fact;


    public KoalaFactDTO(KoalaFactDTO koalaFactDTO) {
        this.fact = koalaFactDTO.getFact();
    }

    public KoalaFactDTO(String koalaFact) {
        this.fact = koalaFact;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

}
