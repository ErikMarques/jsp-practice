package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection; // O Connection foi importado de java.sql.Connection

	public DaoUsuario() { // Chamada do construtor default da classe

		connection = SingleConnection.getConnection();

	}

	// Método para salvar os dados
	// Esse método lança uma excessão por default pois irá realizar acesso ao banco
	// de dados

	public void salvar(BeanCursoJsp usuario) { // Fazer o import do BeanCursoJsp

		try {

			String sql = "insert into usuario(nome, login, senha, telefone) values (?, ?, ?, ?)"; // Preparando a string
																									// SQL - Os ?,?
			// são
			// parametros que serão recebidos na ordem
			// login, senha
			PreparedStatement insert = connection.prepareStatement(sql); // Preparando a instrução SQL e passando a
																			// String sql construida - Toda instrução
																			// SQL lança excessão e deve ser tratada.

			// Passando as posições onde cada informação será inserida
			insert.setString(1, usuario.getNome());// A nova senha será inserida na segunda coluna que corresponde ao
			// campo Senha no banco de dados.
			insert.setString(2, usuario.getLogin()); // O novo login será inserido na primeira coluna que corresponde ao
														// campo Login no banco de dados.
			insert.setString(3, usuario.getSenha());// A nova senha será inserida na segunda coluna que corresponde ao
													// campo Senha no banco de dados.
			insert.setString(4, usuario.getTelefone());// O telefone será inserido na coluna que corresponde
														// ao
			// campo telefone no banco de dados.

			// Realizando o insert no banco de dados
			insert.execute();

			// SE TUDO OCORRER BEM O CONNECTION REALIZA O COMMIT SALVANDO AS ALTERAÇÕES
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace(); // Imprime o erro no console se necessario
			try {
				connection.rollback(); // Rollback é necessario pois anula todas as alterações que seriam realizadas em
										// caso de algum erro.
			} catch (SQLException e1) {
				e1.printStackTrace(); // Imprime o erro no console se necessário
			}
		}
	}

	// Metodo para listar cadastros no banco de dados na tabela usuario
	public List<BeanCursoJsp> listar() throws Exception {

		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>(); // Criando a lista do tipo BeanCursoJsp

		String sql = "select * from usuario"; // Preparação da url SQL para select no banco

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp(); // A cada verificação positiva é criado um novo objeto

			beanCursoJsp.setId(resultSet.getLong("id")); // Atribuindo ao objeto o valor de id resgatado
			beanCursoJsp.setNome(resultSet.getString("nome")); // Atribuido ao objeto o valor de senha resgatado
			// na tabela usuário na coluna nome
			beanCursoJsp.setLogin(resultSet.getString("login")); // Atribuindo ao objeto o valor de login resgatado
																	// na tabela usuário na coluna login
			beanCursoJsp.setSenha(resultSet.getString("senha")); // Atribuido ao objeto o valor de senha resgatado
																	// na tabela usuário na coluna senha
			beanCursoJsp.setTelefone(resultSet.getString("telefone")); // Atribuindo ao objeto o valor da coluna
																		// telefone na tabela usuario.

			// ADICIONANDO OS OBJETOS NA LISTA BEANCURSOJSP
			listar.add(beanCursoJsp);
		}
		return listar; // Retornando a lista com os objetos
	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception { // Lançando a exceção para cima pois é apenas uma
																// consulta

		String sql = "select * from usuario where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql); // Fazendo o Prepared Statement para
																				// preparar a conexão.
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) { // Se existir um resultSet
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));

			return beanCursoJsp; // Retornando um unico objeto.

		}
		return null;
	}

	public boolean validarLogin(String login) throws Exception { // Lançando a exceção para cima pois é apenas uma
		// consulta

		String sql = "select count(1) as qtd from usuario where login= '" + login + "'";
		// Count() retorna 1 se existir o login no banco e 0 se não existir.

		PreparedStatement preparedStatement = connection.prepareStatement(sql); // Fazendo o Prepared Statement para
		// preparar a conexão.
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) { // Se existir um resultSet

			return resultSet.getInt("qtd") <= 0;
			// Se retornar 1 = true = ATUALIZAR . Não será possivel salvar pois já existe um
			// usuário.
			// Se retornar 0 = false = SALVAR
		}
		return false;
	}

	public void atualizar(BeanCursoJsp usuario) {

		try {
			String sql = "update usuario set nome = ?, telefone = ?, login = ?, senha = ? where id = "
					+ usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getTelefone());
			preparedStatement.setString(3, usuario.getLogin());
			preparedStatement.setString(4, usuario.getSenha());
			preparedStatement.executeUpdate();

			connection.commit();

		} catch (Exception e) {

			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
