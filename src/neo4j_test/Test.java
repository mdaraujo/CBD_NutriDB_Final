/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Miguel
 */
public class Test {

    private static final String DB_CONNECTION = "jdbc:neo4j:http://localhost:7474";
    private static final String DB_USER = "neo4j";
    private static final String DB_PASSWORD = "qwer";

    public static void main(String[] args) {
        try {
            //getActorsInMatrix();
            //createPrato(1, "Pato com queijo");
            //createPrato(2, "Cenas com mel");
            //System.out.println("Prato 2: " + pratoExists(2));
            //deletePrato(1);
            //System.out.println("Prato 1: " + pratoExists(1));
            //createAlimento(1, "Cenoura");
            //createAlimento(2, "Banana");
            //createAlimento(3, "Mel");
            //System.out.println("Alimento 2: " + alimentoExists(2));
            //deleteAlimento(1);
            //deleteAlimento(3);
            //addAlimentoToPrato(1, 3, 2, "malgas");
            //System.out.println("Alimento 2: " + alimentoExists(2));
            //System.out.println("Pratos com 1? " + isAlimentoInAnyPrato(1));
            //System.out.println("Pratos com 3? " + isAlimentoInAnyPrato(3));
            //addAlimentoToPrato(1, 1, 4.5, "kg");
            //createAlimento(696, "ASD");
            //addAlimentoToPrato(1, 696, 4.23, "CENAS");
            getIngredientesContidosEmPrato(5);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public static void getIngredientesContidosEmPrato(int idPrato) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato {id: {1} })-[CONTEM]->(a:Alimento) RETURN a.id, a.nome, CONTEM";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, idPrato);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("a.id");
                        String nome = rs.getString("a.nome");

                        HashMap contem = (HashMap) rs.getObject("CONTEM");
                        double qtd = (double) contem.get("qtd");
                        String unidade = (String) contem.get("uni");
                        
                        System.out.println(String.format("%-50s %-8.2f %-20s", nome, qtd, unidade));
                    }
                }
            }
        }
    }

    public static boolean isAlimentoInAnyPrato(int idAlimento) throws SQLException {
        boolean exists = false;
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (aa:Alimento)-[:CONTEM]-(pratoscomaa) WHERE aa.id = {1} RETURN pratoscomaa";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, idAlimento);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }
        }
        return exists;
    }

    public static void addAlimentoToPrato(int idPrato, int idAlimento, double qtd, String unidade) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato { id: {1} }), (a:Alimento { id: {2} }) MERGE (p)-[c:CONTEM]->(a) SET c.qtd = {3}, c.uni = {4}";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, idPrato);
                stmt.setInt(2, idAlimento);
                stmt.setDouble(3, qtd);
                stmt.setString(4, unidade);
                stmt.executeUpdate();
            }
        }
    }

    public static void createAlimento(int id, String nome) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MERGE (aa:Alimento { id: {1}, nome: {2} })";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setString(2, nome);
                stmt.executeUpdate();
            }
        }
    }

    public static boolean alimentoExists(int id) throws SQLException {
        boolean exists = false;
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (a:Alimento) WHERE a.id = {1} RETURN a";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }
        }
        return exists;
    }

    public static void deleteAlimento(int id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (a:Alimento {id:{1}}) DETACH DELETE a";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
    }

    public static void createPrato(int id, String nome) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MERGE (pp:Prato { id: {1}, nome: {2} })";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setString(2, nome);
                stmt.executeUpdate();
            }
        }
        //CREATE CONSTRAINT ON (n:Prato) ASSERT n.id IS UNIQUE
    }

    public static String getPratoNomeById(int id) throws SQLException {
        String prato = "";
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato) WHERE p.id = {1} RETURN p.nome as nome";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        prato = rs.getString("nome");
                        System.out.println("Prato: " + prato);
                    }
                }
            }
        }
        return prato;
    }

    public static boolean pratoExists(int id) throws SQLException {
        boolean exists = false;
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato) WHERE p.id = {1} RETURN p";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }
        }
        return exists;
    }

    public static void deletePrato(int id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato {id:{1}}) DETACH DELETE p";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
    }

    public static void getActorsInMatrix() throws SQLException {

        Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

        String query = "MATCH (:Movie {title:{1}})<-[:ACTED_IN]-(a:Person) RETURN a.name as actor";

        try (PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, "The Matrix");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("actor"));
                }
            }
        }
    }
}
