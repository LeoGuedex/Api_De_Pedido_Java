package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Cliente;
import com.leonardoguedex.pedidos.domain.entity.ItemPedido;
import com.leonardoguedex.pedidos.domain.entity.PagamentoComBoleto;
import com.leonardoguedex.pedidos.domain.entity.Pedido;
import com.leonardoguedex.pedidos.domain.enums.EstadoPagamento;
import com.leonardoguedex.pedidos.domain.repository.ItemPedidoRepository;
import com.leonardoguedex.pedidos.domain.repository.PagamentoRepository;
import com.leonardoguedex.pedidos.domain.repository.PedidoRepository;
import com.leonardoguedex.pedidos.exception.AuthorizationException;
import com.leonardoguedex.pedidos.exception.ObjectNotFoundException;
import com.leonardoguedex.pedidos.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! id: "
                + id + " tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.00);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());

        emailService.sendOrderConfirmationEmail(pedido);

//        emailService.sendOrderconfirmationHtmlEmail(pedido);

        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso Negado");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Cliente cliente = clienteService.find(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}