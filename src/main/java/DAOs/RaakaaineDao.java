package DAOs;

import static Webserver.Database.getConnection;
import Webserver.Raaka_aine;
import java.sql.*;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object saveOrUpdate(Object raakaaine) throws SQLException {
        try{
        Raaka_aine uusi = (Raaka_aine) raakaaine;
        // tästä puuttuu tarkistus onko raaka-ainetta jo olemassa
        
        Connection con = getConnection();
        PreparedStatement lisaa = con.prepareStatement("INSERT INTO Raakaine (nimi, mittayksikko, kuvaus) "
                + "VALUES ? ? ?");
        lisaa.setString(1, uusi.getNimi());
        lisaa.setString(2, uusi.getMittayksikko());
        lisaa.setString(3, uusi.getKuvaus());
        
        lisaa.executeUpdate();
        lisaa.close();
        con.close();
        
        
        } catch (SQLException e) {
            System.out.println("ongelma lisätessä uutta raaka-ainetta" + e.getMessage());
        }
        return true;
        
    }

    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
