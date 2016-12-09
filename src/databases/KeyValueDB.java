/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Miguel
 */
public class KeyValueDB {

    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_CONNECTION = "jdbc:sqlserver://DESKTOP-8HJ3B7P:1433;databaseName=NutriDB_KeyValue";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "qwer";

    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getException());
            System.exit(1);
        }
        String url = DB_CONNECTION;
        try {
            conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    /* Inserir prato */
    public static void insertImage(int idPrato, String img) throws SQLException, FileNotFoundException {
        try (Connection dbConnection = getDBConnection()) {

            if (!img.isEmpty()) {
                File file = new File(img);
                FileInputStream fis = new FileInputStream(file);
                int length = (int) file.length();
                String query = "INSERT INTO " + Contract.KEYVALUETable + " (ID,Prato_img) VALUES(?,?)";
                try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
                    pstmt.setInt(1, idPrato);
                    pstmt.setBinaryStream(2, fis, length);
                    pstmt.executeUpdate();
                }
            } else {
                String query = ("INSERT INTO " + Contract.KEYVALUETable + " (ID,Prato_img) VALUES (?,NULL)");
                try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
                    pstmt.setInt(1, idPrato);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    /* obter a imagem de um dado prato  */
    public static byte[] getImage(int id) throws SQLException {
        byte[] fileBytes = null;
        try (Connection dbConnection = getDBConnection()) {
            String query = "SELECT Prato_img FROM " + Contract.KEYVALUETable + " WHERE ID=" + id;
            try (Statement st = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    fileBytes = rs.getBytes(1);
                }
            }
        }
        return fileBytes;
    }

    /* eliminar prato */
    public static void deletePratoImage(int id) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "DELETE FROM " + Contract.KEYVALUETable + " WHERE ID=" + id;
            try (Statement st = dbConnection.createStatement()) {
                st.execute(query);
            }
        }
    }

    /* atualizar pratos */
    public static void updatePratoImg(int idPrato, String img) throws SQLException, FileNotFoundException {
        try (Connection dbConnection = getDBConnection()) {
            if (img != null) {
                File file = new File(img);
                FileInputStream fis = new FileInputStream(file);
                int len = (int) file.length();

                String query = "UPDATE " + Contract.KEYVALUETable + " SET Prato_img=? Where ID=?";
                try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                    st.setBinaryStream(1, fis, len);
                    st.setInt(2, idPrato);
                    st.executeUpdate();
                }
            }
        }
    }
}
