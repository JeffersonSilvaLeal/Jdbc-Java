package dao;

import java.sql.Connection;

import conexao.jdbc.SingleConnection;

public class UserJdbcDao {

	
	private Connection connection;
	
	public UserJdbcDao() {
		// Instancia a conexão
		connection = SingleConnection.getConnection();
	}
}
