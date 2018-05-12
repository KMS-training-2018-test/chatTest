package bean;

import java.util.LinkedHashMap;

public class MakeGroupBean extends SessionBean {
	/** 会員リスト */
	private LinkedHashMap<String, String> memberList;

	/** 選択会員 */
	private String[] chosedMember;

	/** グループ名 */
	private String groupName;

	public LinkedHashMap<String, String> getMemberList() {
		return memberList;
	}

	public void setMemberList(LinkedHashMap<String, String> memberList) {
		this.memberList = memberList;
	}

	public String[] getChosedMember() {
		return chosedMember;
	}

	public void setChosedMember(String[] chosedMember) {
		this.chosedMember = chosedMember;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
