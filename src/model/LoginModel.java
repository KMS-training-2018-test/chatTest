package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.LoginBean;

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
		StringBuilder sb = new StringBuilder();
		String userId = bean.getUserId();
		String password = bean.getPassword();

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67:1521:XE";
		String user = "SYSTEM";
		String dbPassword = "kmskms";
		// JDBCドライバーのロード
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 接続作成
		conn = DriverManager.getConnection(url, user, dbPassword);

		// SQL作成
		sb.append("SELECT ");
		sb.append(" user_no ");
		sb.append(" ,user_name ");
		sb.append("FROM ");
		sb.append(" m_user ");
		sb.append("WHERE ");
		sb.append(" user_id = '" + userId + "' " );
		sb.append(" AND password = '" + password + "'");

		// SQL実行
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sb.toString());

//		ResultSet rs = dataAccess.selectData(sb.toString());
		if (!rs.next()) {
			bean.setErrorMessage("パスワードが一致しませんでした。");
		} else {
			bean.setUserNo(rs.getString("user_no"));
			bean.setUserName(rs.getString("user_name"));
			conn.close();
		}

		return bean;
	}
}
