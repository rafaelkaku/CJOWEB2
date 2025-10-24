package br.edu.ifspcjo.ads.cjoweb2.servelts;

import java.io.IOException;
import java.time.LocalDate;

import br.edu.ifspcjo.ads.cjoweb2.dao.AnimalDao;
import br.edu.ifspcjo.ads.cjoweb2.model.Animal;
import br.edu.ifspcjo.ads.cjoweb2.model.User;
import br.edu.ifspcjo.ads.cjoweb2.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/animalRegisterServlet")
public class AnimalRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AnimalRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Parâmetros do formulário do animal
		Long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String species = req.getParameter("species");
		
        // CORRETO: Pega "dateOfBirth" (que vem do seu formulário JSP)
		LocalDate dateOfBirth = LocalDate.parse(req.getParameter("birth_date")); 
		
		String description = req.getParameter("description");
		String status = req.getParameter("status");

		String url;
		HttpSession session = req.getSession(false);
		
		// Lógica de sessão é idêntica
		if(session == null || session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			User user = (User)session.getAttribute("user");
			
			// Usa o AnimalDao
			AnimalDao animalDao = new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
			
			// Cria um objeto Animal
			Animal animal = new Animal();
			animal.setName(name);
			animal.setSpecies(species);
			animal.setDateOfBirth(dateOfBirth);
			animal.setDescription(description);
			animal.setStatus(status);
			animal.setUserId(user.getId()); // Define o ID do usuário
			
			// Lógica de salvar (id=0) ou atualizar (id>0)
			if(id == 0) {
				if(animalDao.save(animal)) {
					req.setAttribute("result", "registered");
				}
			} else {
				animal.setId(id);
				if(animalDao.update(animal)) {
					req.setAttribute("result", "registered");
				}
			}
            
			// CORRETO: Redireciona para "animalRegister.jsp" (sem hífen)
			url = "/animal-register.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Pega os parâmetros como String primeiro, sem dar erro
		String action = req.getParameter("action");
		String animalIdParam = req.getParameter("animal-id"); 
		
		String url = null;
		HttpSession session = req.getSession(false);
		
		// 2. Verifica se o usuário está logado
		if(session == null || session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		// 3. VERIFICAÇÃO NOVA: Se os parâmetros não existem, manda para a home
		else if (action == null || animalIdParam == null) {
			url = "/homeServlet"; // Não podemos fazer nada sem os parâmetros
		}
		// 4. Se está logado E os parâmetros existem, continua
		else {
			try {
				// 5. Só agora é seguro converter o ID
				Long animalId = Long.parseLong(animalIdParam); 
				
				AnimalDao animalDao = new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
				Animal animal = animalDao.getAnimalById(animalId); 
				
				if(animal != null) {
					if(action.equals("update")) {
						req.setAttribute("animal", animal); 
						url = "/animal-register.jsp"; // Corrigido (sem hífen)
					}
					if(action.equals("delete")) {
						animalDao.delete(animalId); 
						url = "/homeServlet";
					}
				} else {
					// Animal não encontrado no banco
					url = "/homeServlet";
				}
			} catch (NumberFormatException e) {
				// Se o ID não for um número (ex: "abc")
				url = "/homeServlet";
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}}