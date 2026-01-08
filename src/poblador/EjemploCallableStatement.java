package poblador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploCallableStatement {

	/*
	 * Es un tipo de Statement en Java diseñado para llamar procedimientos
	 * (PROCEDURE) o funciones (FUNCTION) que ya existen en la base de datos.
	 */

//	La idea es: la lógica está guardada en la base de datos, no en tu código Java.
	
	public static void mostrarError(SQLException e) {
		System.err.println("❌ Error SQL");
		System.err.println("Mensaje : " + e.getMessage());
		System.err.println("Estado  : " + e.getSQLState());
		System.err.println("Código  : " + e.getErrorCode());
	}

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		String usuario = "root";
		String password = "root";

//      indica que vas a llamar al procedimiento almacenado
		String sql = "{call buscar_personas_por_nombre(?)}";

		System.out.println("Conectando a la base de datos...");

		try (Connection conn = DriverManager.getConnection(url, usuario, password);
				CallableStatement cs = conn.prepareCall(sql);) {

			System.out.println("Conexión establecida");

			// 4️ Asignar el parámetro de entrada (IN)
			cs.setString(1, "VALERIA");

			// 5️ ejecuta el procedimiento en la base de datos y devuelve un ResultSet
			ResultSet rs = cs.executeQuery();

			System.out.println("Resultados del procedimiento:");

			// 6️ Leer el ResultSet
			while (rs.next()) {

				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String apellido2 = rs.getString("apellido2");
				int nacimiento = rs.getInt("nacimiento");

				System.out.println("Nombre: " + nombre + " | Apellido: " + apellido + " | Apellido2: " + apellido2 + " | Nacimiento: "
						+ nacimiento);
			}

			// 7️ Cerrar ResultSet
			rs.close();

		} catch (SQLException e) {
			mostrarError(e);
		}

		System.out.println("bye");

	}

}
