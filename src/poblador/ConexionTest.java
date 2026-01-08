package poblador;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionTest {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mundo?allowPublicKeyRetrieval=true&useSSL=false";
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
