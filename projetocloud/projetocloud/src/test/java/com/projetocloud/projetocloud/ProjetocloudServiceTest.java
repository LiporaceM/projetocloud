package com.projetocloud.projetocloud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import com.projetocloud.projetocloud.exception.UsuarioException;
import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Usuario;
import com.projetocloud.projetocloud.repository.CartaoRepository;
import com.projetocloud.projetocloud.repository.UsuarioRepository;
import com.projetocloud.projetocloud.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario_DeveCriarUsuario_QuandoUsuarioNaoExistir() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");
        usuario.setCartoes(new ArrayList<>());

        when(usuarioRepository.findUsuarioByCpf(any(String.class))).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);

        // Assert
        assertNotNull(usuarioCriado);
        verify(usuarioRepository, times(1)).findUsuarioByCpf(any(String.class));
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void criarUsuario_DeveLancarExcecao_QuandoUsuarioJaExistir() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");

        when(usuarioRepository.findUsuarioByCpf(any(String.class))).thenReturn(Optional.of(usuario));

        // Act & Assert
        UsuarioException exception = assertThrows(UsuarioException.class, () -> {
            usuarioService.criarUsuario(usuario);
        });

        assertEquals("Usuario com cpf informado já cadastrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findUsuarioByCpf(any(String.class));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void criarUsuario_DeveAssociarCartoes() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");

        Cartao cartao = new Cartao();
        cartao.setAtivo(true);

        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(cartao);
        usuario.setCartoes(cartoes);

        when(usuarioRepository.findUsuarioByCpf(any(String.class))).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);

        // Assert
        assertNotNull(usuarioCriado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(cartaoRepository, times(1)).save(any(Cartao.class));
    }

    @Test
    void associarCartao_DeveLancarExcecao_QuandoCartaoInativo() {
        // Arrange
        Usuario usuario = new Usuario();
        Cartao cartao = new Cartao();
        cartao.setAtivo(false);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            usuarioService.associarCartao(cartao, usuario);
        });

        assertEquals("Não posso associar um cartão inativo ao usuário", exception.getMessage());
        verify(cartaoRepository, never()).save(any(Cartao.class));
    }

    @Test
    void buscaUsuario_DeveRetornarUsuario_QuandoIdExistir() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(usuario));

        // Act
        Usuario usuarioEncontrado = usuarioService.buscaUsuario(1);

        // Assert
        assertNotNull(usuarioEncontrado);
        assertEquals(1, usuarioEncontrado.getId());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void buscaUsuario_DeveRetornarNull_QuandoIdNaoExistir() {
        // Arrange
        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Act
        Usuario usuarioEncontrado = usuarioService.buscaUsuario(1);

        // Assert
        assertNull(usuarioEncontrado);
        verify(usuarioRepository, times(1)).findById(1);
    }
}
