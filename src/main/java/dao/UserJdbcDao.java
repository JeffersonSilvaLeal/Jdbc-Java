package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.jdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserJdbcJava;

public class UserJdbcDao {

	private Connection connection;

	public UserJdbcDao() {
		// Instância a conexão
		connection = SingleConnection.getConnection();
	}

	// Salvar gera o id automatico através de um sequênciador cirado no banco de dados!!
	public void salvar(UserJdbcJava userJdbcJava) {

		try {

			// Sql
			String sql = "insert into userjdbcjava (nome, email) values (?,?)";
			// Prepara ele
			PreparedStatement insert = connection.prepareStatement(sql);

			// Dados
			insert.setString(1, userJdbcJava.getNome());
			insert.setString(2, userJdbcJava.getEmail());
			// Executa o insert
			insert.execute();
			// Salva no banco
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Salva telefone do usuário
	public void salvarTelefone(Telefone telefone) {
	
		try {
			
			String sql = "INSERT INTO telefoneuser(numero, tipo, userpessoa) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
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
	public UserJdbcJava buscarPorId(Long id) {
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
	
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		String sql = "  select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userjdbcjava as users ";
		sql += " on fone.userpessoa = users.id ";
		sql += "  where users.id = " + idUser;
		
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				
				userFone.setEmail(resultSet.getString("nome"));
				userFone.setNome(resultSet.getString("numero"));
				userFone.setNumero(resultSet.getString("email"));
				
				beanUserFones.add(userFone);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return beanUserFones;
		
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
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// Delete
	public void deletar(Long id) {
		try {

			// SQL
			String sql = "delete from userjdbcjava where id = " + id;
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
			e.printStackTrace();
		}
	}

	public void deleteFonePorUsuario(Long idUser) {
		
		String sqlFone = " delete from telefoneuser where userpessoa = " + idUser;
		String sqlUser = " delete from userjdbcjava where id = " + idUser;
		
		try {
			
			// Deleta Primeiro a entidade filha
			PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
			preparedStatement.executeUpdate();
			connection.commit();
			
			// Deleta a entidade Pai
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeUpdate();
			connection.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
