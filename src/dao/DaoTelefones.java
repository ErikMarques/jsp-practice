package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Telefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	// Criando o método para salvar os dados
	public void salvar(Telefones telefone) {
		try {
			// String sql que irá persistir os dados no banco ao chamar o método salvar
			String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
			// String sql = "insert into produto(nome, quantidade, valor) values(?, ?, ?)";
			// Preparando a instrução e passando a url sql
			PreparedStatement insert = connection.prepareStatement(sql);

			// informando onde cada informação será persistida no banco de dados
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			// preparando para inserir as informações no banco de dados
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
	public List<Telefones> listar(Long user) throws Exception {

		List<Telefones> listar = new ArrayList<Telefones>(); // Criando a lista do tipo telefones

		String sql = "select * from telefone where usuario = " + user; // Preparação da url SQL para select no banco

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Telefones telefone = new Telefones();
			// A cada verificação positiva é criado um novo objeto

			// ADICIONANDO OS OBJETOS NA LISTA BEANCadastroProdutos

			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));

			listar.add(telefone);
		}
		return listar; // Retornando a lista com os objetos
	}

	public void delete(String id) {

		try {

			String sql = "delete from telefone where id = '" + id + "'";
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
}
