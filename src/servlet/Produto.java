package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BeanCadastroProdutos;
import bean.BeanCursoJsp;
import dao.DaoProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto") // Mapeamento do WebServlet recebendo aqui o action de um formulário
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto(); // Não estava como private

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {

			String acao = request.getParameter("acao");
			String produto = request.getParameter("product");

			if (acao.equalsIgnoreCase("delete")) {

				daoProduto.delete(produto);
				System.out.println("Produto deletado!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCadastroProdutos beanCadastroProdutos = daoProduto.consultar(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("product", beanCadastroProdutos);
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("listartodos")) { // Carrega os dados na tabela ao acessar a pagina de
																// cadastro.

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

		System.out.println("Entrou no doPost da Servlet Produto");

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			System.out.println("Resetando os campos do formulário");
			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			// Variaveis que irão receber os valores da tela
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			BeanCadastroProdutos produto = new BeanCadastroProdutos();

			String msg = null;
			String msg_produto = null;
			boolean podeInserir = true;

			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null); // produto.setId(Long.parseLong(id));

			if (nome == null || nome.isEmpty()) {
				produto.setNome(nome);

				if (quantidade != null || quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));

					if (valor != null || valor.isEmpty()) {
						produto.setValor(Double.parseDouble(valor));
					} else {
						msg = "Valor deve ser informado";
						podeInserir = false;
						request.setAttribute("product", produto);
					}
				} else {
					msg = "Quantidade deve ser informado";
					podeInserir = false;
					request.setAttribute("product", produto);
				}

			} else {
				msg = "Nome deve ser informado";
				podeInserir = false;
				request.setAttribute("product", produto);
			}

			try

			{

				// CADASTR0 - VERIFICAÇÃO SE JÁ EXISTE UM PRODUTO CADASTRADO COM A MESMA
				// INFORMAÇÃO

				if (id == null || id.isEmpty() && podeInserir) {
					if (daoProduto.validarProduto(nome) && podeInserir) {

						daoProduto.salvar(produto);
						msg = "Cadastro salvo!";

					} else if (daoProduto.validarProduto(nome) == false) {

						msg = "Produto já cadastrado!";
						msg_produto = "Último produto inserido: : " + nome;
						produto.setNome("");
						request.setAttribute("product", produto);
					}
				}

				// ATUALIZAÇÃO - VERIFICAÇÃO SE JÁ EXISTE UM LOGIN CADASTRADO COM A MESMA
				// INFORMAÇÃO

				else if (id != null && !id.isEmpty() && podeInserir) { // Verificando se é uma atualização de cadastro

					long testeId = Long.parseLong(request.getParameter("id"));

					if (!daoProduto.validarProduto(nome) || daoProduto.validarProduto(nome) && podeInserir) {

						daoProduto.atualizar(produto);
						msg = "Cadastro atualizado!";

					} else if (daoProduto.validarProduto(nome) == false) {

						msg = "Produto já cadastrado!";
						msg_produto = "Último produto inserido: : " + nome;
						produto.setNome("");
						request.setAttribute("product", produto);
					}
				}

				if (msg != null || msg_produto != null) {
					request.setAttribute("msg", msg);
					request.setAttribute("msg_produto", msg_produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
