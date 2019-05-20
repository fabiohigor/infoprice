package co.infoprice.challenge.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Teste {

	public static void main(String[] args) {

		CarrinhoComprasFactory ccf = new CarrinhoComprasFactory();
		
		CarrinhoCompras carrinho = ccf.criar("Fabio Higor");
		CarrinhoCompras carrinho2 = ccf.criar("Karen Carla");
		CarrinhoCompras carrinho3 = ccf.criar("Meiri");
		
		
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < 20; i++) {
			produtos.add(new Produto((long) i, "Produto" + i));
		}
		for (int i = 0; i < 10; i++) {
//			carrinho.getItens().add(new Item(produtos.get(i), new BigDecimal(1), 1));
			carrinho.getItens().add(new Item(produtos.get(i), new BigDecimal(i * 10), 1));
			carrinho2.getItens().add(new Item(produtos.get(i), new BigDecimal((i * 10)+1), 1));
			carrinho3.getItens().add(new Item(produtos.get(i), new BigDecimal((i * 10)+2), 1));
		}
		
		
//		IMPRESSAO
		Imprimir(carrinho,"Alterado");
		carrinho.adicionarItem(produtos.get(1), new BigDecimal(9), 10);
		Imprimir(carrinho,"Alterado");
//		Imprimir(carrinho2,"Alterado");
//		System.out.println("Total Carrinho 1 - " + ccf.criar(carrinho.getIdentificador()	).getValorTotal());
//		System.out.println("Total Carrinho 2 - " + ccf.criar(carrinho2.getIdentificador()).getValorTotal());
//		System.out.println("Total Carrinho 3 - " + ccf.criar(carrinho3.getIdentificador()).getValorTotal());
//		System.out.println("getValorTicketMedio - " + ccf.getValorTicketMedio());
//		ccf.invalidar(carrinho3.getIdentificador());
//		System.out.println("getValorTicketMedio - " + ccf.getValorTicketMedio());
		
		
		
	}
	
	
	private static void Imprimir(CarrinhoCompras carrinho, String descricao){
		System.out.println("Descricao"+ descricao);
		System.out.println("Identificador: " + carrinho.getIdentificador());
		System.out.println("Valor Total: " + carrinho.getValorTotal());
		for (Item item : carrinho.getItens()) {
				System.out.println(item.getProduto().getDescricao() +"- Valor: "+item.getValorUnitario()+" -Quantidade: "+item.getQuantidade());
				
		}
	}

}
