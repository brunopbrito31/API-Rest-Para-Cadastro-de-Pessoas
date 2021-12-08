package com.example.atsd201751087727.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    private String nome;

    private Date dataNascimento;

    private String cpf;

    private String tipoSanguineo;

    @OneToMany(mappedBy = "titularEndereco")
    private List<Endereco> enderecosCadastrados;
}
