package Webserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * Tämän luokan tarkoitus on toimia tietokantatestiympäristönä. MIKÄÄN TÄSTÄ EI
 * OLE TARKOITETTU MUUHUN KUIN TESTAAMISEEN. Jos tämä tiedosto löytyy
 * lopulliselta palvelimelta, ohjelma ei ole valmis.
 *
 */
public class TestiDao {

    //private Database db;
    public TestiDao() {

        try {
            Connection con = getConnection(); 
            PreparedStatement prep = con.prepareStatement("CREATE TABLE test (id SERIAL PRIMARY KEY, num integer)");
            prep.executeUpdate();
            prep.close();
            con.close();
            System.out.println("testitable luotu.");
        
        } catch (Exception e) {
            System.out.println("Joko ei yhteyttä luotaessa tai jokin muu ongelma yhteydessä: " + e);
        }

    }

    public boolean saveOne(int i) {
        System.out.println("SaveOne käynnistetty!");
        try {
            
            Connection con = getConnection();
            PreparedStatement prep = con.prepareStatement("INSERT INTO test (num) VALUES(?)");
            prep.setInt(1, i);
            prep.executeUpdate();
            
            prep.close();
            con.close();
                        return true;
        } catch (Exception e) {
            System.out.println("Ongelma tietokantayhteydessä TestiDai.java: " + e);
        }
        return false;
    }

    public static Connection getConnection() throws Exception {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            System.out.println("1");
            return DriverManager.getConnection(dbUrl);
            
        }
        
        System.out.println("2");

        return DriverManager.getConnection("jdbc:sqlite:resepti.db");
    }

}
