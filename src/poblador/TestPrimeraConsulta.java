package poblador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPrimeraConsulta {

	public static void showSQLExceptionInfo(SQLException e) {
		System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
		System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
		System.err.printf("SQL Estado           : %s%n", e.getSQLState());
		System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
	}

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "root", pass = "root";
		String sql = "SELECT * FROM personas";

		System.out.println("Estableciendo conexión");

		/*
		 * Se abre la conexión (conn) 
		 * Se crea un Statement (stm) 
		 * Se ejecuta el SELECT y se obtiene un ResultSet (rs)
		 */
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);) {

			System.out.println("Conexión establecida con éxito");

			int counter = 0;
			int index = 0;

			/*
			 * rs es como un puntero a la tabla de resultados que devuelve la consulta
			 * rs.next() mueve el cursor a la siguiente fila disponible
			 * Devuelve true si hay datos
			 * Devuelve false cuando ya no quedan filas
			 */
			
			while (rs.next()) {
//				solo procesa filas donde el año de nacimiento termina en 0
				if (rs.getInt("nacimiento") % 10 == 0) {
//					Obtiene el valor de cada columna de esa fila
					System.out.printf("%d[%s,%s,%s,%d]%n", index, "nombre="+rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getInt(4));
					counter++;
				}
				index++;
			}
			System.out.printf("Elementos mostrados: %d%n", counter);
			System.out.printf("Elementos leidos (totales): %d%n", index);

		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}
		System.out.println("bye");
	}

}
