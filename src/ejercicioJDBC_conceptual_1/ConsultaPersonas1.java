package ejercicioJDBC_conceptual_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPersonas1 {
	
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
		
		String sql = "SELECT * FROM personas WHERE nacimiento > 1990";
		
		System.out.println("Estableciendo conexión");
		
//		DriverManager: gestiona los drivers JDBC y permite establecer la conexión con la base de datos usando las credenciales
//		Devuelve un objeto Connection que representa la sesión activa con la base de datos.
		try (Connection conection = DriverManager.getConnection(url, user, password);
//				PreparedStatement: una sentencia SQL precompilada
				PreparedStatement ps = conection.prepareStatement(sql);) {
			
			System.out.println("Conexión establecida con éxito!!");
			
//			ResultSet: un objeto que contiene los resultados de una consulta SELECT
			ResultSet rs = ps.executeQuery();
//			executeQuery(): Envía la sentencia SQL a la base de datos
			
			int personasCounter = 0;
			int index = 1;
			
			while(rs.next()) {
//				cuando imprimes datos, mejor usar nombres de columna en vez de índices (rs.getString("nombre")) para evitar errores si cambian las columnas.
				System.out.printf("%d: [%s %s %s (%d)] %n", index, rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getInt("nacimiento"));
				personasCounter++;
				index++;		
			}
			System.out.printf("Filas que cumplen condición: %d%n", personasCounter);
		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}
		
		
	}

}
