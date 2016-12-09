package databases;

import entities.Prato;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andreia Machado
 */
public class DocDB {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String DATABASE = "NutriDB_Docs";
    private static final String TABLE = "documents";
    private static final DB databaseConnection = authentication();

    private static DB authentication() {
        try {

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // Now connect to your databases
            DB db = mongoClient.getDB(DATABASE);
            return db;
        } catch (UnknownHostException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static boolean insertDoc(Prato prato) {
        try {
            DBCollection coll = databaseConnection.getCollection(TABLE);

            BasicDBObject doc = new BasicDBObject("_id", prato.getID()).
                    append("Preparacao", prato.getPreparacao()).
                    append("Cozinha", prato.getCozinha()).
                    append("Dificuldade", prato.getDificuldade()).
                    append("Tempo", prato.getTempo()).
                    append("Doses", prato.getDoses());

            coll.insert(doc);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean updatePratoDoc(Prato prato) {
        try {
            DBCollection coll = databaseConnection.getCollection(TABLE);

            BasicDBObject queryID = new BasicDBObject();
            queryID.put("_id", prato.getID());
            DBCursor cursor = coll.find(queryID);

            while (cursor.hasNext()) {
                DBObject updateDocument = cursor.next();
                updateDocument.put("Preparacao", prato.getPreparacao());
                updateDocument.put("Cozinha", prato.getCozinha());
                updateDocument.put("Dificuldade", prato.getDificuldade());
                updateDocument.put("Tempo", prato.getTempo());
                updateDocument.put("Doses", prato.getDoses());
                coll.update(new BasicDBObject().append("_id", prato.getID()), updateDocument);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean deletePratoDoc(int id) {
        try {
            DBCollection coll = databaseConnection.getCollection(TABLE);

            BasicDBObject queryID = new BasicDBObject();
            queryID.put("_id", id);
            DBCursor cursor = coll.find(queryID);

            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                coll.remove(document);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static Prato getPrato(int id) {
        try {
            Prato prato = null;

            DBCollection coll = databaseConnection.getCollection(TABLE);

            BasicDBObject queryID = new BasicDBObject();
            queryID.put("_id", id);
            DBCursor cursor = coll.find(queryID);

            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                String cozinha = (String) document.get("Cozinha");
                String dificuldade = (String) document.get("Dificuldade");
                String tempo = (String) document.get("Tempo");
                int doses = (int) document.get("Doses");
                String preparacao = (String) document.get("Preparacao");

                prato = new Prato(id, cozinha, dificuldade, tempo, doses, preparacao);
            }
            return prato;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static List selectPratos(Map<String, String> map) {
        try {
            List<Prato> pratos = new ArrayList<>();

            DBCollection coll = databaseConnection.getCollection(TABLE);

            DBCursor cursor;
            BasicDBObject query = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                obj.add(new BasicDBObject(entry.getKey(), entry.getValue()));
            }
            if (obj.isEmpty()) {
                cursor = coll.find().sort(new BasicDBObject("_id", 1));
            } else {
                query.put("$and", obj);
                cursor = coll.find(query).sort(new BasicDBObject("_id", 1));
            }

            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                int id = (int) document.get("_id");
                String cozinha = (String) document.get("Cozinha");
                String dificuldade = (String) document.get("Dificuldade");
                String tempo = (String) document.get("Tempo");
                int doses = (int) document.get("Doses");

                Prato prato = new Prato(id, cozinha, dificuldade, tempo, doses);
                pratos.add(prato);
            }

            return pratos;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

}
