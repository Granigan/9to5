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

        List<Raaka_aine> raakaaineet = new ArrayList<>();
        List<AnnosRaakaaine> reseptinCache = new ArrayList();

        /**
         *
         * R E I T I T
         *
         *
         */
        
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

        Spark.post("/raaka_aineet/delete/:id", (req, res) -> {

            String id = req.params(":id");
            try {
                Raaka_aine dummy = new Raaka_aine();
                dummy.setId(Integer.parseInt(id));
                raakaDao.delete(dummy);
            } catch (Exception e) {
                System.out.println("Raaka-aineen delete: Yritettiin muuntaa id integeriksi siinä onnistumatta");
            }
            
            res.redirect("/raaka_aineet");
            return " ";
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
            //List<AnnosRaakaaine> b = new ArrayList();

            a = raakaDao.findAll();
            map.put("edelliset", reseptinCache);
            map.put("raaka_aineet", a);
            return new ModelAndView(map, "lisaaresepti");
        }, new ThymeleafTemplateEngine());

        Spark.post("/raaka_ainereseptiin", (req, res) -> {

            if (req.queryParams("end") != null) {
                // TODO: Tallennus tietokantaan
                
                
                res.redirect("/reseptit");
                return " ";
                
            } else {
                // reseptinCache:een tallentaminen
                AnnosRaakaaine r = new AnnosRaakaaine();
                r.setOhje(req.queryParams("ohje"));
                int maara = 0;
                try {
                    Integer.parseInt(req.queryParams("maara"));
                } catch (NumberFormatException e) {
                    // TODO jos ehtii: joku palaute käyttäjälle huonosta syötteestä
                    // TAI html-formiin jokin määräys syötteen muodosta
                    maara = 0;
                }
                
                r.setMaara(maara);
                Raaka_aine raak = new Raaka_aine();
                // Väliaikainen raaka-aineolio etsintää varten
                raak.setNimi(req.queryParams("raakaaine"));

                // Type casting, koska RaakaaineDao palauttaa Object -olion
                Raaka_aine realRaak = (Raaka_aine) (raakaDao.findOne(raak));

                r.setRaakaaineId(realRaak.getId());

                reseptinCache.add(r);
                res.redirect("/lisaaresepti");
            }
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
