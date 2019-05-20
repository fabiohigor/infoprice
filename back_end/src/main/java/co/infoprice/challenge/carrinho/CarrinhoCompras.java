package co.infoprice.challenge.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private ArrayList<Item> itens;

	private String identificador;

	public CarrinhoCompras(String identificador) {
		this.identificador = identificador;
		this.itens = new ArrayList<Item>();
	}

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 * 
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes
	 * regras deverão ser seguidas: - A quantidade do item deverá ser a soma da
	 * quantidade atual com a quantidade passada como parâmetro. - Se o valor
	 * unitário informado for diferente do valor unitário atual do item, o novo
	 * valor unitário do item deverá ser o passado como parâmetro.
	 * 
	 * Devem ser lançadas subclasses de RuntimeException caso não seja possível
	 * adicionar o item ao carrinho de compras.
	 * 
	 * @param produto
	 * @param valorUnitario
	 * @param quantidade
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {

		Optional<Item> result = itens.stream().filter(item -> item.getProduto().getCodigo().equals(produto.getCodigo()))
				.findAny();

		if (result.isPresent()) {
			Item item = result.get();
			Item aux = new Item(item.getProduto(),
					valorUnitario.compareTo(item.getValorUnitario()) == 1 ? valorUnitario : item.getValorUnitario(),
					item.getQuantidade() + quantidade);
			this.removerItem(item.getProduto());
			itens.add(aux);
		} else {
			itens.add(new Item(produto, valorUnitario, quantidade));
		}
	}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de
	 * compras.
	 * 
	 * @param produto
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no
	 *         carrinho de compras e false caso o produto não exista no
	 *         carrinho.
	 */
	public boolean removerItem(Produto produto) {
		Optional<Item> result = itens.stream().filter(item -> item.getProduto().getCodigo().equals(produto.getCodigo()))
				.findAny();
		if (result.isPresent()) {
			this.itens.remove(result.get());
			return true;
		}
		return false;
	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos
	 * valores totais de todos os itens que compõem o carrinho.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return new BigDecimal(
				itens.stream().map(item -> item.getValorTotal()).mapToDouble(BigDecimal::doubleValue).sum());
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 * 
	 * @return itens
	 */
	public Collection<Item> getItens() {
		return this.itens;
	}

	/**
	 * Retorna o identificador
	 * 
	 * @return identificador
	 */
	public String getIdentificador() {
		return this.identificador;
	}
}