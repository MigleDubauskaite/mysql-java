package poblador;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionTest {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3307/mundo?allowPublicKeyRetrieval=true&useSSL=false";

//		jdbc:mysql → driver de MySQL
//		localhost → la base de datos está en tu máquina
//		3307 → puerto (ojo: MySQL normalmente usa 3306)
//		mundo → nombre de la base de datos
		
//		allowPublicKeyRetrieval=true: Permite obtener la clave pública del servidor (necesario en MySQL 8+ con ciertos métodos de autenticación).
//		useSSL=false: Desactiva SSL para evitar advertencias si no está configurado.
		
		String user = "root";
		String pass = "root";

		try {
			System.out.println("Estableciendo conexión...");
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Conexión establecida con éxito");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
