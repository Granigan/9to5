package Webserver;

import java.util.*;
import spark.*;
//import java.sql.*;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.List;
//import spark.ModelAndView;
//import spark.Spark;
//import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
//import tikape.Database;

public class Main {

    public static void main(String[] args) {

        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        System.out.println("Server started.");

        // TODO: Database-yhteyden luominen
        List<Raaka_aine> raakaaineet = new ArrayList<>();
        raakaaineet.add(new Raaka_aine("Banaani"));
        raakaaineet.add(new Raaka_aine("suola"));
        raakaaineet.add(new Raaka_aine("suoli"));
        raakaaineet.add(new Raaka_aine("suole"));

        Spark.get("*", (req, res) -> {

            HashMap map = new HashMap<>();

            map.put("raaka_aineet", raakaaineet);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        System.out.println("");
    }

}
