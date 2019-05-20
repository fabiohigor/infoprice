package co.infoprice.challenge.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private Collection<CarrinhoCompras> carrinhos = new ArrayList<CarrinhoCompras>();

	/**
	 * Cria e retorna um novo carrinho de compras para o cliente passado como
	 * parâmetro.
	 *
	 * Caso já exista um carrinho de compras para o cliente passado como
	 * parâmetro, este carrinho deverá ser retornado.
	 *
	 * @param identificacaoCliente
	 * @return CarrinhoCompras
	 */
	public CarrinhoCompras criar(String identificacaoCliente) {
		Optional<CarrinhoCompras> result = carrinhos.stream()
				.filter(carrinho -> carrinho.getIdentificador().equals(identificacaoCliente)).findAny();
		if (result.isPresent()) {
			return result.get();
		} else {
			CarrinhoCompras cc = new CarrinhoCompras(identificacaoCliente);
			carrinhos.add(cc);
			return cc;
		}

	}

	/**
	 * Retorna o valor do ticket médio no momento da chamada ao método. O valor
	 * do ticket médio é a soma do valor total de todos os carrinhos de compra
	 * dividido pela quantidade de carrinhos de compra. O valor retornado deverá
	 * ser arredondado com duas casas decimais, seguindo a regra: 0-4 deve ser
	 * arredondado para baixo e 5-9 deve ser arredondado para cima.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTicketMedio() {
		OptionalDouble media = carrinhos.stream().map(carrinho -> carrinho.getValorTotal())
				.mapToDouble(BigDecimal::doubleValue).average();

		return new BigDecimal(media.getAsDouble());
	}

	/**
	 * Invalida um carrinho de compras quando o cliente faz um checkout ou sua
	 * sessão expirar. Deve ser efetuada a remoção do carrinho do cliente
	 * passado como parâmetro da listagem de carrinhos de compras.
	 *
	 * @param identificacaoCliente
	 * @return Retorna um boolean, tendo o valor true caso o cliente passado
	 *         como parämetro tenha um carrinho de compras e e false caso o
	 *         cliente não possua um carrinho.
	 */
	public boolean invalidar(String identificacaoCliente) {
		Optional<CarrinhoCompras> result = carrinhos.stream()
				.filter(carrinho -> carrinho.getIdentificador().equals(identificacaoCliente)).findAny();
		if (result.isPresent()) {
			carrinhos.remove(result.get());
			return true;
		} else {
			return false;
		}
	}

}
