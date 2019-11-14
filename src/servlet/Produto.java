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
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {

				daoProduto.delete(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produto", daoProduto.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCadastroProdutos beanCadastroProdutos = daoProduto.consultar(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produto", beanCadastroProdutos); // Va
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("listartodos")) { // Carrega os dados na tabela ao acessar a pagina de
																// cadastro.

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produto", daoProduto.listar());
				view.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

		try {

			System.out.println("Entrou no doPost da Servlet Produto");

			String acao = request.getParameter("acao");

			if (acao != null && acao.equalsIgnoreCase("salvar")) {

				// Variaveis que irão receber os valores da tela
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String quantidade = request.getParameter("quantidade");
				String valor = request.getParameter("valor");

				BeanCadastroProdutos produto = new BeanCadastroProdutos();

				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null); // produto.setId(Long.parseLong(id));
				produto.setNome(nome);
				produto.setQuantidade(Double.parseDouble(quantidade));
				produto.setValor(Double.parseDouble(valor));

				System.out.println("Salvando dados do produto na tabela:");

				daoProduto.salvar(produto);
				String msg;
				msg = "Produto salvo no banco com sucesso!";
				request.setAttribute("msg", msg);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");

				request.setAttribute("produto", daoProduto);

				view.forward(request, response); // Fazendo o redirecionamento da view

				System.out.println("TERMINOU, Produto salvo com sucesso!");

			} else {

				System.out.println("A ação é nula, verifique!");

				String msg;
				msg = "O produto não foi salvo!";
				request.setAttribute("msg", msg);
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

}
