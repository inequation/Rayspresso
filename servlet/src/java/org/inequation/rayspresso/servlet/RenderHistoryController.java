package org.inequation.rayspresso.servlet;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import org.inequation.rayspresso.renderer.RenderType;
import org.inequation.rayspresso.renderer.RenderTypeFormatException;

/**
 * Database backend controller singleton for the render history.
 * @author inequation
 * @version 21.01.2012
 */
public class RenderHistoryController {
    public static RenderHistoryController getInstance() {
        if (m_instance == null) {
            synchronized (RenderHistoryController.class) {
                if (m_instance == null) {
                    m_instance = new RenderHistoryController();
                }
            }
        }
        return m_instance;
    }
    
    private RenderHistoryController() {
        setupConnection();
    }
    
    private void setupConnection() {
        ServletContext ctx = RayspressoServlet.getContext();
        if (ctx == null)
            return;
        try {
            // load driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // create a connection
            String driver = ctx.getInitParameter("DBDriver");
            String host = ctx.getInitParameter("DBHost");
            int port = Integer.parseInt(ctx.getInitParameter("DBPort"));
            String db = ctx.getInitParameter("DBDatabase");
            String user = ctx.getInitParameter("DBUsername");
            String pass = ctx.getInitParameter("DBPassword");
            m_conn = DriverManager.getConnection(
                String.format("jdbc:%s://%s:%d/%s",
                    driver, host, port, db), user, pass);
        } catch (ClassNotFoundException cnfe) {
            System.err.print("Rayspresso: " + cnfe.getMessage());
        } catch (SQLException sqle) {
            System.err.print("Rayspresso: " + sqle.getMessage());
        }
        
        // make sure the SQL tables exist
        createSQLTables();
    }
    
    /** Creates all the necessary SQL tables in the DB. */
    private void createSQLTables() {
        try {
            // create the statement object
            Statement statement = m_conn.createStatement();
            // create table schema
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS RenderHistory " +
                "(timestamp TIMESTAMP, rendermode VARCHAR(20), zNear FLOAT, " +
                "zFar FLOAT, width INTEGER, height INTEGER, " +
                "PRIMARY KEY (timestamp))");
        } catch (Exception e) {
        }
    }
    
    public void destroy() {
        try {
            if (m_conn != null)
                m_conn.close();
        } catch (SQLException sqle) {
            System.err.print("Rayspresso: " + sqle.getMessage());
        }
        m_instance = null;
    }
    
    public void insertEntry(RenderHistoryEntry e) {
        if (m_conn == null)
            setupConnection();
        try {
            Statement statement = m_conn.createStatement();
            DecimalFormatSymbols dot =
                new DecimalFormatSymbols();
            dot.setDecimalSeparator('.');
            DecimalFormat alwaysDot = new DecimalFormat("###.###", dot);
            String sql = String.format("INSERT INTO RenderHistory "
                    + "VALUES ('%s', '%s', %s, %s, %d, %d)",
                    e.getTimestamp().toString(), e.getRenderMode().toString(),
                    alwaysDot.format(e.getZNear()),
                    alwaysDot.format(e.getZFar()),
                    e.getWidth(), e.getHeight());
            statement.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.err.print("Rayspresso: " + sqle.getMessage());
        }
    }
    
    public ArrayList<RenderHistoryEntry> getEntries() {
        if (m_conn == null)
            setupConnection();
        ArrayList<RenderHistoryEntry> result = new ArrayList();
        try {
            Statement statement = m_conn.createStatement();
            // Wysyłamy zapytanie do bazy danych
            ResultSet rs = statement.executeQuery("SELECT * FROM RenderHistory");
            // Przeglądamy otrzymane wyniki
            while (rs.next()) {
                result.add(new RenderHistoryEntry(
                    rs.getTimestamp("timestamp"),
                    RenderType.parseRenderType(rs.getString("rendermode")),
                    rs.getFloat("zNear"), rs.getFloat("zFar"),
                    rs.getInt("width"), rs.getInt("height")
                ));
            }
            rs.close();
        } catch (SQLException sqle) {
            System.err.print("Rayspresso: " + sqle.getMessage());
        } catch (RenderTypeFormatException rtfe) {
            System.err.print("Rayspresso: " + rtfe.getMessage());
        }
        return result;
    }
    
    private static volatile RenderHistoryController m_instance = null;
    
    private Connection m_conn;
}
