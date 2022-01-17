package com.leonardoguedex.pedidos.service;
import com.leonardoguedex.pedidos.domain.entity.Pedido;
import com.leonardoguedex.pedidos.domain.repository.PedidoRepository;
import com.leonardoguedex.pedidos.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(()-> new ObjectNotFoundException("Objeto Não Encontrado! id: "
                + id + " tipo: " + Pedido.class.getName()));
    }
}
