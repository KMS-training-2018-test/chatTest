package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SessionBean;

public class MainPageServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッション情報取得
		HttpSession session = req.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("session");
		req.setAttribute("bean", sessionBean);
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}
