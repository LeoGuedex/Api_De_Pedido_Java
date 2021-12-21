package com.leonardoguedex.pedidos.domain.repository;

import com.leonardoguedex.pedidos.domain.entity.Cidade;
import com.leonardoguedex.pedidos.domain.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {


}
