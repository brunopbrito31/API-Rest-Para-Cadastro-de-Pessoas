package com.example.atsd201751087727.service;

import com.example.atsd201751087727.entity.Endereco;
import com.example.atsd201751087727.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco saveEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> saveEnderecos(List<Endereco> enderecos) {
        return repository.saveAll(enderecos);
    }

    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long idEndereco) {
        return repository.findById(idEndereco);
    }

    public List<Endereco> getEnderecoByLogradouro(String logradouro) {
        return repository.findByLogradouro(logradouro);
    }

    public String deleteEndereco(Long idEndereco) {
        repository.deleteById(idEndereco);
        return "endereço removido !! " + idEndereco;
    }

    public Endereco updateEndereco(Long id, Endereco endereco) {
        Endereco enderecoAntigo = repository.getById(id);
        updateEntityRepository(enderecoAntigo, endereco);
        return repository.save(enderecoAntigo);
    }

    private void updateEntityRepository(Endereco enderecoAntigo, Endereco enderecoNovo) {
        enderecoAntigo.setTitularEndereco(enderecoNovo.getTitularEndereco());
        enderecoAntigo.setBairro(enderecoNovo.getBairro());
        enderecoAntigo.setComplemento(enderecoNovo.getComplemento());
        enderecoAntigo.setNumero(enderecoNovo.getNumero());
        enderecoAntigo.setLogradouro(enderecoNovo.getLogradouro());
    }
}
