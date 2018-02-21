package Webserver;

import java.util.*;
import spark.*;
import Webserver.Database;
import java.sql.*;
//import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
//import java.util.List;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

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

        // Tämä asettaa herokun portin ympäristömuuttujan määräämäksi,
        // jos ympäristömuuttuja on olemassa. Herokua varten tärkeä!
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }

        System.out.println("Server starting.");

        // TODO: Database-yhteyden luominen
        List<Raaka_aine> raakaaineet = new ArrayList<>();
        raakaaineet.add(new Raaka_aine("Banaani"));
        raakaaineet.add(new Raaka_aine("suola"));
        raakaaineet.add(new Raaka_aine("suoli"));
        raakaaineet.add(new Raaka_aine("suole"));

        
        
        
        
        /**
         *
         * R E I T I T
         *
         *
         */
        
        
        
        // herokun postgresql:n testaamista varten.
        Spark.get("/testi", (req, res) -> {
            testi.saveOne((int) (Math.random() * 1000));
            res.redirect("/");
            return " ";
        });
        
        
        
        // Tästä alkavat "oikeat" eli tuotantoreitit
        Spark.get("*", (req, res) -> {

            HashMap map = new HashMap<>();

            map.put("raaka_aineet", raakaaineet);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/reseptit", (req, res) -> {

            HashMap map = new HashMap<>();

            // TODO: 
            map.put("raaka_aineet", raakaaineet);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
    }

}
