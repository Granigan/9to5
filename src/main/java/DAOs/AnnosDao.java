
package DAOs;

import Webserver.Annos;
import Webserver.Database;
import static Webserver.Database.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnosDao implements Dao {

    
    public AnnosDao() throws SQLException {
        try {
            Connection con = getConnection();
            PreparedStatement createTable = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Annos ("
                    + "id SERIAL PRIMARY KEY,"
                    + "nimi varchar(50)"
                    + ");");
            createTable.executeUpdate();
            createTable.close();
            con.close();

        } catch (Exception e) {
            System.out.println("ongelma luodessa Annos-taulua: " + e.getMessage());
        }
    }

    @Override
    public Annos findOne(Object key) throws SQLException {
        try {
            String etsittava = (String) key;
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE nimi = ?");
            stmt.setString(1, etsittava);

            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) {
                return null;
            }

            Annos annos = new Annos(rs.getInt("id"), rs.getString("nimi"));
            return annos;

        } catch (SQLException e) {
            System.out.println("ongelma etsiessä yhtä annosta" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object saveOrUpdate(Object annos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object key) throws SQLException {
        // TODO: poistaminen
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
