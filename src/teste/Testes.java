package teste;

import bean.BeanCadastroProdutos;
import dao.DaoProduto;
import dao.DaoUsuario;

public class Testes {

	public static void main(String[] args) {
		BeanCadastroProdutos produto = new BeanCadastroProdutos();

		produto.setNome("Refrigerante");
		//produto.setQuantidade(10l);
		//produto.setValor(2.75);

		DaoProduto inserirBanco = new DaoProduto();
		inserirBanco.salvar(produto);

	}

}
