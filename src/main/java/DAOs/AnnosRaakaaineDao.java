/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import static Webserver.Database.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaakkovilenius
 */
public class AnnosRaakaaineDao implements Dao {

    public AnnosRaakaaineDao() throws SQLException {

        Connection con = getConnection();
        try {
            PreparedStatement createTable = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS AnnosRaakaaine ("
                    + "annos_id integer,"
                    + "raakaaine_id integer,"
                    + "jarjestys integer,"
                    + "maara integer,"
                    + "ohje varchar(500),"
                    + "FOREIGN KEY (annos_id) REFERENCES Annos (id),"
                    + "FOREIGN KEY (raakaaine_id) REFERENCES Raakaaine (id)"
                    + ");");
            createTable.executeUpdate();
            createTable.close();
        } catch (Exception e) {
            System.out.println("ongelma luodessa raakaaine-taulua: " + e.getMessage());
        }
        try {
            PreparedStatement createIndexAnnos = con.prepareStatement(
                    "CREATE INDEX IF NOT EXISTS AnnosRaakaaineAnnosIdx "
                    + "ON AnnosRaakaaine (annos_id);");
            createIndexAnnos.executeUpdate();
            createIndexAnnos.close();
        } catch (Exception e) {
            System.out.println("ongelma luodessa raakaaine-taulun indeksiä: " + e.getMessage());
        }
        try {
            PreparedStatement createIndexAnnos = con.prepareStatement(
                    "CREATE INDEX IF NOT EXISTS AnnosRaakaaineRaakaaineIdx "
                    + "ON AnnosRaakaaine (raakaaine_id);");
            createIndexAnnos.executeUpdate();
            createIndexAnnos.close();
        } catch (Exception e) {
            System.out.println("ongelma luodessa raakaaine-taulun indeksiä: " + e.getMessage());
        }
        
        con.close();

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
    public Object saveOrUpdate(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
