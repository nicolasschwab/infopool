package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactoryUtil {
	private static final EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("infopool");
		} catch (Throwable t) {
			// Log the exception
			System.err.println("Initial EntityManagerFactory creation failed." + t);
			throw new ExceptionInInitializerError(t);
		}
	}

	public static EntityManagerFactory getEm() {
		return emf;
	}
}