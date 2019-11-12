package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;

@WebFilter(urlPatterns = {"/*"}) //Mapeamento do WebFilter com urlPatterns - Toda requisição e resposta irá passar pelo Filter. 
public class Filter implements javax.servlet.Filter {

	private static Connection connection; //Será atribuido os valores no método init.

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //Esse método filtra o clique de um botão que vem da tela (front-end).
			throws IOException, ServletException {

		try {

			chain.doFilter(request, response);
			connection.commit(); // Se ocorreu tudo bem o commit será realizado

		} catch (Exception e) {

			try {
				e.printStackTrace();//Mostra no console a pilha de exceções 
				connection.rollback(); // Senão as alterações serão descartadas

			} catch (SQLException e1) {
				
				e1.printStackTrace();

			}

		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { // Este método init é sempre invocado
		
		connection = SingleConnection.getConnection();// Para o filtro receber a conexão que foi iniciada
		
	}

}
