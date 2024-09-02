package com.projetocloud.projetocloud.controller;

import com.projetocloud.projetocloud.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static List<Usuario> Usuarios = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() { return new ResponseEntity(Usuarios, HttpStatus.OK);}

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") int id) {
        Usuario response = null;

        for (Usuario usuario : Usuarios) {
            if (usuario.getId() == id) {
                response = usuario;
                break;
            }
        }

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
        Usuarios.add(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }





}
