package jdbc_java.jdbc_java;

import org.junit.Test;

import dao.UserJdbcDao;
import model.UserJdbcJava;

public class TesteJdbcJava {

	// testando conexão do banco
	@Test
	public void initBanco() {
		UserJdbcDao userJdbcDao = new UserJdbcDao();
		UserJdbcJava userJdbcJava = new UserJdbcJava();
		
		userJdbcJava.setId(6L);
		userJdbcJava.setNome("ueldon");
		userJdbcJava.setEmail("Ueldon@gmail.com");

		userJdbcDao.salvar(userJdbcJava);
	}
}
