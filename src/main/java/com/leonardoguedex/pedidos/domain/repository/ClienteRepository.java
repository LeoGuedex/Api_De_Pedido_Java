package com.leonardoguedex.pedidos.domain.repository;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

        @Transactional(readOnly = true)
        Cliente findByEmail(String email);
}
