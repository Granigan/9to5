package Webserver;

public class Raaka_aine {

    private String nimi;
    private String mittayksikko;
    private String kuvaus;
    private Integer id;

    public Raaka_aine(Integer id, String nimi, String mittayksikko, String kuvaus) {
        this.nimi = nimi;
        this.id = id;
        this.kuvaus = kuvaus;
    }

    public String getMittayksikko() {
        return mittayksikko;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {

        return this.nimi;
    }

}
