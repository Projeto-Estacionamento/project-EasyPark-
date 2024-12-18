package com.backend.EasyPark.model.seletor;


import com.backend.EasyPark.model.entities.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioSeletor extends BaseSeletor implements Specification<Usuario> {

    private String nome;
    private String email;
    private String cpf;

    public boolean temFiltro() {
        return (this.nome != null && this.nome.trim().length() > 0)
                || (this.email != null && this.email.trim().length() > 0)
                || (this.cpf != null && this.cpf.trim().length() > 0);

    }

    @Override
    public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.getNome() != null && this.getNome().trim().length() > 0) {
            predicates.add(cb.like(root.get("nome"), "%" + this.getNome() + "%"));
        }
        if (this.getEmail() != null && this.getEmail().trim().length() > 0) {
            predicates.add(cb.like(root.get("email"), "%" + this.getEmail() + "%"));
        }
        if (this.getCpf() != null && this.getCpf().trim().length() > 0) {
            predicates.add(cb.like(root.get("cpf"), "%" + getCpf() + "%"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
