package com.example.atsd201751087727.service;

import com.example.atsd201751087727.entity.Pessoa;
import com.example.atsd201751087727.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> getPessoas() {
        return repository.findAll();
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return repository.findById(id);
    }

    public List<Pessoa> getPessoaByName(String name) {
        return repository.findByName(name);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public String deletePessoa(Long id) {
        repository.deleteById(id);
        return "pessoa removida !! " + id;
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Pessoa pessoaAntiga = repository.getById(id);
        updateEntityRepository(pessoaAntiga, pessoa);
        return repository.save(pessoaAntiga);
    }

    private void updateEntityRepository(Pessoa pessoaAntiga, Pessoa pessoaNova) {
        pessoaAntiga.setNome(pessoaNova.getNome());
        pessoaAntiga.setCpf(pessoaNova.getCpf());
        pessoaAntiga.setDataNascimento(pessoaNova.getDataNascimento());
        pessoaAntiga.setTipoSanguineo(pessoaNova.getTipoSanguineo());
        pessoaAntiga.setEnderecosCadastrados(pessoaNova.getEnderecosCadastrados());
    }
}
