package common.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbUtil {
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("bookmarkreminder");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void close() {
		if(emf != null) {
			emf.close();
			emf = null;
		}
	}
}
