<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>チャット研修プログラム</h1>
<h2>メインメニュー</h2>
<P>${session.userName}さん</P>
<table>
<c:forEach varStatus="loop" var="memberBean" items="${userList}">
	<tr>
		<td><a href="./talk?target=${memberBean.memberNo}">${memberBean.userName}</a></td>
		<td>${memberBean.message}</td>
	</tr>
</c:forEach>
</table>
<p>
<table>
<c:forEach varStatus="loop" var="groupBean" items="${groupList}">
	<tr>
		<td><a href="./groupTalk?target=${groupBean.groupNo}">${memberBean.groupName}</a></td>
		<td>${groupBean.message}</td>
	</tr>
</c:forEach>
</table>
<p>
<form action="/chatTest/makeGroup" method="POST">
	<input type="submit" value="グループ作成画面へ">
</form>
<p>
<form action="/chatTest/editProfile" method="POST">
	<input type="submit" value="プロフィール画面へ">
</form>
</body>
</html>