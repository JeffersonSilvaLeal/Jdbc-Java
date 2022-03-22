package jdbc_java.jdbc_java;

import java.util.List;

import org.junit.Test;

import dao.UserJdbcDao;
import model.UserJdbcJava;

public class TesteJdbcJava {

	// Insert
	@Test
	public void insert() {
		UserJdbcDao userJdbcDao = new UserJdbcDao();
		UserJdbcJava userJdbcJava = new UserJdbcJava();

		userJdbcJava.setId(6L);
		userJdbcJava.setNome("ueldon");
		userJdbcJava.setEmail("Ueldon@gmail.com");

		userJdbcDao.salvar(userJdbcJava);
	}

	// list
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
	
	// Buscar
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
	
	// Atualizar
	@Test
	public void atualizar() {
		
		try {
			UserJdbcDao dao = new UserJdbcDao();
			
			UserJdbcJava userJdbcJava = dao.buscar(4L);
			
			userJdbcJava.setNome("Nome Atualizado");
			
			dao.atualizar(userJdbcJava);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
