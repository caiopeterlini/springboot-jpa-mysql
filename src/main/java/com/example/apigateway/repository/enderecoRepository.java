package com.example.apigateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apigateway.model.Endereco;

@Repository
public interface enderecoRepository extends CrudRepository<Endereco, Long> {

    @Query("select u from Endereco u where u.indice = ?1")
    Optional<List<Endereco>> findByIndice(long indice);

    @Query("select u from Endereco u where u.codigo = ?1")
    Optional<List<Endereco>> findByCodigo(long codigo);

    @Query("select u from Endereco u where u.cidade = ?1")
    Optional<List<Endereco>> findByCidade(long cidade);

}
