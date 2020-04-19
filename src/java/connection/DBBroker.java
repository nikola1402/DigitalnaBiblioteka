/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;


import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Part;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;



/**
 *
 * @author nikol
 */
public class DBBroker {
    
    Mongo mongo = null;
    

    
public void sacuvajFajl(String nazivDela, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto, Part filePart) throws IOException{
   
    MongoClient client = new MongoClient();       
    DB db = client.getDB("digiteka");
    InputStream inputStream = null;       
    GridFS objekti = new GridFS(db, "objekti");
       
    try {         
        inputStream = filePart.getInputStream();
    } catch (FileNotFoundException e) {
        System.out.println("Ne mogu da pronadjem fajl");
    }
        
    GridFSInputFile pdf = objekti.createFile(inputStream, nazivDela);
    
    BasicDBObject meta = new BasicDBObject("imeAutora", ime)
                            .append("prezimeAutora", prezime)
                            .append("godina", godina)
                            .append("jezik", jezik)
                            .append("kolekcija", kolekcija)
                            .append("stamparija", stamparija)
                            .append("mesto", mesto);
            
    pdf.setMetaData(meta);
    pdf.save();
        
    System.out.println("Fajl je sacuvan!");
    
}



public Integer prebroj(String nazivDela) {
        
    Integer count = 1;
    for (int i=0;i<=nazivDela.length()-1;i++) {
        if (nazivDela.charAt(i) == ' ' && nazivDela.charAt(i+1)!=' ') {
            count++;
        }
    }
    
    return count;
        
}
    
    
    
public List<GridFSDBFile> nadjiObjekatNazivi(String nazivDela) {

    MongoClient client = new MongoClient();
    DB dab = client.getDB("digiteka");
    MongoDatabase db = client.getDatabase("digiteka");
    MongoCollection<Document> collection = db.getCollection("objekti");
    GridFS gf = new GridFS(dab, "objekti");
    ArrayList<String> nazivi = new ArrayList();
    List<GridFSDBFile> lista = null;
    BasicDBObject query = new BasicDBObject();
    Integer brojReci = prebroj(nazivDela);          
    System.out.println("Broj reci : "+brojReci);
        System.out.println("Naziv dela : "+nazivDela);

    String[] arr = nazivDela.split(" ");    
    nazivi.addAll(Arrays.asList(arr));
    
    if (brojReci == 1) {
        
        query = new BasicDBObject("filename", new BasicDBObject("$regex", nazivDela)
                                                .append("$options", "i"));
        String r;
        lista = gf.find(query);
        Integer lgt = lista.size();
        
        System.out.println("Velicina liste je: "+lgt);
        
        for (int i=0; i<lista.size(); i++){
            GridFSDBFile f = lista.get(i);
            r = f.getFilename();
            System.out.println("Fajlnejm je: "+r);
            
            DBObject obj = f.getMetaData();    
        }
        System.out.println("Lista 1 : "+lista);   
        
    }
    
    else if (brojReci == 2) {
        ArrayList<String> ls = new ArrayList();
        
        for (int i=0; i<nazivi.size();i++){
            String s = nazivi.get(i);
            ls.add(s);
        }
        
        String s = ls.get(0);
        String s1 = ls.get(1);
        
        query = new BasicDBObject("filename", new BasicDBObject("$regex", s)
            .append("$regex", s1)
            .append("$options", "i"));
        
        String r;
        lista = gf.find(query);
        Integer lgt = lista.size();
        
        System.out.println("Velicina liste je: "+lgt);
        
        for (int i=0; i<lista.size(); i++){
            GridFSDBFile f = lista.get(i);
            r = f.getFilename();
            System.out.println("Fajlnejm je: "+r);
        }

   } else {
        ArrayList<String> ls = new ArrayList();
        
        for (int i=0; i<nazivi.size();i++){
            String s = nazivi.get(i);
            ls.add(s);
        }
        
        String s = ls.get(0);
        String s1 = ls.get(1);
        String s2 = ls.get(2);
        
        query = new BasicDBObject("filename", new BasicDBObject("$regex", s)
            .append("$regex", s1)
            .append("$regex", s2)
            .append("$options", "i"));
        
        String r;
        lista = gf.find(query);
        Integer lgt = lista.size();
        System.out.println("Velicina liste je: "+lgt);
        for (int i=0; i<lista.size(); i++){
            GridFSDBFile f = lista.get(i);
            r = f.getFilename();
            System.out.println("Fajlnejm je: "+r);
        }
   }
    return lista;
}



public List<GridFSDBFile> nadjiObjekatAutor(String autor) {

    MongoClient client = new MongoClient();
    DB dab = client.getDB("digiteka");
    GridFS gf = new GridFS(dab, "objekti");
    ArrayList<String> nazivi = new ArrayList();
    List<GridFSDBFile> lista = null;
    BasicDBObject query = new BasicDBObject();
    BasicDBObject query1 = new BasicDBObject();
    Integer brojReci = prebroj(autor);          


  
    if (brojReci == 1) {
        
        List<BasicDBObject> objekti = new ArrayList<BasicDBObject>();
        objekti.add(new BasicDBObject("metadata.imeAutora", new BasicDBObject("$regex", autor).append("$options", "i")));
        objekti.add(new BasicDBObject("metadata.prezimeAutora", new BasicDBObject("$regex", autor).append("$options", "i")));
        
        query.put("$or", objekti);

        String r;
        lista = gf.find(query);
        Integer lgt = lista.size();
        System.out.println("Velicina liste je: "+lgt);
        for (int i=0; i<lista.size(); i++){
            GridFSDBFile f = lista.get(i);
            r = f.getFilename();
            System.out.println("Fajlnejm je: "+r);
        }
        System.out.println("Lista 2 : "+lista);       
    }
    else {
        
        String[] arr = autor.split(" ");    
        nazivi.addAll(Arrays.asList(arr));
        ArrayList<String> ls = new ArrayList();
        
        for (int i=0; i<nazivi.size();i++){
            String s = nazivi.get(i);
            ls.add(s);
        }
        
        String s = ls.get(0);
        String s1 = ls.get(1);
        
        
        List<BasicDBObject> objekti = new ArrayList<BasicDBObject>();
        List<BasicDBObject> objekti1 = new ArrayList<BasicDBObject>();
        List<BasicDBObject> ob = new ArrayList<BasicDBObject>();
        
        objekti.add(new BasicDBObject("metadata.imeAutora", new BasicDBObject("$regex", s)
                                                            .append("$options", "i")));
        objekti.add(new BasicDBObject("metadata.prezimeAutora", new BasicDBObject("$regex", s1)
                                                            .append("$options", "i")));
        
        objekti1.add(new BasicDBObject("metadata.imeAutora", new BasicDBObject("$regex", s1)
                                                            .append("$options", "i")));
        objekti1.add(new BasicDBObject("metadata.prezimeAutora", new BasicDBObject("$regex", s)
                                                            .append("$options", "i")));
        
        query.put("$and", objekti);
        query1.put("$and", objekti1);
        
        ob.add(query);
        ob.add(query1);
        
        BasicDBObject q = new BasicDBObject();
        
        q.put("$or", ob);
                
        String r;
        lista = gf.find(q);
        Integer lgt = lista.size();
        System.out.println("Velicina liste je: "+lgt);
        for (int i=0; i<lista.size(); i++){
            GridFSDBFile f = lista.get(i);
            r = f.getFilename();
            System.out.println("Fajlnejm je: "+r);
        }

   } 
       
    return lista;
}


public void azuriraj (String nazivDela, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto){
    
    MongoClient client = new MongoClient();      
    DB db = client.getDB("digiteka");  
    InputStream inputStream = null;      
    GridFS objekti = new GridFS(db, "objekti");
    GridFSDBFile outputFile = new GridFSDBFile();
    
    outputFile = objekti.findOne(nazivDela);
    
    System.out.println("objekat : " +outputFile);
    BasicDBObject updateo = new BasicDBObject();
    updateo.put("imeAutora", ime);
    updateo.put("prezimeAutora", prezime);
    updateo.put("godina", godina);
    updateo.put("jezik", jezik);
    updateo.put("kolekcija", kolekcija);
    updateo.put("stamparija", stamparija);
    updateo.put("mesto", mesto);

    outputFile.setMetaData(updateo);
    outputFile.save();
  
    System.out.println("Fajl je azuriran!");
    } 

public void azurirajId (String id, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto){
    
    MongoClient client = new MongoClient();      
    DB db = client.getDB("digiteka");  
    InputStream inputStream = null;      
    GridFS objekti = new GridFS(db, "objekti");
    GridFSDBFile outputFile = new GridFSDBFile();
    
    System.out.println("id je : "+id);
    
    
    DBObject o = new BasicDBObject();
    o.put("_id", new ObjectId(id));
    /*
    DBObject q = new BasicDBObject();
    q.put("_id", id);*/
    outputFile = objekti.findOne(o);
    
    System.out.println("objekat : " +outputFile);
    BasicDBObject updateo = new BasicDBObject();
    updateo.put("imeAutora", ime);
    updateo.put("prezimeAutora", prezime);
    updateo.put("godina", godina);
    updateo.put("jezik", jezik);
    updateo.put("kolekcija", kolekcija);
    updateo.put("stamparija", stamparija);
    updateo.put("mesto", mesto);

    outputFile.setMetaData(updateo);
    outputFile.save();
  
    System.out.println("Fajl je azuriran!");
    } 
    
   
}

