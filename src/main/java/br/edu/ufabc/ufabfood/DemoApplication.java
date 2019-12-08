package br.edu.ufabc.ufabfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.ufabc.ufabfood.dao.JdbcUsuarioRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
		JdbcUsuarioRepository repo = context.getBean(JdbcUsuarioRepository.class);

	}
}
