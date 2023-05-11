package com.crudexample.crudexampleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudexample.crudexampleapi.model.Aluno;

/*
 * 
 * REPOSITORY SERVE PARA NOS AJUDAR A PERSISTIR OS DADOS
 * 
 */

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long>{
    
}
