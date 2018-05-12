package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MakeGroupBean;
import bean.SessionBean;
import model.MakeGroupModel;

public class MakeGroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("session");
		// セッション情報がない場合はエラー
		if (sessionBean == null) {
			throw new IOException();
		}
		// beanに情報セット
		MakeGroupBean groupBean = new MakeGroupBean();
		groupBean.setUserName(sessionBean.getUserName());
		groupBean.setUserNo(sessionBean.getUserNo());

		// 会員情報取得
		MakeGroupModel model = new MakeGroupModel();
		try {
			groupBean = model.searchMemberList(groupBean);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		req.setAttribute("bean", groupBean);
		req.getRequestDispatcher("/WEB-INF/jsp/makeGroup.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String[] memberList = req.getParameterValues("memberList");
		for(String member : memberList) {
			System.out.println(member);
		}
	}

}
