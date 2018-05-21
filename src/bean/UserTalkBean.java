package bean;

/**
 * 会話情報Bean
 *
 */
public class UserTalkBean {
	/** 会員番号 */
	private String userNo;

	/** 会員名 */
	private String userName;

	/** 最新会話 */
	private String message;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
