package Webserver;

import spark.Spark;

/**
 *
 * @author Jonse
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Server started.");
        
        Spark.get("/", (req, res) -> {
        
            
            
        return " ";
        });

    }

}
