package com.example.demo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_despesas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 50)
    @NotBlank(message = "Campo Nome é obrigatório!")
    @Size(max = 50)
    private String nome;

    @Column(name = "tipo_despesa", nullable = false, length = 50)
    @NotBlank(message = "Campo Tipo é obrigatório!")
    @Size(max = 50)
    private String tipo;

    @NotNull(message = "Campo Valor é Obrigatório!")
    @Column(name = "valor", nullable = false)
    private Double valor;

    @Size(max = 200)
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;



    



}
