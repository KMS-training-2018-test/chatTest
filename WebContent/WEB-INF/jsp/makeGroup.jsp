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
<h2>グループ作成</h2>
<form action="/chatTest/makeGroup" method="POST">
<p>${bean.userName}さん</p>
<c:forEach varStatus="loop" var="list" items="${bean.memberList}">
	<input type="checkbox" name="memberList" value="${list.key}" >
	<label>${list.value}</label>
</c:forEach>
<p>グループ名</p>
<input type="text" name="groupName" value="" >
<input type="submit" value="送信">
</form>
</body>
</html>