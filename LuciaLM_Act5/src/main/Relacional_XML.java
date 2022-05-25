package main;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
/**
 * Clase para poder generar XML a partir de bases de datos relacionales.
 * Se permite buscar por cualquier campo los log que cumplan la condición
 * deseada y crear un XML que solo los incluya a ellos, al igual que ordenar por
 * cualquier campo ascendiente o descendientemente para luego crear un XML completo.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Relacional_XML {
	public static void main(String[] args) {
		Relacional_XML relXML = new Relacional_XML();
		File archivoOrdenar = new File("C:\\Users\\llemi\\Desktop\\ordenarXML.xml");
		File archivoBuscar = new File("C:\\Users\\llemi\\Desktop\\buscarXML.xml");
		File archivoNiveles = new File("C:\\Users\\llemi\\Desktop\\buscarNiveles.xml");
		relXML.ordenarPor(archivoOrdenar, "entry_date", "desc");
		relXML.buscarPor(archivoBuscar, "message", "Never gonna let you down");
		relXML.buscarPor(archivoNiveles, "log_level", "FATAL");
		relXML.buscarPor(new File("C:\\Users\\llemi\\Desktop\\buscarFecha.xml"), "entry_date", "2022-05-25 07:18:25.0");
		relXML.buscarPor(new File("C:\\Users\\llemi\\Desktop\\buscarID.xml"), "log_id", "1aa0f8b5-dbea-11ec-83cc-fc7774b47cce");
	}
	/**
	 * Buscar ciertos log que cumplen una condición y crear un XML solo con ellos.
	 * @param archivo - el archivo .xml donde se va a generar el XML.
	 * @param parametro - es cualquier campo del XML por el que se quiera buscar.
	 * @param condicion - la condición que deben cumplir los logs para ser incluidos.
	 */
	public void buscarPor(File archivo, String parametro, String condicion) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document domBuilder = builder.newDocument();
			Element root = domBuilder.createElement("logs");
			domBuilder.appendChild(root);
			// Usamos la clase ConnectionFactory, que ya sabemos que funciona correctamente.
			ConnectionFactory cf = new ConnectionFactory();
			Connection conexion = cf.getConnection();
			Statement stmt = conexion.createStatement();
			// Si se busca una cadena de texto literalmente, hay que escapar las comillas
			// usando la barra invertida. Como todos los parámetros las necesitan, las incluimos.
			ResultSet setResultado = stmt.executeQuery("select * from app_logs where " + parametro + "=\"" + condicion + "\";");
			while (setResultado.next()) {
				Element aux = domBuilder.createElement("log_id");  
				root.appendChild(aux);
				Element id = domBuilder.createElement("log_id");
				Text idTexto = domBuilder.createTextNode(setResultado.getString("log_id"));
				id.appendChild(idTexto);
				Element date = domBuilder.createElement("entry_date");
				// Al ser un elemento de tipo TimeStamp, hay que convertirlo a String antes de crear
				// un TextNode.
				Text dateTexto = domBuilder.createTextNode(setResultado.getTimestamp("entry_date").toString());
				date.appendChild(dateTexto);
				Element loggerElem = domBuilder.createElement("logger");
				Text loggerElemTexto = domBuilder.createTextNode(setResultado.getString("logger"));
				loggerElem.appendChild(loggerElemTexto);
				Element nivel = domBuilder.createElement("log_level");
				Text nivelTexto = domBuilder.createTextNode(setResultado.getString("log_level"));
				nivel.appendChild(nivelTexto);
				Element mensaje = domBuilder.createElement("message");
				Text mensajeTexto = domBuilder.createTextNode(setResultado.getString("message"));
				mensaje.appendChild(mensajeTexto);
				aux.appendChild(id);
				aux.appendChild(date);
				aux.appendChild(mensaje);
				aux.appendChild(nivel);
				aux.appendChild(mensaje);
				// Se genera el XML indentado en el archivo que pasamos como parámetro, a partir de un
				// DOM creado a partir de nuestro Document.
				Transformer transformer;
				try {
					transformer = TransformerFactory.newInstance().newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.transform(new DOMSource(domBuilder), new StreamResult(archivo));
				} catch (TransformerFactoryConfigurationError | TransformerException e) {
					e.printStackTrace();
				}
			}
		} catch (ParserConfigurationException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Ordenar logs para formar un XML ordenado por el parámetro o parámetros que
	 * queremos. Estos parámetros deben corresponderse con las columnas de la tabla:
	 * log_id, entry_date, logger, log_level o message.
	 * 
	 * También se puede decidir si ordenar ascendente o descendientemente.
	 * @param archivo - el archivo .xml donde se va a generar el XML.
	 * @param parametro - es cualquier campo del XML por el que se quiera ordenar.
	 * @param orden - ascendiente o descendiente, "asc" o "desc".
	 */
	public void ordenarPor(File archivo, String parametro, String orden) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document domBuilder = builder.newDocument();
			Element root = domBuilder.createElement("logs");
			domBuilder.appendChild(root);
			// Usamos la clase ConnectionFactory, que ya sabemos que funciona correctamente.
			ConnectionFactory cf = new ConnectionFactory();
			Connection conexion = cf.getConnection();
			Statement stmt = conexion.createStatement();
			ResultSet setResultado = stmt.executeQuery("select * from app_logs order by " + parametro + " " + orden + ";");
			while (setResultado.next()) {
				Element aux = domBuilder.createElement("log_id");  
				root.appendChild(aux);
				Element id = domBuilder.createElement("log_id");
				Text idTexto = domBuilder.createTextNode(setResultado.getString("log_id"));
				id.appendChild(idTexto);
				Element date = domBuilder.createElement("entry_date");
				// Al ser un elemento de tipo TimeStamp, hay que convertirlo a String antes de crear
				// un TextNode.
				Text dateTexto = domBuilder.createTextNode(setResultado.getTimestamp("entry_date").toString());
				date.appendChild(dateTexto);
				Element loggerElem = domBuilder.createElement("logger");
				Text loggerElemTexto = domBuilder.createTextNode(setResultado.getString("logger"));
				loggerElem.appendChild(loggerElemTexto);
				Element nivel = domBuilder.createElement("log_level");
				Text nivelTexto = domBuilder.createTextNode(setResultado.getString("log_level"));
				nivel.appendChild(nivelTexto);
				Element mensaje = domBuilder.createElement("message");
				Text mensajeTexto = domBuilder.createTextNode(setResultado.getString("message"));
				mensaje.appendChild(mensajeTexto);
				aux.appendChild(id);
				aux.appendChild(date);
				aux.appendChild(mensaje);
				aux.appendChild(nivel);
				aux.appendChild(mensaje);
				// Creación del XML indentado a partir del DOM.
				Transformer transformer;
				try {
					transformer = TransformerFactory.newInstance().newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.transform(new DOMSource(domBuilder), new StreamResult(archivo));
				} catch (TransformerFactoryConfigurationError | TransformerException e) {
					e.printStackTrace();
				}
			}
		} catch (ParserConfigurationException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}