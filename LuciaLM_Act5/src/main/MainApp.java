package main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Clase principal para probar los log y su volcado a la base de datos.
 * @author LuciaLM
 * @version 1.0
 */
public class MainApp {
	private static final Logger logger = LogManager.getLogger(MainApp.class);
	public static void main(String[] args) {
		// Probamos los distintos niveles de log (trace no aparece).
		logger.trace("Never gonna give you up");
		logger.debug("Never gonna let you down");
		logger.info("Never gonna run around and desert you");
		logger.warn("Never gonna make you cry");
		logger.error("Never gonna say goodbye");
		logger.fatal("Never gonna tell a lie and hurt you");
		// Provocamos una excepción a propósito para guardar los datos
		// en la columna correspondiente.
		int a, b;
		try {
			a = 15;
			b = 0;
			logger.info("Division = " + (a/b));
		} catch (Exception e) {
			logger.error("Division by 0 :(", e);
		}
	}
}