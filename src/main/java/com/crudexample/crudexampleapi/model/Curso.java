package com.crudexample.crudexampleapi.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

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
 
    //Anotacao de ID e informacao do tipo de geracao desses IDs (Nesse caso e sequencial)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Valida o tamanho do texto
    @Size(min = 2)
    //anotacao para alterar o nome da variavel no json (lembrando que no arquivo JSON a ser recebido deve ser escrito da mesma forma)
    @JsonProperty("curso_nome")
    //Anotacao para informar que esse campo nao pode ser nulo
    @Column(nullable = false)
    private String nome;
    
    //Valida o valor minimo
    @Min(10)
    @Column(nullable = false)
    private double preco;

    @JsonProperty("instrutor_nome")
    @Size(min = 2)
    @Column(nullable = false)
    private String pessoaInstrutora;
 
    @JsonProperty("data_criacao")
    @PastOrPresent
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @JsonProperty("data_atualizacao")
    private Instant updatedAt;

    
    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Aluno> alunos;

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

