package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL接続用クラス
 */
public class DataAccess {
	Connection conn = null;
	String url = "jdbc:oracle:thin:@192.168.51.67:1521:XE";
	String user = "SYSTEM";
	String password = "kmskms";

	/**
	 * select文用メソッド
	 *
	 * @param sql
	 * @return
	 */
	public ResultSet selectData(String sql) throws Exception {
		try {
			// JDBCドライバーのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 接続作成
			conn = DriverManager.getConnection(url, user, password);
			// SQL実行
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * update関係用メソッド
	 *
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public void updateData(String sql) throws Exception {
		try {
			// JDBCドライバーのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 接続作成
			conn = DriverManager.getConnection(url, user, password);
			// SQL実行
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
