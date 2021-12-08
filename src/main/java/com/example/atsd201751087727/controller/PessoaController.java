package com.example.atsd201751087727.controller;

import com.example.atsd201751087727.entity.Pessoa;
import com.example.atsd201751087727.service.EnderecoService;
import com.example.atsd201751087727.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas() {
        List<Pessoa> pessoas = service.getPessoas();
        if (pessoas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoaProcurada = service.getPessoaById(id);
        if (!pessoaProcurada.isPresent()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoaProcurada.get());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Pessoa>> findPessoaByName(@PathVariable String nome) {
        List<Pessoa> pessoasProcurada = service.getPessoaByName(nome);
        if(pessoasProcurada.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoasProcurada);
    }

    @PostMapping
    public ResponseEntity<Pessoa> addPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaAux = service.savePessoa(pessoa);
        if(!pessoa.getEnderecosCadastrados().isEmpty()){
            pessoa.getEnderecosCadastrados().stream().forEach(x -> x.setTitularEndereco(pessoaAux));
            pessoa.getEnderecosCadastrados().stream().forEach(x -> enderecoService.saveEndereco(x));
        }

        // Cria a uri do objeto inserido no banco de dados para retornar no header da request
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaAux.getIdPessoa()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoa = service.updatePessoa(id, pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        service.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }
}
