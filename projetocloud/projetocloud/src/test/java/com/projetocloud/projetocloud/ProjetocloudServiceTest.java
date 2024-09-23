package com.projetocloud.projetocloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetocloud.projetocloud.model.Usuario;
import com.projetocloud.projetocloud.service.UsuarioService;

@SpringBootTest
class ProjetocloudServiceTest {

	@Autowired UsuarioService service;

	@Test 
	public void should_create_usuario() throws Exception {
		Usuario item = new Usuario();
		item.setId(1);
		item.setNome("Novo Usuário");
		item.setCpf("1");
		item.setDataNascimento();
		item.setEmail("teste@teste.com");
		item.setEndereco("5a avenida, new york");
		item.setCelular("988438283");
		item.setCartoes(1);

		Usuario result = service.criarUsuario(item);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.getId() == 1);
		Assertions.assertEquals(result.getNome(), item.getNome());

	}

}
