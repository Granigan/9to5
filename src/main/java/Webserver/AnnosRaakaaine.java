/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webserver;

/**
 *
 * @author jaakkovilenius
 */
public class AnnosRaakaaine {

    private Integer annosid;
    private Integer raakaaineid;
    private Integer jarjestys;
    private Integer maara;
    private String ohje;

    
    public AnnosRaakaaine() {
        // Luo tyhjän objektin ilman arvoja
    }
    
    public AnnosRaakaaine(
            Integer annosid, Integer raakaaineid, Integer jarjestys, Integer maara, String ohje) {
        this.annosid = annosid;
        this.raakaaineid = raakaaineid;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
}

    public Integer getAnnosId() {
        return annosid;
    }

    public Integer getRaakaaineId() {
        return raakaaineid;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public Integer getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setAnnosId(Integer annosid) {
        this.annosid = annosid;
    }

    public void setRaakaaineId(Integer raakaaineid) {
        this.raakaaineid = raakaaineid;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
}