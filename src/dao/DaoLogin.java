package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class DaoLogin {

	// Precisamos de um objeto do tipo Connection para se conectar ao nosso banco.
	private Connection connection;

	// Craindo um construtor default ( SEM DIGITAR NADA PRESSIONE AS TECLAS CTRL +
	// ESPACO.
	public DaoLogin() {

		// Dizendo ao construtor que precisamos atribuir ao connection a conexão do
		// banco de dados.
		connection = SingleConnection.getConnection();

	}

	// Método para validar os dados recebidos por parametros no login
	//É NECESSÁRIO LANÇAR A EXCESSÃO POIS O MÉTODO IRÁ REALIZAR UMA CONSULTA AO BANCO DE DADOS.
	public boolean validarLogin(String login, String senha) throws SQLException {
		System.out.println("Entrando no método validarLogin");
		
		String sql = "select * from usuario where login = '" +login+ "' and senha = '" +senha+ "'";		//Utilizado ' pois se trata de uma String
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		System.out.println("A conexão foi um sucesso!");
		
		//Se houver um resultSet é porque ele encontrou o usuário
		if (resultSet.next()) {
			
			System.out.println("Foi encontrado um usuário cadastrado com esse user e senha");
			
			return true;			//Possui usuário
			
		} else {
			
			System.out.println("Não foi encontrado um usuário cadastrado com esse user e senha");
			
			return false;         //Não possui usuário
		}
	}
}














