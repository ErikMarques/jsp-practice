package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BeanCadastroProdutos;
import bean.BeanCursoJsp;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	// Criando o m�todo para salvar os dados
	public void salvar(BeanCadastroProdutos produto) {
		try {
			// String sql que ir� persistir os dados no banco ao chamar o m�todo salvar
			String sql = "insert into produto(nome, quantidade, valor) values(?, ?, ?)";
			// String sql = "insert into produto(nome, quantidade, valor) values(?, ?, ?)";
			// Preparando a instru��o e passando a url sql
			PreparedStatement insert = connection.prepareStatement(sql);

			// informando onde cada informa��o ser� persistida no banco de dados
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			// preparando para inserir as informa��es no banco de dados
			insert.execute();
			// Se tudo estiver ok o connection realiza um commit e persiste os dados no
			// banco
			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// Metodo para listar cadastros no banco de dados na tabela usuario
	public List<BeanCadastroProdutos> listar() throws Exception {

		List<BeanCadastroProdutos> listar = new ArrayList<BeanCadastroProdutos>(); // Criando a lista do tipo
																					// BeanCadastroProdutos

		String sql = "select * from produto"; // Prepara��o da url SQL para select no banco

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			// A cada verifica��o positiva � criado um novo objeto
			BeanCadastroProdutos beanCadastroProdutos = new BeanCadastroProdutos();

			beanCadastroProdutos.setId(resultSet.getLong("id"));
			beanCadastroProdutos.setNome(resultSet.getString("nome"));
			beanCadastroProdutos.setQuantidade(resultSet.getDouble("quantidade"));
			beanCadastroProdutos.setValor(resultSet.getDouble("valor"));

			// ADICIONANDO OS OBJETOS NA LISTA BEANCadastroProdutos
			listar.add(beanCadastroProdutos);
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

	// Lan�ando a exce��o para cima pois � apenas uma consulta
	public BeanCadastroProdutos consultar(String id) throws Exception {

		String sql = "select * from usuario where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		// Fazendo o Prepared Statement para preparar a conex�o.
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) { // Se existir um resultSet
			BeanCadastroProdutos beanCadastroProdutos = new BeanCadastroProdutos();

			beanCadastroProdutos.setId(resultSet.getLong("id"));
			beanCadastroProdutos.setNome(resultSet.getString("nome"));
			beanCadastroProdutos.setQuantidade(resultSet.getDouble("quantidade"));
			beanCadastroProdutos.setValor(resultSet.getDouble("valor"));

			// Retornando um unico objeto.
			return beanCadastroProdutos;

		}
		return null;
	}

	public boolean validarLogin(String login) throws Exception { // Lan�ando a exce��o para cima pois � apenas uma
		// consulta

		String sql = "select count(1) as qtd from usuario where login= '" + login + "'";
		// Count() retorna 1 se existir o login no banco e 0 se n�o existir.

		PreparedStatement preparedStatement = connection.prepareStatement(sql); // Fazendo o Prepared Statement para
		// preparar a conex�o.
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) { // Se existir um resultSet

			return resultSet.getInt("qtd") <= 0;
			// Se retornar 1 = true = ATUALIZAR . N�o ser� possivel salvar pois j� existe um
			// usu�rio.
			// Se retornar 0 = false = SALVAR
		}
		return false;
	}

	public void atualizar(BeanCadastroProdutos produto) {

		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());

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
