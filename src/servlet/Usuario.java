package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import bean.BeanCursoJsp;
import dao.DaoUsuario;

@MultipartConfig
@WebServlet("/salvarUsuario") // Mapeamento do WebServlet recebendo aqui o action de um formulário
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario(); // Importar a classe DaoUsuario

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {

				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("user", beanCursoJsp); // Va
				view.forward(request, response); // Fazendo o redirecionamento da view

			} else if (acao.equalsIgnoreCase("listartodos")) { // Carrega os dados na tabela ao acessar a pagina de
																// cadastro.

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//doGet(request, response); Lançando erro em tempo de execução.

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String telefone = request.getParameter("telefone");

			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			// Criando um objeto BeanCursoJsp
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null); /// Lançando uma exceção nessa linha: Antes:
																		/// usuario.setId(Long.parseLong(id))
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setTelefone(telefone);

			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);

			try {

				/* Inicio File upload de imagens e pdf */

				if (ServletFileUpload.isMultipartContent(request)) {

					Part imagemFoto = request.getPart("foto");

					String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));

					usuario.setFotoBase64(fotoBase64);
					usuario.setContentType(imagemFoto.getContentType());
				}

				/* Fim File upload de imagens e pdf */

				String msg = null;
				String msg_login = null;
				boolean podeInserir = true;

				// VERIFICAÇÃO DOS CAMPOS SE ESTÃO PREENCHIDOS OU NULOS
				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;
					request.setAttribute("user", usuario);

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informado";
					podeInserir = false;
					request.setAttribute("user", usuario);

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;
					request.setAttribute("user", usuario);

				} else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado";
					podeInserir = false;
					request.setAttribute("user", usuario);
				}

				// CADASTR0 - VERIFICAÇÃO SE JÁ EXISTE UM LOGIN CADASTRADO COM A MESMA
				// INFORMAÇÃO

				if (id == null || id.isEmpty()) {
					if (daoUsuario.validarLogin(login) && podeInserir) {

						daoUsuario.salvar(usuario);
						msg = "Cadastro salvo!";

					} else if (daoUsuario.validarLogin(login) == false) {

						msg = "Login já existe!";
						msg_login = "Último login: " + login;
						usuario.setLogin("");
						request.setAttribute("user", usuario);
					}
				}

				// ATUALIZAÇÃO - VERIFICAÇÃO SE JÁ EXISTE UM LOGIN CADASTRADO COM A MESMA
				// INFORMAÇÃO

				else if (id != null && !id.isEmpty()) { // Verificando se é uma atualização de cadastro

					long testeId = Long.parseLong(request.getParameter("id"));

					if (!daoUsuario.validarLogin(login) || daoUsuario.validarLogin(login) && podeInserir) {

						daoUsuario.atualizar(usuario);
						msg = "Cadastro atualizado!";

					} else if (daoUsuario.validarLogin(login) == false) {

						msg = "Login já existe!";
						msg_login = "Último login: " + login;
						usuario.setLogin("");
						request.setAttribute("user", usuario);
					}
				}

				if (msg != null || msg_login != null) {
					request.setAttribute("msg", msg);
					request.setAttribute("msg_login", msg_login);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar()); // Vai carregar a lista de usuarios e atribuir a
																		// variável usuarios para poder acessar na tela
																		// cadastrousuario.jsp
				view.forward(request, response); // Fazendo o redirecionamento da view

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	/*
	 * Converte a entrada de fluxo de dados da imagem para byte[] (um array de byte)
	 */
	private byte[] converteStreamParaByte(InputStream imagem) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}
}
