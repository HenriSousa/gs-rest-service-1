package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

@Repository
public class PessoaDAO {
    
    @Autowired
    private EntityManager entityManager;
    
    public Pessoa salvar(Pessoa pessoa) {
        entityManager.persist(pessoa);
        return pessoa;
    }

    public List<Pessoa> buscarTodos() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> query = builder.createQuery(Pessoa.class);
        query.from(Pessoa.class);
        return entityManager.createQuery(query).getResultList();
    }
    
    public Pessoa buscarPorId(Long id) {
        return entityManager.find(Pessoa.class, id);
    }

    public Pessoa atualizar(Pessoa pessoa) {
        return entityManager.merge(pessoa);
    }

    public void deletar(Pessoa pessoa) {
        entityManager.remove(pessoa);
    }

}
