package com.leonardoguedex.pedidos.domain.repository;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


}
