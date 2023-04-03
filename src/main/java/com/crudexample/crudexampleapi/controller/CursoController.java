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
    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criarCurso (@RequestBody Curso curso){

        return cursoService.criarCurso(curso);
    }

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

    //endpoint POST (objetivo: DELETAR os dados)
    @DeleteMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> excluirCursoPeloId(@PathVariable(value = "id") Long id, @RequestBody Curso curso){
        return cursoService.excluirCursoPeloId(id);
    }
}
