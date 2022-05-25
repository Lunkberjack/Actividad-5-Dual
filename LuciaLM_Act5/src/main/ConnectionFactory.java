package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Clase ConnectionFactory, que establece una conexión con MySQL en este caso
 * y la devuelve mediante un método con valor de retorno de tipo Connection.
 * @author LuciaLM
 * @version 1.0
 */
public class ConnectionFactory {
	public static void main(String args[]) {  
		Connection conexion;
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Por alguna razón dio fallo cientos de veces hasta que cambié localhost por 127.0.0.1
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ejemplo","ejemplo","ejemplo");   
		} catch (Exception e) {
			e.printStackTrace();
		}  	
	}
	// El nombre de este método se incluye en nuestro archivo log4j2.xml, como atributo method del
	// nodo ConnectionFactory
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ejemplo","ejemplo","ejemplo");
	}}