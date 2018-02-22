package Webserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> getAll() {
        List<Integer> ret = new ArrayList<>();

        try {

            Connection con = getConnection();
            PreparedStatement prep = con.prepareStatement("SELECT * FROM test");

            ResultSet r = prep.executeQuery();

            while (r.next()) {
                ret.add(new Integer(r.getInt("num")));
            }

            r.close();
            prep.close();
            con.close();

            return ret;
        } catch (Exception e) {

            return null;
        }
    }

    public boolean saveOne(int i) throws Exception {
        try {

            Connection con = getConnection();
            PreparedStatement prep = con.prepareStatement("INSERT INTO test (num) VALUES(?)");
            prep.setInt(1, i);
            prep.execute();

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
            return DriverManager.getConnection(dbUrl);
        }
        return DriverManager.getConnection("jdbc:sqlite:resepti.db");
    }

}
