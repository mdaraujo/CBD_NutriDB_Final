/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import entities.Ingrediente;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Miguel
 */
public class GraphDB {

    private static final String DB_CONNECTION = "jdbc:neo4j:http://localhost:7474";
    private static final String DB_USER = "neo4j";
    private static final String DB_PASSWORD = "qwer";

    
    public static List<Ingrediente> getListIngContidosEmPrato(int idPrato) throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            String query = "MATCH (p:Prato {id: {1} })-[CONTEM]->(a:Alimento) RETURN a.id, CONTEM";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, idPrato);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("a.id");
                        HashMap contem = (HashMap) rs.getObject("CONTEM");
                        double qtd = (double) contem.get("qtd");
                        String unidade = (String) contem.get("uni");
                        Ingrediente ing = new Ingrediente(id, qtd, unidade);

                        ingredientes.add(ing);
                    }
                }
            }
        }
        return ingredientes;
    }
    
    public static DefaultListModel getPratosPorAlimentos(List<Integer> idAlimentos) throws SQLException {
        DefaultListModel listModel = new DefaultListModel();

        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato)-[:CONTEM]->(a:Alimento) WHERE a.id IN [";

            for (Integer idAlimento : idAlimentos) {
                query += idAlimento + ", ";
            }
            query = query.substring(0, query.length() - 2); // delete last ", "
            query += "] RETURN DISTINCT p.nome";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String nome = rs.getString("p.nome");

                        listModel.addElement(nome);
                    }
                }
            }
        }
        return listModel;
    }

    public static void deleteCONTEMRelation(int idPrato, int idAlimento) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (:Prato {id: {1} })-[r:CONTEM]-(:Alimento {id: {2} }) DELETE r";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, idPrato);
                stmt.setInt(2, idAlimento);
                stmt.executeUpdate();
            }
        }
    }

    public static DefaultListModel getIngredientesContidosEmPrato(int idPrato) throws SQLException {
        DefaultListModel listModel = new DefaultListModel();

        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato {id: {1} })-[CONTEM]->(a:Alimento) RETURN a.id, a.nome, CONTEM";
            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, idPrato);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String nome = rs.getString("a.nome");

                        HashMap contem = (HashMap) rs.getObject("CONTEM");
                        double qtd = (double) contem.get("qtd");
                        String unidade = (String) contem.get("uni");

                        if (qtd == 0) {
                            listModel.addElement(String.format("%-40s %s", nome, unidade));
                        } else {
                            listModel.addElement(String.format("%-40s %.2f %s", nome, qtd, unidade));
                        }
                    }
                }
            }
        }
        return listModel;
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

    public static void deletePrato(int id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            String query = "MATCH (p:Prato {id:{1}}) DETACH DELETE p";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
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
}
