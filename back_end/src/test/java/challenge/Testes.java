package challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.infoprice.challenge.carrinho.CarrinhoCompras;
import co.infoprice.challenge.carrinho.CarrinhoComprasFactory;
import co.infoprice.challenge.carrinho.Item;
import co.infoprice.challenge.carrinho.Produto;

public class Testes {
	CarrinhoComprasFactory ccf = new CarrinhoComprasFactory();
	List<Produto> produtos = new ArrayList<Produto>();
	List<Item> itens = new ArrayList<Item>();
	
	public void criarCarrinhos(Integer qtdCarrinhos, Integer qtdProdutos){
		for (int i = 0; i < qtdCarrinhos; i++) {
			CarrinhoCompras c = ccf.criar("Cliente-"+i);
			for (int j = 0; j < qtdProdutos; j++) {
				produtos.add(new Produto((long) i, "Produto" + j));
				c.adicionarItem(produtos.get(j), new BigDecimal(j*10), j);
			}
			
		}
	}

	@Test
	public void valorTotalDoCarrinho(){
		criarCarrinhos(1,2);
		assertEquals(new BigDecimal(10), ccf.criar("Cliente-0").getValorTotal());
	}
	
	@Test
	public void ticketMedio(){
		criarCarrinhos(3,2);
		assertEquals(new BigDecimal(10), ccf.getValorTicketMedio());
	}

	
	@Test
	public void invalidarCarrinho(){
		ccf.criar("Fabio Higor");
		assertEquals(true, ccf.invalidar("Fabio Higor"));
		
	}
	@Test
	public void invalidarCarrinhoInexistente(){
		ccf.criar("Fabio Higor");
		assertEquals(false, ccf.invalidar("JOAO"));
		
	}
	@Test
	public void criarCarrinhoSemCliente() {
		CarrinhoCompras carrinho = ccf.criar(null);
		assertNull(carrinho.getIdentificador());
	}
}
