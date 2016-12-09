/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import entities.Alimento;
import entities.Prato;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Miguel
 */
public class RelationalDB {

    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_CONNECTION = "jdbc:sqlserver://DESKTOP-8HJ3B7P:1433;databaseName=NutriDB_Relational";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "qwer";

    public static int getAlimentoID(String nome) throws SQLException {

        int id = -1;
        try (Connection dbConnection = getDBConnection()) {
            String query = "SELECT ID FROM " + Contract.AlimentoTable + " WHERE Nome=?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setString(1, nome);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("ID");
                    }
                }
            }
        }
        return id;
    }
    
    public static DefaultListModel getAlimentosByName(String name) throws SQLException {
        DefaultListModel listModel = new DefaultListModel();

        String query = "SELECT Nome FROM Alimentos WHERE Nome LIKE '%" + name + "%' ORDER BY ID ASC";

        try (Connection dbConnection = getDBConnection()) {
            try (Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet rs = st.executeQuery(query)) {
                    while (rs.next()) {
                        String nome = rs.getString("Nome");
                        listModel.addElement(nome);
                    }
                }
            }
        }
        return listModel;
    }
    
    public static int getPratoID(String pratoNome) throws SQLException {

        int id = -1;
        try (Connection dbConnection = getDBConnection()) {
            String query = "SELECT ID FROM " + Contract.PratoTable + " WHERE Nome=?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setString(1, pratoNome);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("ID");
                    }
                }
            }
        }
        return id;
    }

    public static void insertPrato(String nome, String descricao) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "INSERT INTO " + Contract.PratoTable + " (Nome,Descricao) values(?,?)";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setString(1, nome);
                st.setString(2, descricao);
                st.executeUpdate();
            }
        }
    }

    public static void updatePrato(int idPrato, String nome, String descricao) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "UPDATE " + Contract.PratoTable + " SET Nome=?,Descricao=? WHERE ID=?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setString(1, nome);
                st.setString(2, descricao);
                st.setInt(3, idPrato);
                st.executeUpdate();
            }
        }
    }

    public static Prato getPrato(int id) throws SQLException {
        Prato prato = null;
        try (Connection dbConnection = getDBConnection()) {
            String query = "SELECT * FROM Pratos WHERE ID = ?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("Nome");
                        String descricao = rs.getString("Descricao");
                        prato = new Prato(id, nome, descricao);
                    }
                }
            }
        }
        return prato;
    }

    public static void deletePrato(int id) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "DELETE FROM " + Contract.PratoTable + " WHERE ID=" + id;
            try (Statement st = dbConnection.createStatement()) {
                st.execute(query);
            }
        }
    }

    public static List selectPratos(String selectSQL) throws SQLException {
        List<Prato> pratos = new ArrayList<>();
        try (Connection dbConnection = getDBConnection()) {
            try (Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet rs = st.executeQuery(selectSQL)) {
                    while (rs.next()) {

                        int id = rs.getInt("ID");
                        String nome = rs.getString("Nome");
                        String desc = rs.getString("Descricao");
                        Prato prato = new Prato(id, nome, desc);
                        pratos.add(prato);
                    }
                }
            }
        }
        return pratos;
    }

    public static void deleteAlimento(int id) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "DELETE FROM " + Contract.AlimentoTable + " WHERE ID=?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        }
    }

    public static void updateAlimento(Alimento alimento) throws SQLException {
        try (Connection dbConnection = getDBConnection()) {
            String query = "UPDATE " + Contract.AlimentoTable + " SET Nome=?, Humidade_perc=?, Energia_kcal=?, Proteina_g=?, Lipidos_g=?, Colestrol_mg=?, HidratosDeCarb_g=?, FibraAlimentar_g=?, Categoria=? WHERE ID=?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setInt(10, alimento.getID());
                st.setString(1, alimento.getNome());
                st.setFloat(2, alimento.getHumidade());
                st.setInt(3, alimento.getEnergia());
                st.setFloat(4, alimento.getProteina());
                st.setFloat(5, alimento.getLipidos());
                st.setInt(6, alimento.getColestrol());
                st.setFloat(7, alimento.getHidratos());
                st.setFloat(8, alimento.getFibra());
                st.setString(9, alimento.getCategoria());
                st.executeUpdate();
            }
        }
    }

    public static Alimento getAlimento(int id) throws SQLException {
        Alimento alimento = null;
        try (Connection dbConnection = getDBConnection()) {
            String query = "SELECT * FROM Alimentos WHERE ID = ?";
            try (PreparedStatement st = dbConnection.prepareStatement(query)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("Nome");
                        float humidade = rs.getFloat("Humidade_perc");
                        int energia = rs.getInt("Energia_kcal");
                        float proteina = rs.getFloat("Proteina_g");
                        float lipidos = rs.getFloat("Lipidos_g");
                        int colestrol = rs.getInt("Colestrol_mg");
                        float hidratos = rs.getFloat("HidratosDeCarb_g");
                        float fibra = rs.getFloat("FibraAlimentar_g");
                        String categoria = rs.getString("Categoria");

                        alimento = new Alimento(id, nome, humidade, energia, proteina, lipidos, colestrol, hidratos, fibra, categoria);
                    }
                }
            }
        }
        return alimento;
    }

    public static DefaultListModel selectAlimentos(String selectSQL) throws SQLException {
        DefaultListModel listModel = new DefaultListModel();

        String header = String.format("%-4s %-70s %-8s %-6s %-8s %-7s %-9s %-8s %-7s %-20s", "ID", "NOME", "HUMIDADE", "ENEGIA", "PROTEINA", "LIPIDOS", "COLESTROL", "HIDRATOS", "FIBRA", "CATEGORIA");
        listModel.addElement(header);

        try (Connection dbConnection = getDBConnection()) {
            try (Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet rs = st.executeQuery(selectSQL)) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String nome = rs.getString("Nome");
                        float humidade = rs.getFloat("Humidade_perc");
                        int energia = rs.getInt("Energia_kcal");
                        float proteina = rs.getFloat("Proteina_g");
                        float lipidos = rs.getFloat("Lipidos_g");
                        int colestrol = rs.getInt("Colestrol_mg");
                        float hidratos = rs.getFloat("HidratosDeCarb_g");
                        float fibra = rs.getFloat("FibraAlimentar_g");
                        String categoria = rs.getString("Categoria");

                        Alimento alimento = new Alimento(id, nome, humidade, energia, proteina, lipidos, colestrol, hidratos, fibra, categoria);
                        listModel.addElement(alimento.toString());
                    }
                }
            }
        }
        return listModel;
    }

    public static Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }
}
