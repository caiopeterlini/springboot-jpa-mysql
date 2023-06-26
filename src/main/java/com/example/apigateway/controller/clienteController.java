package com.example.apigateway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigateway.model.Cliente;
import com.example.apigateway.model.Endereco;
import com.example.apigateway.repository.clienteRepository;
import com.example.apigateway.repository.enderecoRepository;

@RestController()
@RequestMapping("/api/v1")
public class clienteController {

    @Autowired
    private clienteRepository _clienteRepository;
    @Autowired
    private enderecoRepository _enderecoRepository;

    @PostMapping(value = "/cliente")
    public ResponseEntity<Cliente> CreateCliente(@RequestBody Cliente cliente) {
        _clienteRepository.save(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping(value = "/cliente")
    public List<Cliente> Getcliente() {
        return (List<Cliente>) _clienteRepository.findAll();
    }

    @GetMapping(value = "/cliente/{codigo}")
    public Optional<Cliente> GetClienteCod(@PathVariable("codigo") long codigo) {
        return _clienteRepository.findByCodigo(codigo);
    }

    @PostMapping(value = "/cliente/{codigo}/endereco")
    public ResponseEntity<Endereco> CreateCliente(@PathVariable("codigo") long codigo, @RequestBody Endereco endereco) {
        if (_clienteRepository.findByCodigo(codigo).isPresent()) {
            _enderecoRepository.save(endereco);
            return ResponseEntity.ok().body(endereco);
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping(value = "/cliente/{codigo}/endereco/{indice}")
    public Optional<List<Endereco>> GetEnderecoIndice(@PathVariable("codigo") long codigo,
            @PathVariable("indice") long indice) {
        if (_clienteRepository.findByCodigo(codigo).isPresent()) {
            return _enderecoRepository.findByIndice(indice);
        }
        return null;
    }

    @GetMapping(value = "/cliente/{codigo}/endereco")
    public Optional<List<Endereco>> GetEnderecoCodigo(@PathVariable("codigo") long codigo) {
        return _enderecoRepository.findByCodigo(codigo);
    }

    @GetMapping(value = "/cliente/endereco/{cidade}")
    public Optional<List<Endereco>> GetEnderecoCidade(@PathVariable("cidade") long cidade) {
        return _enderecoRepository.findByCidade(cidade);
    }

}
