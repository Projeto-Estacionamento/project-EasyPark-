package com.backend.EasyPark.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Email(message = "E-mail deve ser valído! formato: exemplo@dominio.com, user.name@empresa.org, usuario+teste@exemplo.net")
    private String email;
    private String telefone;

    @CPF(message = "CPF não existe ou digitado incorretamente")
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    /*@ManyToOne
    @JoinColumn(name = "plano_id")  // Especifica a coluna de associação
    private Plano plano;*/

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AssinaturaPlano> assinaturas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Veiculo> veiculos; // Relacionamento de Usuario com Veiculo


}
