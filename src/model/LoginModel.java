package model;

import java.sql.ResultSet;

import bean.LoginBean;
import dao.DataAccess;

/**
 * ログイン画面ビジネスロジック
 */
public class LoginModel {

	/**
	 * 必須チェック
	 *
	 * @param bean
	 * @return
	 */
	public LoginBean inputCheck(LoginBean bean) {
		// エラーメッセージ初期化
		bean.setErrorMessage("");

		// 必須チェック
		if (bean.getUserId() == null || bean.getUserId().equals("")) {
			bean.setErrorMessage("会員IDの入力は必須です");
		}
		if (bean.getPassword() == null|| bean.getPassword().equals("")) {
			bean.setErrorMessage("パスワードの入力は必須です");
		}
		return bean;
	}

	public LoginBean authentication(LoginBean bean) throws Exception {
		// 初期化
		DataAccess dataAccess = new DataAccess();
		StringBuilder sb = new StringBuilder();
		String userId = bean.getUserId();
		String password = bean.getPassword();

		// SQL作成
		sb.append("SELECT ");
		sb.append(" user_no ");
		sb.append(" ,user_name ");
		sb.append("FROM ");
		sb.append(" m_user ");
		sb.append("WHERE ");
		sb.append(" user_id = '" + userId + "' " );
		sb.append(" AND password = '" + password + "' ");

		ResultSet rs = dataAccess.selectData(sb.toString());
		if (!rs.next()) {
			bean.setErrorMessage("パスワードが一致しませんでした。");
		} else {
			bean.setUserNo(rs.getString("user_no"));
			bean.setUserName(rs.getString("user_name"));
		}

		return bean;
	}
}
