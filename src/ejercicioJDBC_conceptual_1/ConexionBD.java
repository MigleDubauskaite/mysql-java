package ejercicioJDBC_conceptual_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

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

		System.out.printf("Conectando a la base de datos...%n");
		try (Connection conection = DriverManager.getConnection(url, user, password);) {
			System.out.println("Conexión establecida correctamente");
		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}

	}

}
