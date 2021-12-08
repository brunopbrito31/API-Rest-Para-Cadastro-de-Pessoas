package com.example.atsd201751087727.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "titular_id")
    private Pessoa titularEndereco;

    private String logradouro;

    private String bairro;

    private String numero;

    private String complemento;
}
