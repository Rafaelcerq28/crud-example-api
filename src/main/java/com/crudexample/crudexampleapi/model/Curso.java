package com.crudexample.crudexampleapi.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 *
 * AQUI CRIAMOS A ENTIDADE CURSO PARA SER GRAVADA NO BANCO E INSERIMOS AS ANOTACOES
 *  
 */

//Entity e uma anotacao para informar ao spring que essa classe e uma tabela no banco de dados
@Entity
//Anotacao para setarmos o nome da tabela
@Table(name = "curso")
public class Curso {
 
    /* Teste para criar um CreatedAt sem utilizar anotacao passando a data direto no construtor do objeto
    public Curso() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = new Date();
        this.createdAt = formatador.format(data);
    }
     */

    //Anotacao de ID e informacao do tipo de geracao desses IDs (Nesse caso e sequencial)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Anotacao para informar que esse campo nao pode ser nulo
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private String pessoaInstrutora;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    //Getters e Setters
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPessoaInstrutora() {
        return pessoaInstrutora;
    }

    public void setPessoaInstrutora(String pessoaInstrutora) {
        this.pessoaInstrutora = pessoaInstrutora;
    }

    
}

