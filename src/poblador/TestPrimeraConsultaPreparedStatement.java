package poblador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPrimeraConsultaPreparedStatement {

	public static void showSQLExceptionInfo(SQLException e) {
		System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
		System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
		System.err.printf("SQL Estado           : %s%n", e.getSQLState());
		System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
	}

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "root", pass = "root";
		String sql = "SELECT * FROM personas WHERE nombre = ? AND nacimiento = ?"; // El ? es un parámetro

		System.out.println("Estableciendo conexión");

		/*
		 * Se abre la conexión (conn) 
		 * Se crea un Statement (stm) 
		 * Se ejecuta el SELECT y se obtiene un ResultSet (rs)
		 */
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {

//			asignar valores
//			1 → primer ?
//			"VALERIA" → valor que se sustituye
			ps.setString(1, "VALERIA");
			ps.setInt(2, 2010);

//			ejecutamos
			ResultSet rs = ps.executeQuery();

			System.out.println("Conexión establecida con éxito");

			int counter = 0;
			int index = 1;

//			ResultSet ya está filtrado, así que index no refleja todas las filas de la tabla.
			while (rs.next()) {
				if (rs.getString(2).contains("F")) {
					System.out.printf("%d[%s,%s,%s,%s]%n", index, rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4));
					counter++;
				}
				index++;
			}

			System.out.printf("Filas que cumplen condición: %d%n", counter);

		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}
		System.out.println("bye");
	}

}
