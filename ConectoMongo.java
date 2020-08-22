package login;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConectoMongo {
	
	private static MongoClient mc = new MongoClient("localhost", 27017);
	private static MongoDatabase md = (MongoDatabase) mc.getDatabase("centro_masajes");
	public static String user;
	
	public ConectoMongo() {
		
	}
	
	public MongoDatabase bd() {
		return ConectoMongo.md;
	}

}
