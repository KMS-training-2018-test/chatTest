package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SessionBean;
import bean.UserTalkBean;
import model.DirectMessageModel;

public class DirectMessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// セッション情報取得
		HttpSession session = req.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("session");

		// パラメーター取得(会話するユーザーの会員番号)
		String targetUserNo = req.getParameter("target");

		// 初期化
		List<UserTalkBean> talkList = new ArrayList<UserTalkBean>();

		//　パラメーターチェック
		if (sessionBean == null || targetUserNo == null) {
			throw new IOException();
		}

		// 会話情報取得処理
		talkList = DirectMessageModel.searchTalkInfo(sessionBean.getUserNo(), targetUserNo);

		//
	}
}
