package br.edu.ifspcjo.ads.cjoweb2.servelts; // Pacote do seu projeto

import java.io.IOException;
import java.util.List;

import br.edu.ifspcjo.ads.cjoweb2.dao.AnimalDao; // Alterado de ActivityDao
import br.edu.ifspcjo.ads.cjoweb2.model.Animal; // Alterado de Activity
import br.edu.ifspcjo.ads.cjoweb2.model.User;
import br.edu.ifspcjo.ads.cjoweb2.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url;
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			User user = (User)session.getAttribute("user");
			
			// --- Início das Alterações ---
			
			// 1. Usa o AnimalDao
			AnimalDao animalDao = new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
			
			// 2. Busca a lista de Animais
			List<Animal> userAnimals = animalDao.getAnimalsByUser(user);
			
			// 3. Envia a lista "userAnimals" para o JSP
			req.setAttribute("userAnimals", userAnimals);
			
			// --- Fim das Alterações ---
			
			// Mantém o nome do usuário para a barra de menu
			req.setAttribute("name", user.getName());
			url = "/home.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
}