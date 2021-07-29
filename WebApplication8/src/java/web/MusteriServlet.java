package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MusteriDAO;
import model.Musteri;


@WebServlet("/MusteriServlet/*")
public class MusteriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusteriDAO musteriDAO;
	
	public void init() {
		musteriDAO = new MusteriDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getRequestURI();
		action = action.substring(action.lastIndexOf("/")).toLowerCase();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertMusteri(request, response);
				break;
			case "/delete":
				deleteMusteri(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateMusteri(request, response);
				break;
			default:
				listMusteri(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listMusteri(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Musteri> listMusteri = musteriDAO.selectAllMusteri();
		request.setAttribute("listMusteri", listMusteri);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-musteri.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-musteri.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Musteri existingUser = musteriDAO.selectMusteri(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-musteri.jsp");
		request.setAttribute("musteri", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String ad = request.getParameter("ad");
		String soyad = request.getParameter("soyad");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		Musteri newMusteri = new Musteri(ad,soyad,telefon,adres);
		musteriDAO.insertMusteri(newMusteri);
		response.sendRedirect("MusteriServlet/listMusteri");
	}

	private void updateMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ad = request.getParameter("ad");
		String soyad = request.getParameter("soyad");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		Musteri musteri = new Musteri(id, ad, soyad,telefon,adres);
		musteriDAO.updateMusteri(musteri);
		response.sendRedirect("MusteriServlet/listMusteri");
	}

	private void deleteMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		musteriDAO.deleteMusteri(id);
		response.sendRedirect("MusteriServlet/listMusteri");

	}

}
