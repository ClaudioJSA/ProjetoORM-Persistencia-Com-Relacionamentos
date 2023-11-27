/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class DbConnection {
    private static Connection connection;
    public static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    
    static{
        URL = "jdbc:mysql://db4free.net:3306/" + Dao.DB
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&serverTimezone=UTC"
                + "&autoReconnect=true";
        USER = "rootlibrarypoo";
        PASSWORD = "9pPuy4eQff_-ysj";
    }

    public DbConnection() {
    }
    
    public static Connection getConnection() throws SQLException{
        if(connection == null){
            try{
                System.out.println("Connecting to database.");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch(SQLException ex){
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }    
}