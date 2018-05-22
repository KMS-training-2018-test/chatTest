package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.UserTalkBean;

public class DirectMessageModel {

	static Connection conn = null;
	static String url = "jdbc:oracle:thin:@192.168.51.67:1521:XE";
	static String user = "SYSTEM";
	static String dbPassword = "kmskms";

	/**
	 * 会話情報取得処理
	 *
	 * @param myUserNo
	 * @param targetUserNo
	 * @return
	 */
	public static List<UserTalkBean> searchTalkInfo(String myUserNo, String targetUserNo) {
		// 初期化
		List<UserTalkBean> talkList = new ArrayList<UserTalkBean>();
		StringBuilder sb = new StringBuilder();

		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQL作成


			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			// Mapにセット
			while(rs.next()) {
				UserTalkBean bean = new UserTalkBean();
				bean.setUserNo(rs.getString("user_no"));
				bean.setUserName(rs.getString("user_name"));
				bean.setMessage(rs.getString("message"));

				talkList.add(bean);
			}

			return talkList;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return talkList;
	}
}
