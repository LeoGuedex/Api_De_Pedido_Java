package com.leonardoguedex.pedidos.domain.repository;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
