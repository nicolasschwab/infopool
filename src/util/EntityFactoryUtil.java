package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactoryUtil {
	private static final EntityManagerFactory em;
	static {
		try {
			em = Persistence.createEntityManagerFactory("infopool");
		} catch (Throwable ex) {
			// Log the exception
			System.err.println("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEm() {
		return em;
	}
}