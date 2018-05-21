package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupTalkBean;
import bean.SessionBean;
import bean.UserTalkBean;
import model.MainPageModel;

public class MainPageServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッション情報取得
		HttpSession session = req.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("session");

		// 初期化
		List<UserTalkBean> talkAndMemberList = new ArrayList<UserTalkBean>();
		List<GroupTalkBean> groupInfoList = new ArrayList<GroupTalkBean>();

		// セッションが情報がなかった場合エラー
		if (sessionBean == null) {
			throw new IOException();
		}

		// 会員一覧を取得
		Map<String, String> userInfo = MainPageModel.getUserInfo(sessionBean.getUserNo());
		if (userInfo == null) {
			throw new IOException();
		}

		// 会員の人数分最新メッセージを取得
		for (Map.Entry<String, String> entry : userInfo.entrySet()) {
			UserTalkBean talkBean = new UserTalkBean();
			// 最新の会話を取得
			talkBean = MainPageModel.searchTalk(sessionBean.getUserNo(), entry.getKey());

			// 会話情報がない場合はデフォルトメッセージをセット
			if(talkBean.getMessage() == null) {
				talkBean.setMessage("会話を始めましょう");
			}

			// リストに追加
			talkAndMemberList.add(talkBean);
		}

		// グループ情報一覧取得
		groupInfoList = MainPageModel.searchGroupInfo(sessionBean.getUserNo());


		// リクエストにセット
		req.setAttribute("userList", talkAndMemberList);
		req.setAttribute("groupList", groupInfoList);
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}
