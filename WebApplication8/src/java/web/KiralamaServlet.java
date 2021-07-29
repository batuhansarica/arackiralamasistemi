package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AracDAO;
import dao.AracTeslimDAO;
import dao.KiralamaDAO;
import dao.MusteriDAO;
import model.Arac;
import model.Kiralama;
import model.Musteri;



@WebServlet("/KiralamaServlet/*")
public class KiralamaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KiralamaDAO kiralamaDAO;
	private AracTeslimDAO aracTeslimDAO;

      
	public void init() {
	
		kiralamaDAO=new KiralamaDAO();
		aracTeslimDAO=new AracTeslimDAO();
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
					showKiralamaNewForm(request, response);
					break;
				case "/insert":
					insertKiralama(request, response);
					break;
				case "/delete":
					deleteKiralama(request, response);
					break;
				case "/edit":
					showKiralamaEditForm(request, response);
					break;
				case "/update":
					updateKiralama(request, response);
					break;
				
                                default:
					listKiralama(request, response);
					break;
				
				}
			}
		 catch (SQLException ex) {
			throw new ServletException(ex);
		}
}
		
	


	
	private void listKiralama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<Kiralama> listKiralama = kiralamaDAO.selectAllKiralama();
		request.setAttribute("listKiralama", listKiralama);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-kiralama.jsp");
		dispatcher.forward(request, response);
	}

	private void showKiralamaNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Arac> aracListesi = new AracDAO().selectAllArac();
		List<Musteri> musteriListesi = new MusteriDAO().selectAllMusteri();
		request.setAttribute("listArac", aracListesi);
		request.setAttribute("listMusteri", musteriListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-kiralama.jsp");
		dispatcher.forward(request, response);
	}

	private void showKiralamaEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Kiralama existingUser = kiralamaDAO.selectKiralama(id);
		List<Arac> aracListesi = new AracDAO().selectAllArac();
		List<Musteri> musteriListesi = new MusteriDAO().selectAllMusteri();
		request.setAttribute("listArac", aracListesi);
		request.setAttribute("listMusteri", musteriListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-kiralama.jsp");
		request.setAttribute("kiralama", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException {
		request.setCharacterEncoding("UTF-8");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer aracId = Integer.parseInt(request.getParameter("arac"));
		Integer musteriId = Integer.parseInt(request.getParameter("musteri"));
		Arac arac = new AracDAO().selectArac(aracId);
		Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
		List<Kiralama> kiralist=kiralamaDAO.selectAllKiraArac(aracId,baslangicTarihi);
		if(kiralist.size()>0) {
			request.setAttribute("error", "Arac secilen tarih araliginda kiradadir ");
			showKiralamaNewForm(request, response);
		}else {
			Kiralama newKiralama = new Kiralama(arac,musteri,baslangicTarihi,bitisTarihi);
			kiralamaDAO.insertKiralama(newKiralama);
			response.sendRedirect("KiralamaServlet/listKiralama");
		}
		
	}

	private void updateKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer aracId = Integer.parseInt(request.getParameter("arac"));
		Integer musteriId = Integer.parseInt(request.getParameter("musteri"));
		Arac arac = new AracDAO().selectArac(aracId);
		Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
		Kiralama kiralama = new Kiralama(arac,musteri,baslangicTarihi,bitisTarihi);
		kiralamaDAO.updateKiralama(kiralama);
		response.sendRedirect("KiralamaServlet/listKiralama");
	}

	private void deleteKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		kiralamaDAO.deleteKiralama(id);
		response.sendRedirect("KiralamaServlet/listKiralama");
	}
	

}
