package com.example.atsd201751087727.controller;

import com.example.atsd201751087727.entity.Endereco;
import com.example.atsd201751087727.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAllEndereco() {
        List<Endereco> enderecos = service.getEnderecos();
        if (enderecos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findEnderecoById(@PathVariable Long id) {
        Optional<Endereco> enderecoBuscado = service.getEnderecoById(id);
        if (!enderecoBuscado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(enderecoBuscado.get());
    }

    @GetMapping("/{logradouro}")
    public ResponseEntity<List<Endereco>> findEnderecoByLogradouro(@PathVariable String logradouro) {
        List<Endereco> enderecosBuscados = service.getEnderecoByLogradouro(logradouro);
        if(enderecosBuscados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(enderecosBuscados);
    }

    @PostMapping
    public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) {
        endereco = service.saveEndereco(endereco);
        // Cria a uri do objeto inserido no banco de dados para retornar no header da request
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(uri).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        endereco = service.updateEndereco(id, endereco);
        return ResponseEntity.ok().body(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        service.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
