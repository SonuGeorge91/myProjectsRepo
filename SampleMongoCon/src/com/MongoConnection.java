package com;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient("localhost",27017);
		String connectPoint = client.getConnectPoint();
		MongoDatabase database = client.getDatabase("test");
	    MongoCollection<Document> collection = database.getCollection("testCol");
	    
	    String operation = "";
	    
	    //operation = "INSERT";
	    operation = "RETRIEVE";
	   // operation = "UPDATE";
	   //operation = "DELETE";
	    if(operation.equals("INSERT")){
		    Document document = new Document();
		    document.put("name", "Sonu George");
		    document.put("salary", 50000);
		    document.put("type", "FT/PT");
		    
		    Document address = new Document();
		    address.put("area", "KLM");
		    address.put("city", "KERALA");
		    
		    document.put("address", address);
		    
		    collection.insertOne(document);
	    }else if(operation.equals("RETRIEVE")){
	    	List<Document> documents = (List<Document>)collection.find().into(new ArrayList<Document>());
	    	
	    	for(Document document : documents){
	    		System.out.println(document);
	    		Document address = (Document)document.get("address");
	    		System.out.println("Area " + address.getString("area"));
	    		System.out.println("City " + address.getString("city"));
	    	}
	    }else if(operation.equals("UPDATE")){
	    	Bson filter = new Document("name","Sonu George");
	    	Bson newValue = new Document("salary",90000);
	    	Bson updateOperationDocument = new Document("$set",newValue);
	    	collection.updateOne(filter, updateOperationDocument);    	
	    }else if(operation.equals("DELETE")){
	    	Bson condition = new Document("$lt",50000);
	    	Bson filter = new Document("salary",condition);
	    	collection.deleteOne(filter);
	    }
	    client.close();
	    
	    
		System.out.println(connectPoint);
		client.close();

	}

}
