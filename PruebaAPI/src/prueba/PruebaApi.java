package prueba;

import java.io.File;

import main.MainApp;
import main.Relacional_XML;
/**
 * Prueba de la actividad 5 importada como librería a este proyecto.
 * @author LuciaLM
 * @version 1.0
 */
public class PruebaApi {
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		Relacional_XML relXML = new Relacional_XML();
		
		relXML.buscarPor(new File("C:\\Users\\llemi\\Desktop\\usandoAPI.xml"), "log_level", "WARN");
		relXML.ordenarPor(new File("C:\\Users\\llemi\\Desktop\\ordenarUsandoAPI.xml"), "message", "asc");
	}
}
