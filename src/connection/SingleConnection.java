package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
 * 
 * @author erikm
 *
 */
public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null; // Connection do java.sql.Connection;

	// Método conectar é static para garantir que sempre será executado ao chamar
	// esta classe, com isso garantimos a conexão ao banco.
	static {
		conectar();
	}

	// Construtor da classe
	public SingleConnection() {
		conectar();
	}

	// Método para se conectar ao banco, observe que o código está em um try/catch
	// para eventuais problemas ao se conectar
	private static void conectar() {
		try {
			System.out.println("Conectando ao banco");
			
			if (connection == null) {//UTILIZE O BRAKPOINT PARA DEBUG NESSA LINHA PARA ACOMPANHAR AS MENSAGENS DE CONEXÃO NO CONSOLE!
				Class.forName("org.postgresql.Driver"); //Driver do banco PostgreSQL
				System.out.println("Driver do banco PostgreSQL");
				connection = DriverManager.getConnection(banco, user, password); //Passando os parâmetros de conexão ao banco
				System.out.println("Passando os parâmetros de conexão ao banco");
				connection.setAutoCommit(false); //Configurando o auto commit para desligado, assim podemos controlar quando os commits serão realizados
				System.out.println("Conectado ao banco de dados curso-jsp com sucesso!");
			}
			
		} catch (Exception e) {
			System.out.println("Exibindo erro de conexão: "); //Será exibida no console em caso de erro com a conexão do banco.
			e.printStackTrace(); //Erro armazenado em e que será exibido no console pelo printStackTrace
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	//Método que retorna a conexão
	public static Connection getConnection() {
		return connection;
	}
	
}
