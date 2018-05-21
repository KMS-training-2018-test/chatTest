package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.GroupTalkBean;
import bean.UserTalkBean;

public class MainPageModel {

	static Connection conn = null;
	static String url = "jdbc:oracle:thin:@192.168.51.67:1521:XE";
	static String user = "SYSTEM";
	static String dbPassword = "kmskms";

	/**
	 * 会員一覧取得処理
	 *
	 * @param userNo
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getUserInfo(String userNo) {
		// 初期化
		LinkedHashMap<String, String> userInfo = new LinkedHashMap<String, String>();
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
			sb.append("SELECT ");
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");
			sb.append("WHERE ");
			sb.append(" user_no != '" + userNo + "' ");
			sb.append("ORDER BY ");
			sb.append(" user_no");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			// 取得データをmapにセット
			while (rs.next()) {
				userInfo.put(rs.getString("user_no"), rs.getString("user_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 会員が取得できなかった場合はNullを返す
		if (userInfo.size() == 0) {
			return null;
		}

		return userInfo;

	}

	/**
	 *
	 * 会話情報取得処理
	 *
	 * @param myUserNo
	 * @param targetUserNo
	 * @return
	 */
	public static UserTalkBean searchTalk(String myUserNo, String targetUserNo) {
		// 初期化
		UserTalkBean bean = new UserTalkBean();
		bean.setUserNo(targetUserNo);
		StringBuilder sb = new StringBuilder();

		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQL作成
			sb.append("SELECT ");
			sb.append(" message ");
			sb.append("FROM ");
			sb.append(" t_message_info ");
			sb.append("WHERE ");
			sb.append(" (send_user_no = " + myUserNo + " AND to_send_user_no = " + targetUserNo + ")");
			sb.append("OR ");
			sb.append(" (send_user_no = " + targetUserNo + " AND to_send_user_no = " + myUserNo + ")");
			sb.append("ORDER BY ");
			sb.append(" regist_date desc");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			// 取得データをmapにセット
			if (rs != null) {
				bean.setMessage(rs.getString("message"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bean;
	}

	/**
	 * グループ情報取得処理
	 *
	 * @param userNo
	 * @return
	 */
	public static List<GroupTalkBean> searchGroupInfo(String userNo) {
		// 初期化
		List<GroupTalkBean> groupInfoList = new ArrayList<GroupTalkBean>();
		StringBuilder sb = new StringBuilder();

		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQL作成
			sb.append("SELECT ");
			sb.append(" a.group_no ");
			sb.append(" ,a.group_name ");
			sb.append(" ,b.last_message");
			sb.append("FROM ");
			sb.append(" m_group a");
			sb.append("INNER JOIN ( ");
			sb.append(" SELECT message as last_message ");
			sb.append(" ,to_send_group_no ");
			sb.append(" ,MAX(regist_date) ");
			sb.append(" FROM ");
			sb.append(" t_message_info ");
			sb.append(" GROUP BY to_send_group_no ) b ");
			sb.append("ON ");
			sb.append(" a.group_no = b.to_send_group_no ");
			sb.append("INNER JOIN t_group_info c ");
			sb.append("ON b.to_send_group_no = c.group_no ");
			sb.append("WHERE ");
			sb.append(" c.user_no = " + userNo);
			sb.append("ORDER BY ");
			sb.append("a.group_no DESC");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			// 取得データをmapにセット
			while (rs.next()) {
				GroupTalkBean bean = new GroupTalkBean();
				bean.setGroupNo(rs.getString("a.group_no"));
				bean.setGroupName(rs.getString("a.group_name"));
				bean.setMessage(rs.getString("b.last_message"));

				groupInfoList.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return groupInfoList;
	}

}
