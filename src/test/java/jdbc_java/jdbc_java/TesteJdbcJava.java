package jdbc_java.jdbc_java;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import org.junit.Test;

import dao.UserJdbcDao;
import model.UserJdbcJava;

public class TesteJdbcJava {

	// testando conexão do banco
	@Test
	public void insert() {
		UserJdbcDao userJdbcDao = new UserJdbcDao();
		UserJdbcJava userJdbcJava = new UserJdbcJava();

		userJdbcJava.setId(6L);
		userJdbcJava.setNome("ueldon");
		userJdbcJava.setEmail("Ueldon@gmail.com");

		userJdbcDao.salvar(userJdbcJava);
	}

	@Test
	public void Listar() {

		try {
			UserJdbcDao dao = new UserJdbcDao();
			List<UserJdbcJava> list = dao.listar();
			
			for (UserJdbcJava userJdbcJava : list) {
				System.out.println(userJdbcJava);
				System.out.println("----------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void buscar() {
		
		UserJdbcDao dao = new UserJdbcDao();
		try {
			UserJdbcJava userJdbcJava = dao.buscar(6L);
			System.out.println(userJdbcJava);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
