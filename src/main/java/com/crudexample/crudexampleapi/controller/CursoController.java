package com.crudexample.crudexampleapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crudexample.crudexampleapi.model.Curso;
import com.crudexample.crudexampleapi.service.CursoService;

import jakarta.validation.Valid;

/*
 * 
 * AQUI COLOCAMOS OS ENDPOINTS DA NOSSA APLICACAO "/ONDEQUEREMOSCHEGAR"
 * 
 */

 @RestController
 @RequestMapping("/api")
public class CursoController {
    
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    CursoService cursoService;

    //endpoint POST (objetivo e GRAVAR os dados)
    //cria curso e retorna o location desse curso Ex: http://localhost:8080/api/cursos/1
    //Anotacao @Valid para validar os campos no nosso model
    @PostMapping("/cursos")
    public ResponseEntity<Curso> criarCurso (@Valid  @RequestBody Curso curso){
        return cursoService.criarCurso(curso);
    }

    /*
    CRIAR CURSOS POREM SEM RETORNAR O LOCATION
    
    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criarCurso (@RequestBody Curso curso){
         return cursoService.criarCurso(curso);
    }
    */

    //endpoint GET (objetivo: BUSCAR TODOS os dados)
    @GetMapping("/cursos")
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listarCursos(){
        return cursoService.listarCursos();
    }

    //endpoint GET (objetivo: BUSCAR o dado PELO ID)
    @GetMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> buscarCursoPeloId(@PathVariable(value = "id") Long id){
        return cursoService.buscarCursoPeloId(id);
    }

    //endpoint PUT (objetivo: ATUALIZAR os dados)
    @PutMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> atualizarCursoPeloId(@PathVariable(value = "id") Long id, @RequestBody Curso curso){
        return cursoService.atualizarCursoPeloId(curso, id);
    }

    //endpoint DELETE (objetivo: DELETAR os dados)
    @DeleteMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)                                                //esse @RequestBody e opcional
    public ResponseEntity<Object> excluirCursoPeloId(@PathVariable(value = "id") Long id){//}, @RequestBody Curso curso){
        return cursoService.excluirCursoPeloId(id);
    }

    
}

