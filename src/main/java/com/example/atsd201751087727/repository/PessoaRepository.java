package com.example.atsd201751087727.repository;

import com.example.atsd201751087727.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    default List<Pessoa> findByName(String name) {
        return this.findAll().stream()
                .filter(x -> x.getNome().equals(name)).collect(Collectors.toList());
    }

}
