package web;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import dao.AracDAO;
import dao.AracTeslimDAO;
import dao.KiralamaDAO;
import dao.UserDAO;
import model.Arac;
import model.AracTeslim;
import model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private static final String LOGGED_USER = "loggedUser";
        private KiralamaDAO kiralamaDAO;
	private AracTeslimDAO aracTeslimDAO;

      
	
	public void init() {
		userDAO = new UserDAO();
                kiralamaDAO=new KiralamaDAO();
		aracTeslimDAO=new AracTeslimDAO();
	}
	protected boolean SessionController(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute(LOGGED_USER);
		if(user!= null) {
			
				request.setAttribute("logged", user);
			return true;
		}
		else {
			return false;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			if(SessionController(request) == false) {
				switch (action) {
					case "/loginUser":
						loginAccount(request, response);
						break;
					case "/uyeOl":
						showUyeOl(request, response);
						break;
					case "/insertUyeOl":
						insertUyeOl(request, response);
						break;
					default:
						diplayLoginPage(request, response);
						break;
				}
			}
			else {
				switch (action) {
				case "/cikisYap":
					cikisYap(request, response);
					break;
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				case "/home":
					showHome(request, response);
					break;
				case "/listUser":
					listUser(request, response);
					break;
				case "/listAracTeslim":
					listAracTeslim(request, response);
					break;
				case "/insertAracTeslim":
					insertAracTeslim(request, response);
					break;
				case "/profilim":
					showProfilimForm(request, response);
					break;
				case "/updateProfilim":
					updateProfilim(request, response);
					break;
                                case "/kiralamalistTemizle":
                                        kiralamalistTemizle(request, response);
                                        break;
				default:
                                        showHome(request, response);
                                        break;
				}
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-user.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-user.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insert-user.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User newUser = new User(name, password,firstName,lastName);
		userDAO.insertUser(newUser);
		response.sendRedirect("listUser");
	}
	private void insertUyeOl(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User newUser = new User(name, password,firstName,lastName);
		userDAO.insertUser(newUser);
		response.sendRedirect("loginUser");
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(id,name, password,firstName,lastName);
		userDAO.updateUser(user);
		response.sendRedirect("listUser");
	}
	private void updateProfilim(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(id,name, password,firstName,lastName);
		userDAO.updateUser(user);
		response.sendRedirect("home");
	}
	private void showProfilimForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user=(User)request.getSession().getAttribute("loggedUser");
		int id = user.getId();
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profil.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("listUser");

	}
	private void diplayLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("insert-login.jsp");
		dispatcher.forward(request, response);
	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String sifre = request.getParameter("password");
                        
                        
			User user = new UserDAO().authtenticateUser(name, sifre);
			if(user == null) {
				request.setAttribute("error", "Email veya parola bilgisi yanl�� girildi! ");
				diplayLoginPage(request, response);
			}
                        
			else {
				request.getSession().setAttribute(LOGGED_USER, user);
				request.removeAttribute("error");
				response.sendRedirect("home.jsp");
			}
	
       
        }
	private void cikisYap(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		request.getSession().setAttribute(LOGGED_USER, null);
		diplayLoginPage(request, response);
	}
	
	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	private void showUyeOl(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("insert-uye.jsp");
		dispatcher.forward(request, response);
	}
        private void listAracTeslim(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<AracTeslim> listAracTeslim = aracTeslimDAO.ButunAraclariGoster();
		request.setAttribute("listAracTeslim", listAracTeslim);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-aracTeslim.jsp");
		dispatcher.forward(request, response);
	}
	private void insertAracTeslim(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		kiralamaDAO.deleteKiralama(id);
		int aracId = Integer.parseInt(request.getParameter("aracid"));
		User user=(User)request.getSession().getAttribute("loggedUser");
		Arac arac = new AracDAO().selectArac(aracId);
		AracTeslim aracTeslim = new AracTeslim(arac,user,java.sql.Date.valueOf(java.time.LocalDate.now()));
		aracTeslimDAO.insertAracTeslim(aracTeslim);
		response.sendRedirect("KiralamaServlet/listKiralama");
	}
	  private void kiralamalistTemizle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		boolean listAracTeslim = aracTeslimDAO.deleteAllAracTeslim();
                request.setAttribute("listAracTeslim", listAracTeslim);
		response.sendRedirect("KiralamaServlet/listKiralama");      
                        
                        
		
		
	}
	
}
