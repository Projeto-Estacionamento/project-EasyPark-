package com.backend.EasyPark.seletor;

import com.backend.EasyPark.entities.AssinaturaPlano;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class BaseSeletor {

    private int pagina;
    private int limite;

    public BaseSeletor() {
        this.limite = 0;
        this.pagina = 0;
    }

    public boolean temPaginacao() {
        return this.limite > 0 && this.pagina > 0;
    }

    //Filtro por data.
    static void filtroPeriodo(Root<AssinaturaPlano> root, CriteriaBuilder cb, List<Predicate> predicates, LocalDate dataInicio, LocalDate dataFinal, String nomeAtributo) {
        if (dataInicio != null && dataFinal != null) {
            predicates.add(cb.between(root.get(nomeAtributo), dataInicio, dataFinal));
        } else if (dataInicio != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nomeAtributo), dataInicio));
        } else if (dataFinal != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nomeAtributo), dataFinal));
        }
    }
}
