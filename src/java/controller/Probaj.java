/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 *
 * @author nikol
 */
public class Probaj {
    
    public static void main(String[] args) throws UnknownHostException, FileNotFoundException, IOException {
    MongoClient client = new MongoClient();
    DB dab = client.getDB("digiteka");
    MongoDatabase db = client.getDatabase("digiteka");
    MongoCollection<Document> collection = db.getCollection("objekti");
    GridFS gf = new GridFS(dab, "objekti");
    ArrayList<String> nazivi = new ArrayList();
    String s = "case";
    String s1 = "copy";

    BasicDBObject query = new BasicDBObject("filename", new BasicDBObject("$regex", s)
            .append("$regex", s1)
            .append("$options", "i"));
    
    String r;
   List<GridFSDBFile> list = gf.find("CASE - Copy.pdf");
   List<GridFSDBFile> lista = gf.find(query);
   Integer lgt = lista.size();
   System.out.println("Velicina liste je: "+lgt);
   for (int i=0; i<lista.size(); i++){
       GridFSDBFile f = lista.get(i);
       r = f.getFilename();
       System.out.println("Fajlnejm je: "+r);
   }
   System.out.println("Lista 3 : "+lista);
   
    }
    
    
   public void proba(String str) {
    MongoClient client = new MongoClient();
    DB dab = client.getDB("digiteka");
    MongoDatabase db = client.getDatabase("digiteka");
    MongoCollection<Document> collection = db.getCollection("objekti");
    GridFS gf = new GridFS(dab, "objekti");
    ArrayList<String> nazivi = new ArrayList();

    Document query = new Document();
    query.append("$regex", "S");
    query.append("$options", "i");

    Document find = new Document();
    find.append("filename", query);
    System.out.println("NESTO");
    String r;
   List<GridFSDBFile> list = gf.find("CASE - Copy.pdf");
   Integer lgt = list.size();
   System.out.println("Velicina liste je: "+lgt);
   for (int i=0; i<list.size(); i++){
       GridFSDBFile f = list.get(i);
       r = f.getFilename();
       System.out.println("Fajlnejm je: "+r);
   }
   System.out.println("Lista 4 : "+list);
}
   
   
}
    

