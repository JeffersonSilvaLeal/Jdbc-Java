package jdbc_java.jdbc_java;

import java.util.List;

import org.junit.Test;

import dao.UserJdbcDao;
import model.BeanUserFone;
import model.Telefone;
import model.UserJdbcJava;

public class TesteJdbcJava {

	// Insert
	@Test
	public void insert() {
		UserJdbcDao userJdbcDao = new UserJdbcDao();
		UserJdbcJava userJdbcJava = new UserJdbcJava();

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
			UserJdbcJava userJdbcJava = dao.buscarPorId(6L);
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

			UserJdbcJava userJdbcJava = dao.buscarPorId(4L);

			userJdbcJava.setNome("Nome Atualizado");

			dao.atualizar(userJdbcJava);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Deletar
	@Test
	public void deletar() {
		try {

			UserJdbcDao dao = new UserJdbcDao();
			dao.deletar(6L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Salvar telefone
	@Test
	public void insertTelefone() {
		
		Telefone telefone = new Telefone();
		telefone.setNumero("11 92584-2561");
		telefone.setTipo("Celular");
		telefone.setUsuario(2L);
		
		UserJdbcDao dao = new UserJdbcDao();
		dao.salvarTelefone(telefone);
		
	}
	
	
	@Test
	public void carregarFonesUser() {
		
		UserJdbcDao dao = new UserJdbcDao();
		
		List<BeanUserFone>beanUserFones = dao.listaUserFone(1L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("---------------------");
			
		}
	}
	
	
	@Test
	public void deleteUserFone() {
		
		UserJdbcDao dao = new UserJdbcDao();
		dao.deleteFonePorUsuario(1L);
		System.out.println("Excluido com sucesso!!");
	}
}
