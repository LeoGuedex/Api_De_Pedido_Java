package com.leonardoguedex.pedidos.domain.repository;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {


}
