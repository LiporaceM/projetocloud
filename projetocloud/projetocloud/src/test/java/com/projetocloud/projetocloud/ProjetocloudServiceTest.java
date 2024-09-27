package com.projetocloud.projetocloud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Usuario;
import com.projetocloud.projetocloud.service.UsuarioService;

@SpringBootTest
class ProjetocloudServiceTest {

	@Autowired UsuarioService service;

	@Test 
	public void should_create_usuario() throws Exception {
		Usuario item = new Usuario();
		item.setId(1);
		item.setNome("Paulo");
		item.setCpf("1");
    	item.setDataNascimento(2020-10-2T00:00:00);
		item.setEmail("teste@teste.com");
		item.setEndereco("5a avenida, new york");
		item.setCelular("988438283");
		item.setCartoes(cartoes);

		Usuario result = service.criarUsuario(item);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.getId() == 1);
		Assertions.assertEquals(result.getNome(), item.getNome());

	}

	@Test
	public void should_get_item() throws Exception {
		Usuario item = new Usuario();
		item.setNome("Jorge");
		item.setCpf("1");
		item.setDataNascimento(2002-10-31T00:00:00);
		item.setEmail("teste@teste.com");
		item.setId(5);
		item.setEndereco("5a avenida, new york");
		item.setCelular("928394822");
		item.setCartoes(cartoes);

		Usuario response = service.getItem(5);

		Assertions.assertNull(response);
		Assertions.assertTrue(response.getId(5) == response);
		Assertions.assertEquals(response.getCpf(), response.getCpf());


	}

	@Test
	public void should_create_item_if_usuario_has_less_five_less_cartoes() throws Exception {
		for (int i = 0; i < 5; i++) {
			Usuario item = new Usuario();
			item.setNome("Felipe");
			item.setCpf("1");
			item.setDataNascimento(2002-10-31T00:00:00);
			item.setEmail("teste@teste.com");
			item.setId(i);
			item.setEndereco("5a avenida, new york");
			item.setCelular("923432356");
			item.setCartoes(cartoes + i);
		}

		Cartao newCartao = new Cartao();
		item.setId(10);
		item.setStatus(1);
		item.setCVV(342);
		item.setLimite(4000,00);
		item.setNumero("4035-0324-2034-2034");
		item.setTransacoes(transacoes);
		Cartao response = service.criarCartao(new Cartao());

		Assertions.assertNotNull(response);
		Assertions.assertTrue(response.getId() == 10);
		Assertions.assertEquals(response.getStatus(), 1);

	}

	@Test
	public void should_not_create_item_if_usuario_has_five_more_cartoes() throws Exception {
		for (int i = 0; i < 5; i++) {
			Usuario item = new Usuario();
			item.setNome("Felipe");
			item.setCpf("1");
			item.setDataNascimento(2002-10-31T00:00:00);
			item.setEmail("teste@teste.com");
			item.setId(i);
			item.setEndereco("5a avenida, new york");
			item.setCelular("923432356");
			item.setCartoes(cartoes + i);
		}

		Cartao newCartao = new Cartao();
		item.setId(10);
		item.setStatus(1);
		item.setCVV(342);
		item.setLimite(4000,00);
		item.setNumero("4035-0324-2034-2034");
		item.setTransacoes(transacoes);
		Cartao response = service.criarCartao(new Cartao());

		Assertions.assertThrowsExactly(Exception.class, null)

	}
	}

}
