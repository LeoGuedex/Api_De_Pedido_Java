package com.leonardoguedex.pedidos.rest.controller;

import com.leonardoguedex.pedidos.domain.entity.Cliente;
import com.leonardoguedex.pedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

}
