package Webserver;

import spark.Spark;
import java.sql.*;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
//import tikape.Database;

public class Main {

    public static void main(String[] args) {
        System.out.println("Server started.");
        
        Spark.get("*", (req, res) -> {
        
            
            
        return " ";
        });

        System.out.println("");
    }

}
