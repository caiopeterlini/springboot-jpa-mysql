package com.example.apigateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apigateway.model.Cliente;

@Repository
public interface clienteRepository extends CrudRepository<Cliente, Long> {

    @Query("select u from Cliente u where u.codigo = ?1")
    Optional<Cliente> findByCodigo(long codigo);
}
