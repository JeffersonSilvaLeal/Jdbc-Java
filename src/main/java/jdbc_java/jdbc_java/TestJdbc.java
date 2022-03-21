package jdbc_java.jdbc_java;

import org.junit.Test;

import conexao.jdbc.SingleConnection;

public class TestJdbc {

	
	// testando conexão do banco 
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}
}
