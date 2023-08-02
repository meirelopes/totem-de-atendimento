package model;

import enumeration.FormaPagamento;

import java.math.BigDecimal;
import java.util.List;

public class Carrinho {

    private Long id;
    private BigDecimal valorTotal;
    private boolean fechado;
    private FormaPagamento formaPagamento;
    private List<ItemProduto> itens;
}
