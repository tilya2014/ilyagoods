<%@ page language="java" contentType="text/html; charset= UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--link rel="stylesheet" type="text/css" href="style.css"--%>
        <title>Insert title here</title>
    </head>
    <body>
    <link rel="section" type="text/html" href="login.jsp">
    <iframe src="login.jsp"  frameborder="0"> </iframe>
    <h1 align = center>Описание товара</h1>
    <form action="Servlet" method="get">
        <table width="768px" align="center" border="1 solid" cellpadding="10px" cellspacing="0">
            <tr>
                <th width="128px">Наименование</th>
                <td><input type="text" name="item_name" value="<c:out value="${requestScope['item'].name}"/>"> </td>
            </tr>
            <tr>
                <th>Описание</th>
                
                <td><input type="text" name="item_description" value="<c:out value="${requestScope['item'].description}"/>"> </td>
            </tr>
            <tr>
                <th>Изображение</th>
                <td><img src="images/<c:out value="${requestScope['item'].imgName}"/>"  width="256" height="256" alt="Нет изображения"></td>
            </tr>
            <tr>
                <th>Количество</th>
                <td><input type="text" name="count" value="<c:out value="${requestScope['item'].count}"/>"> </td>
            </tr>
            <tr>
                <th>Действие</th>
                <td>
                    <input type='hidden' name='action' value='updateItem'/>
                    <input type='hidden' name='item_image' value='${requestScope['item'].imgName}'/>
                    <input type='hidden' name='category_id' value='${requestScope['item'].categoryID}'/>
                    <input type="hidden" name="item_id" value="<c:out value="${requestScope['item'].id}"/>"> 
                    <input type="submit" value="Сохранить" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>