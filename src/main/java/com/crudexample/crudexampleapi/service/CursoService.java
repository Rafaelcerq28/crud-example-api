package com.crudexample.crudexampleapi.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.stereotype.Service;

import com.crudexample.crudexampleapi.model.Aluno;
import com.crudexample.crudexampleapi.model.Curso;
import com.crudexample.crudexampleapi.repository.AlunoRepository;
import com.crudexample.crudexampleapi.repository.CursoRepository;

/*
 * CLASSE SERVICE SERVE PARA GERARMOS NOSSAS REGRAS DE NEGOCIO,
 * ISSO INCLUI METODOS PARA NOSSO CRUD
 */

@Service
public class CursoService {
    
    public CursoService(CursoRepository cursoRepository, AlunoRepository alunoRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
    }

    private CursoRepository cursoRepository;
    private AlunoRepository alunoRepository;

    //metodo para CRIAR curso
    public ResponseEntity<Curso> criarCurso(Curso curso){

        //persiste o curso no banco 
        Curso savedCurso = cursoRepository.save(curso);
        //Armazena o location em uma uri Ex: http://localhost:8080/api/cursos/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(savedCurso.getId()).toUri();

        //Retorna o curso com o status criado e a localizacao
        return ResponseEntity.created(location).body(savedCurso);
    }

    /*
    CRIAR CURSO POREM SEM RETORNAR A LOCATION
    public Curso criarCurso(Curso curso){
        return cursoRepository.save(curso);
    }
    */

    //Metodo para BUSCAR TODOS os cursos
    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    //Metodo para BUSCAR curso pelo ID
    public ResponseEntity<Curso> buscarCursoPeloId(Long id){
        return cursoRepository.findById(id).map(curso -> ResponseEntity.ok().body(curso)).orElse(ResponseEntity.notFound().build());
    }
    
    //Metodo para ATUALIZAR 1 item
    public ResponseEntity<Curso> atualizarCursoPeloId (Curso curso, Long id){
        return cursoRepository.findById(id).map(cursoParaAtualizar -> {
            cursoParaAtualizar.setNome(curso.getNome());
            cursoParaAtualizar.setPessoaInstrutora(curso.getPessoaInstrutora());
            cursoParaAtualizar.setPreco(curso.getPreco());
            Curso updatedCurso = cursoRepository.save(cursoParaAtualizar);
            return ResponseEntity.ok().body(updatedCurso); 
        }).orElse(ResponseEntity.notFound().build());
    }

    //Metodo para DELETAR curso pelo ID
    public ResponseEntity<Object> excluirCursoPeloId(Long id){
        return cursoRepository.findById(id).map(cursoParaDeletar -> {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    //Metodo para LISTAR todos os alunos do curso
    public ResponseEntity<Object> listarAlunos(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        
        if(curso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(curso.get().getAlunos());
    }

    //Metodo para CRIAR um aluno DENTRO do curso
    public ResponseEntity<Aluno> criarAlunoNoCurso(Long id, Aluno aluno) {
    
        Optional<Curso> curso = cursoRepository.findById(id);
        
        if(curso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        aluno.setCurso(curso.get());
        
        Aluno savedAluno = alunoRepository.save(aluno);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(savedAluno.getId()).toUri();

        //Retorna o curso com o status criado e a localizacao
        return ResponseEntity.created(location).body(savedAluno);
    }

    //Metodo para SELECIONAR um aluno NO curso

    public ResponseEntity<Object> pegarAlunoNoCursoPeloId(Long idCurso,long idAluno) {
        //busco o curso    
        Optional<Curso> curso = cursoRepository.findById(idCurso);
        //checo se ele existe, caso contrario retorno um 404
        if(curso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Optional<Aluno> aluno = null;

        //existindo um curso, faco uma varredura na lista de alunos procurando pelo id do aluno
        for (Aluno alunos : curso.get().getAlunos()) {

            if(alunos.getId() == idAluno){
                
                //encontrando o aluno busco ele no banco
                aluno = alunoRepository.findById(idAluno);
                //checo se ele existe
                if(aluno.isEmpty()){
                    return ResponseEntity.notFound().build();
                }
                break;
            }
        }

        //existindo um aluno retorno o selecionado
        return ResponseEntity.ok().body(aluno.get());
    }

}
