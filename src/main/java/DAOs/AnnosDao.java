
package DAOs;

import Webserver.Annos;
import Webserver.Database;
import static Webserver.Database.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AnnosDao implements Dao<Annos, Integer> {
//    private Database database;
//    
//    public AnnosDao(Database database) {
//        this.database = database;
//    }
    
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
    public Annos findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Annos saveOrUpdate(Annos object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
