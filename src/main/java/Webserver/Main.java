package Webserver;

import DAOs.*;
import java.sql.SQLException;
import java.util.*;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.List;
//import spark.ModelAndView;
//import spark.TemplateEngine;
//import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Luodaan TestiDao herokun tietokannan testausta varten
        TestiDao testi = new TestiDao();

        // Tällä asetetaan testidaoon muutama testitieto aina palvelimen käynnistyessä.
        // Tämäkin olemassa lähinnä testaamista ja valmistelua varten.
        for (int i = 0; i < 10; i++) {
            try {
                testi.saveOne((int) (Math.random() * 1000));
            } catch (Exception e) {

            }
        }

        RaakaaineDao raakaDao = new RaakaaineDao();
        raakaDao.saveOrUpdate(new Raaka_aine(0, "soijarouhe", "g", "ruskea soijarouhe, toimii siinä missä jauhelihakin"));
        
        
        AnnosDao annosDao = new AnnosDao();
        //annosDao.saveOrUpdate(new Annos(0, "jauhelihakeitto"));
        System.out.println(annosDao.findOne("jauhelihakeitto"));
        
        AnnosRaakaaineDao annosaineDao = new AnnosRaakaaineDao();
        
        
        // Tämä asettaa herokun portin ympäristömuuttujan määräämäksi,
        // jos ympäristömuuttuja on olemassa. Herokua varten tärkeä!
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }

        System.out.println("Server starting.");

        // TODO: Database-yhteyden luominen
        List<Raaka_aine> raakaaineet = new ArrayList<>();
        /*
        raakaaineet.add(new Raaka_aine("Banaani"));
        raakaaineet.add(new Raaka_aine("suola"));
        raakaaineet.add(new Raaka_aine("suoli"));
        raakaaineet.add(new Raaka_aine("suole"));
         */

        /**
         *
         * R E I T I T
         *
         *
         */
        // herokun postgresql:n testaamista varten.
        /*
        Spark.get("/testi", (req, res) -> {
            testi.saveOne((int) (Math.random() * 1000));
            res.redirect("/");
            return " ";
        });*/

        // Tästä alkavat "oikeat" eli tuotantoreitit
        Spark.get("/raaka_aineet", (req, res) -> {

            HashMap map = new HashMap<>();
            List<Raaka_aine> raaka_aineet = new ArrayList<>();
            raaka_aineet = raakaDao.findAll();
            map.put("raaka_aineet", raaka_aineet);

            return new ModelAndView(map, "raaka-aine");
        }, new ThymeleafTemplateEngine());

        
        
        Spark.post("/raaka_aineet", (req, res) -> {
            // Lisätään raaka-aine tietokantaan

            String raakisNimi = req.queryParams("nimi");
            String raakisMittis = req.queryParams("mittayksikko");
            String raakisKuvaus = req.queryParams("kuvaus");

            raakaDao.saveOrUpdate(new Raaka_aine(0, raakisNimi, raakisMittis, raakisKuvaus));

            res.redirect("/raaka_aineet");
            return " ";
        });

        Spark.post("/raaka_aineet/delete", (req, res) -> {

            String nimi = req.queryParams("raaka_aine");
            
            /*
             TODO: 
            if (annosaineDao.isUsed(nimi)) {
                // Tähän se mitä tehdään jos raaka-aine on käytössä
            } else {
                raakaDao.delete(nimi);
            }
            */
            return "<p>9to5 Corp has not implented this feature as of yet.</p>";
            // Lopuksi redirect, kun kaikki ominaisuudet luotu
           // res.redirect("/raaka_aineet");
            //return " ";
        });

        Spark.get("/reseptit", (req, res) -> {

            HashMap map = new HashMap<>();

            // TODO: EI TOTEUTETTU
            map.put("raaka_aineet", raakaaineet);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/annosraakaaine", (req, res) -> {

            HashMap map = new HashMap<>();
            List<Integer> ARs = new ArrayList<>();
            ARs = annosaineDao.findAll();
            // TODO: tähän annosraakaaineiden haku
            
            
            map.put("annosraakaaineet", ARs);
            return new ModelAndView(map, "annosraakaaine");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.get("/lisaaresepti", (req, res) -> {
            
            HashMap map = new HashMap<>();
            List<Raaka_aine> a = new ArrayList();
            a = raakaDao.findAll();
            map.put("raaka_aineet", a);
            return new ModelAndView(map, "lisaaresepti");
        }, new ThymeleafTemplateEngine());
        
        
        
        Spark.post("/resepti", (req, res) -> {
            
            Set<String> s = req.queryParams();
            System.out.println(req.body());
            System.out.println(req.queryParams());
            System.out.println(s.toString());
            s.forEach(f -> {
                System.out.println(f);
            
            });
            
            
            
            res.redirect("/reseptit");
            return " ";
        });


        Spark.get("/int", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Integer> intit = new ArrayList<>();
            intit = testi.getAll();
            map.put("intit", intit);
            return new ModelAndView(map, "int");
        }, new ThymeleafTemplateEngine());

        
        
        // "Catch-all" -reitti
        Spark.get("*", (req, res) -> {
            HashMap map = new HashMap<>();
            //map.put("raaka_aineet", raakaaineet);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

    }

}
