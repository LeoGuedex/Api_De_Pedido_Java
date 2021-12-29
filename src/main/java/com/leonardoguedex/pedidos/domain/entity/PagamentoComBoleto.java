package com.leonardoguedex.pedidos.domain.entity;

import com.leonardoguedex.pedidos.domain.enums.EstadoPagamento;

import java.util.Date;

public class PagamentoComBoleto extends Pagamento{

    private static final long serialVersionUID = 1L;

    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, Pedido pedido, EstadoPagamento estado, Date dataVencimento, Date dataPagamento) {
        super(id, pedido, estado);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


}
