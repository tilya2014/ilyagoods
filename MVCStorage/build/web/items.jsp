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
    <h1 align = center>Товары на складе</h1>
    <table align="left">
        <tr>
            <td>
                <form action="Servlet">
                    <strong>Категория:</strong>
                    <select name="category_id">
                        <c:forEach var="category" items="${requestScope['categories']}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                    <input type='hidden' name='action' value='showItems'/>
                    <input type="submit" value="Просмотреть" />
                </form>
            </td>
        </tr>
    </table>

    <table width="768px" align="center" border="1 solid" cellpadding="10px" cellspacing="0">
        <tr>
            <th>Наименование</th>
            <th>Описание</th>
            <th>Изображение</th>
            <th>Количество</th>
            <th>Действие</th>
        </tr>

        <c:forEach var="item" items="${requestScope['items']}">
            <tr>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.description}"/></td>
                <td><img src="images/<c:out value="${item.imgName}"/>"  width="128" height="128" alt="Нет изображения"></td>
                <td>
                    <form action="Servlet" method="get">
                        <input type="text" name="count" value="<c:out value="${item.count}"/>"> 
                        <input type='hidden' name='action' value='setCount'/>
                        <input type="hidden" name="item_id" value="<c:out value="${item.id}"/>"> 
                        <input type="hidden" name="category_id" value="<c:out value="${item.categoryID}"/>"> 
                        <input type="submit" value="Установить" />
                    </form>

                </td>
                <td>
                    <div><a href='Servlet?item_id=${item.id}&action=showItem'>Подробно</a></div>
                    <div><a href='Servlet?item_id=${item.id}&action=deleteItem'>Удалить</a></div>

                </td>

            </tr>
        </c:forEach>


    </body>
</html>