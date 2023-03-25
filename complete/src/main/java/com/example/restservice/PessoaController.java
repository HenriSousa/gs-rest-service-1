package com.example.restservice;
import com.example.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaDAO pessoaDAO;

    @Autowired
    private TeleSignSMS teleSignSMS;

    @GetMapping
    public List<Pessoa> buscarTodos() {
        return pessoaDAO.buscarTodos();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable("id") Long id) {
        return pessoaDAO.buscarPorId(id);
    }

    @PostMapping
    public Pessoa adicionar(@RequestBody Pessoa pessoa) {
        pessoa = pessoaDAO.salvar(pessoa);
        teleSignSMS.enviarSMS(pessoa.getPosicaoFila(), pessoa.getNome());
        return pessoa;
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        pessoa = pessoaDAO.atualizar(pessoa);
        return pessoa;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        pessoaDAO.deletar(pessoa);
    }
    
}