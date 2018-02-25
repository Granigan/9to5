package Webserver;

//import java.util.ArrayList;

public class Annos {

    private int id;
    private String nimi;
    private String valmistusohje;
//    private ArrayList<Raaka_aine> AnnosRaakaAineLista;  kommentoin ulos, näitä ei kait tarvita täällä? -tt

    public Annos(int id, String nimi, String valmistusohje) {
        this.id = id;
        this.nimi = nimi;
        this.valmistusohje = valmistusohje;
//        ArrayList<Object> raakaAineLista;
//        this.AnnosRaakaAineLista = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setValmistusohje(String valmistusohje) {
        this.valmistusohje = valmistusohje;
    }

    public String getValmistusohje() {
        return valmistusohje;
    }

}
