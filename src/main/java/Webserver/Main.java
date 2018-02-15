package Webserver;

//import java.sql.*;
//import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.TemplateEngine;
//import tikape.Database;

public class Main {

    public static void main(String[] args) {

        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        System.out.println("Server started.");

        HashMap map = new HashMap<>();
        
        map.put("1", new Raaka_aine("this"));

        Spark.get("*", (req, res) -> {

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        System.out.println("");
    }

}
