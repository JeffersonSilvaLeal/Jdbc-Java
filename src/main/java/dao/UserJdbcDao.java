package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import conexao.jdbc.SingleConnection;
import model.UserJdbcJava;

public class UserJdbcDao {

	
	private Connection connection;
	
	public UserJdbcDao() {
		// Instância a conexão
		connection = SingleConnection.getConnection();
	}
	
	
	public void salvar(UserJdbcJava userJdbcJava) {
	
		try {
			
			// Sql
			String sql = "insert into userjdbcjava (id, nome, email) values (?,?,?)";
			// Prepara ele
			PreparedStatement insert = connection.prepareStatement(sql);
			// Dados
			insert.setLong(1, userJdbcJava.getId());
			insert.setString(2,	userJdbcJava.getNome());
			insert.setString(3, userJdbcJava.getEmail());
			// Executa o insert
			insert.execute();
			// Salva no banco
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
