package ejercicioJDBC_conceptual_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarPersona {
	public static void showSQLExceptionInfo(SQLException e) {
		System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
		System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
		System.err.printf("SQL Estado           : %s%n", e.getSQLState());
		System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
	}

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3307/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "root";
		String password = "root";

		String sql = "INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES (?, ?, ?, ?)";
		
		System.out.println("Estableciendo conexión");
		
		try (Connection conection = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = conection.prepareStatement(sql);) {

			System.out.println("Conexión establecida con éxito!!");

			ps.setString(1, "Juan");
			ps.setString(2, "Martin");
			ps.setString(3, "Sanchez");
			ps.setInt(4, 2001);

//			executeUpdate(): Ejecuta cualquier sentencia INSERT, UPDATE o DELETE.
			int filasAfectadas = ps.executeUpdate();
			
			if(filasAfectadas > 0) {
				System.out.printf("Persona insertada correctamente%n");
			}
			System.out.println("Número de filas afectadas: " + filasAfectadas);
			
		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}
	}
}
