package DAOs;

import static Webserver.Database.getConnection;
import Webserver.Raaka_aine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
DAO raaka-aineille, constructorissa create table
 */
public class RaakaaineDao implements Dao {

    public RaakaaineDao() throws SQLException {
        try {
            Connection con = getConnection();
            PreparedStatement createTable = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Raakaaine ("
                    + "id SERIAL PRIMARY KEY,"
                    + "nimi varchar(50),"
                    + "mittayksikko varchar(50),"
                    + "kuvaus varchar(500)"
                    + ");");
            createTable.executeUpdate();
            createTable.close();
            con.close();

        } catch (Exception e) {
            System.out.println("ongelma luodessa raakaaine-taulua: " + e.getMessage());
        }

    }

    @Override
    public Object findOne(Object key) throws SQLException {
        try {
            Raaka_aine etsittava = (Raaka_aine) key;
            Raaka_aine osuma = null;
            Connection con = getConnection();
            PreparedStatement haku = con.prepareStatement(""
                    + "SELECT * FROM Raakaaine WHERE nimi = ?");
            haku.setString(1, etsittava.getNimi());
            ResultSet rs = haku.executeQuery();
            if (rs.next()) {
                osuma = new Raaka_aine(rs.getInt("id"), rs.getString("nimi"), rs.getString("mittayksikko"), rs.getString("kuvaus"));
            }

            rs.close();
            haku.close();
            con.close();
            return osuma;

        } catch (SQLException e) {
            System.out.println("ongelma etsiessä yhtä raaka-ainetta" + e.getMessage());
        }
        return null;
    }

    @Override
    public List findAll() throws SQLException {

        List<Raaka_aine> ret = new ArrayList<>();

        try {

            Connection con = getConnection();
            PreparedStatement prep = con.prepareStatement("SELECT * FROM Raakaaine");

            ResultSet r = prep.executeQuery();

            while (r.next()) {
                ret.add(new Raaka_aine(r.getInt("id"), r.getString("nimi"), r.getString("mittayksikko"), r.getString("kuvaus")));
            }

            r.close();
            prep.close();
            con.close();

            return ret;
        } catch (Exception e) {
            System.out.println("Ongelma findAll-metodissa: " + e);
            return null;
        }
    }

    @Override
    public Object saveOrUpdate(Object raakaaine) throws SQLException {
        try {
            Raaka_aine uusi = (Raaka_aine) raakaaine;
            if (findOne(uusi) != null) {
                Raaka_aine vanha = (Raaka_aine) findOne(uusi);
                Connection con = getConnection();
                PreparedStatement paivita = con.prepareStatement(""
                        + "UPDATE Raakaaine SET mittayksikko = ?, kuvaus = ? WHERE nimi = ?");
                paivita.setString(1, uusi.getMittayksikko());
                paivita.setString(2, uusi.getKuvaus());
                paivita.setString(3, uusi.getNimi());
                paivita.executeUpdate();
                paivita.close();
                con.close();

            } else {

                Connection con = getConnection();
                PreparedStatement lisaa = con.prepareStatement("INSERT INTO Raakaaine (nimi, mittayksikko, kuvaus) "
                        + "VALUES (?, ?, ?)");
                lisaa.setString(1, uusi.getNimi());
                lisaa.setString(2, uusi.getMittayksikko());
                lisaa.setString(3, uusi.getKuvaus());

                lisaa.executeUpdate();
                lisaa.close();
                con.close();
            }

        } catch (SQLException e) {
            System.out.println("ongelma lisätessä uutta raaka-ainetta" + e.getMessage());
        }
        return true;

    }

    @Override
    public void delete(Object key) throws SQLException {
        // TODO: poistaminen
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
