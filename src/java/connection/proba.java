/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Part;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author nikol
 */
public class proba {
    
    public void sacuvajObjekat(Document objekat) { //unos dokumenta
        

        MongoClient client = new MongoClient();
        
        MongoDatabase db = client.getDatabase("objekat_baza");
        MongoCollection<Document> coll = db.getCollection("objekat_coll"); 
    
        Document objekat1 = new Document();
        objekat1 = objekat;
    
        coll.insertOne(objekat1);
    
    }
    
    public GridFSDBFile nadjiObjekatNaziv(String nazivDela){ //za pretragu po punom naslovu
    
        MongoClient client = new MongoClient();
        DB db = client.getDB("digiteka");
        DBCollection collection = db.getCollection("objekti");
    
        GridFS gfsPdf = new GridFS(db, "objekti");
        GridFSDBFile output = gfsPdf.findOne(new BasicDBObject("filename", nazivDela));
        String ime = output.getFilename();

        return output;
    }

    public GridFSDBFile nadjiObjekatNaz(String nazivDela){ //za pretragu kolekcije
    
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("digiteka");
        MongoCollection<Document> coll = db.getCollection("objekti");
        GridFSDBFile output = null;

        return output;
    }

    public ArrayList<String> nadjiObjekatNaziv1(String nazivDela) {
    
        MongoClient client = new MongoClient();
        DB db = client.getDB("digiteka");
        DBCollection collection = db.getCollection("objekti");
        ArrayList<String> nazivi = new ArrayList();
        GridFS gfsPDF = new GridFS(db, "objekti");
   
        GridFSDBFile file = new GridFSDBFile();
    
        file.put("filename", new BasicDBObject("$regex", nazivDela));
 
    
        System.out.println("file to string : "+file.toString());
    
        Cursor cursor = collection.find();
        while (cursor.hasNext()) {
            System.out.println("cursor : "+cursor.next());
        
            nazivi.add(cursor.toString());
        }
    
        return nazivi;
    }


    public void sacuvajFajl1(String nazivDela, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto, Part filePart) throws FileNotFoundException, IOException{
            
        MongoClient client = new MongoClient();
        
        DB db = client.getDB("digiteka");

        
        FileInputStream inputStream = null;
        
        
        
        GridFS objekti = new GridFS(db, "objekti");
        
        try {         
            inputStream = (FileInputStream) filePart.getInputStream();
        } catch (FileNotFoundException e) {
            System.out.println("Ne mogu da pronadjem fajl"+e);

        }
        
        GridFSInputFile pdf = objekti.createFile(inputStream, nazivDela);
    
        BasicDBObject meta = new BasicDBObject("naziv", nazivDela);
        
        ArrayList<String> tags = new ArrayList<>();
        tags.add("PDF");
        tags.add("Uputstvo");
        
        meta.append("tags", tags);
        
        pdf.setMetaData(meta);
        pdf.save();
        
        System.out.println("Object id je: " + pdf.get("_id"));
        System.out.println("Fajl je sacuvan!");
        System.out.println("Pregled fajla");
        
        GridFSDBFile gridFile = objekti.findOne(new BasicDBObject("filename", "relaciona-digitalizacija.pdf"));
        
        FileOutputStream outputStream = new FileOutputStream("C:\\MongoDB\\Server\\3.2\\data\\db\\"+nazivDela);
        gridFile.writeTo(outputStream);
    }
    
    public void nadjiObjekatNazivi1(String nazivDela) {
    
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("digiteka");
        MongoCollection<Document> collection = db.getCollection("objekti");
    
        ArrayList<String> nazivi = new ArrayList();
    
        String[] arr = nazivDela.split(" ");    
        nazivi.addAll(Arrays.asList(arr));
    
  
        Bson filter = eq("_id", 0);
    
        List<Document> all = collection.find(filter).into(new ArrayList<Document>());
    
        for (Document cur :all) {
            System.out.println(cur);
        }

    
    /* 
        Bson filter = eq("$regex", nazivDela);
        Bson projection = Projections.fields(Projections.include("filename"), Projections.excludeId());
        List<Document> all = collection.find().into(new ArrayList<Document>());
    */

    }
    
    
   public List<GridFSDBFile> nadjiObjekatAutor(String autor) {

    DBBroker conn = new DBBroker();
    MongoClient client = new MongoClient();
    DB dab = client.getDB("digiteka");
    GridFS gf = new GridFS(dab, "objekti");
    ArrayList<String> nazivi = new ArrayList();
    List<GridFSDBFile> lista = null;
    BasicDBObject query = new BasicDBObject();
    BasicDBObject query1 = new BasicDBObject();
    
    Integer brojReci = conn.prebroj(autor);          


    if (brojReci == 1) {
        
        List<BasicDBObject> objekti = new ArrayList<BasicDBObject>();
        objekti.add(new BasicDBObject("metadata.imeAutora", new BasicDBObject("$regex", autor).append("$options", "i")));
        objekti.add(new BasicDBObject("metadata.prezimeAutora", new BasicDBObject("$regex", autor).append("$options", "i")));
        
        query.put("$or", objekti);

        lista = gf.find(query);
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

        lista = gf.find(q);
   }      
    return lista;
}
    
}
