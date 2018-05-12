package model;

import java.util.LinkedHashMap;

import bean.MakeGroupBean;
import dao.DataAccess;

public class MakeGroupModel {

	public MakeGroupBean searchMemberList(MakeGroupBean bean) throws Exception {
		// 初期化
		DataAccess dataAccess = new DataAccess();
		StringBuilder sb = new StringBuilder();
		LinkedHashMap<String, String> memberList = new LinkedHashMap<String, String>();

		// SQL作成
		sb.append("SELECT ");
		sb.append(" user_no ");
		sb.append(" ,user_name ");
		sb.append("FROM ");
		sb.append(" m_user ");
		sb.append("WHERE ");
		sb.append(" user_no != " + bean.getUserNo());

		// SQL実行
		//ResultSet rs = dataAccess.selectData(sb.toString());

		// 取得データをリストにセット
		//while(rs.next()) {
		//	memberList.put(rs.getString("user_no"), rs.getString("user_name"));
		//}

		memberList.put("1", "みやざき");
		memberList.put("2", "みやざき2");

		bean.setMemberList(memberList);

		return bean;
	}

}
