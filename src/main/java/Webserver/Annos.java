
package Webserver;

import java.util.ArrayList;


public class Annos {
    
    private int id;
    private String nimi;
    private ArrayList<Raaka_aine> AnnosRaakaAineLista;
    
    
    public Annos(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
        ArrayList<Object> raakaAineLista;
        this.AnnosRaakaAineLista = new ArrayList<>();
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
    
}
