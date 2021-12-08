package com.example.atsd201751087727.repository;

import com.example.atsd201751087727.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    default List<Endereco> findByLogradouro(String logradouro) {
        return this.findAll().stream()
                .filter(x -> x.getLogradouro().equals(logradouro)).collect(Collectors.toList());
    }

}
