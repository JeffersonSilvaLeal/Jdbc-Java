package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.jdbc.SingleConnection;
import model.UserJdbcJava;

public class UserJdbcDao {

	private Connection connection;

	public UserJdbcDao() {
		// Instância a conexão
		connection = SingleConnection.getConnection();
	}

	// Salvar
	public void salvar(UserJdbcJava userJdbcJava) {

		try {

			// Sql
			String sql = "insert into userjdbcjava (id, nome, email) values (?,?,?)";
			// Prepara ele
			PreparedStatement insert = connection.prepareStatement(sql);
			// Dados
			insert.setLong(1, userJdbcJava.getId());
			insert.setString(2, userJdbcJava.getNome());
			insert.setString(3, userJdbcJava.getEmail());
			// Executa o insert
			insert.execute();
			// Salva no banco
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Litar
	public List<UserJdbcJava> listar() {
		// Instância a lista
		List<UserJdbcJava> list = new ArrayList<UserJdbcJava>();

		// Sql
		String sql = "select * from userjdbcjava";

		try {
			// Prepara o sql
			PreparedStatement statement = connection.prepareStatement(sql);
			// Executa no banco de dados
			ResultSet resultado = statement.executeQuery();

			// Itera no banco enquanto for true
			while (resultado.next()) {
				// Cria os objetos
				UserJdbcJava userJdbcJava = new UserJdbcJava();
				// Seta os atributos
				userJdbcJava.setId(resultado.getLong("id"));
				userJdbcJava.setNome(resultado.getString("nome"));
				userJdbcJava.setEmail(resultado.getString("email"));

				// Adiciona a lista
				list.add(userJdbcJava);
			}
			// trata os erros
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// retorna a lista
		return list;
	}

	// Buscar Apenas um Objeto
	public UserJdbcJava buscar(Long id) {
		UserJdbcJava retorno = new UserJdbcJava();

		// Sql
		String sql = "select * from userjdbcjava where id = " + id;

		try {
			// Prepara o sql
			PreparedStatement statement = connection.prepareStatement(sql);
			// Executa no banco de dados
			ResultSet resultado = statement.executeQuery();

			// Itera no banco enquanto for true
			while (resultado.next()) {
				// Cria os objetos
				UserJdbcJava userJdbcJava = new UserJdbcJava();
				// Seta os atributos
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));

			}
			// trata os erros
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// retorna a lista
		return retorno;
	}

	// Atualizar
	public void atualizar(UserJdbcJava userJdbcJava) {

		
		try {
			// SQL
			String sql = "update userjdbcjava set nome = ? where id = " + userJdbcJava.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userJdbcJava.getNome());
			preparedStatement.execute();
			connection.commit();
		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
