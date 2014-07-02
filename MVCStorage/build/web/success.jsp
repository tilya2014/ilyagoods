<%@ page language="java" contentType="text/html; charset= UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Insert title here</title>
    </head>
    <body>
        <table align="center" width="100%" border="1" cellspacing="0">
            <tr >
                
                    <h1>Товары на складе</h1>
                    <p> Всего на складе типов товаров: ${requestScope['count']}</p>
                
            </tr>
            <tr>
                <td align="left" width="256px">
                    <div> Группы товаров</div>
                </td>
                <td>
                    <div>Список товаров</div>
                </td>
                <td  width="256px">
                    <div></div>
                </td>
            </tr>

            <tr>
                <td>
                    <div></div>
                </td>
                <td>
                    <table width="100%">
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Image</th>
                        </tr>

                        <c:forEach var="item" items="${requestScope['items']}">
                            <tr>
                                <td><c:out value="${item.name}"/></td>
                                <td><c:out value="${item.description}"/></td>
                                <td align="center"><img src="images/<c:out value="${item.imgName}"/>"  width="128" height="128" alt="Нет изображения"></td>
                            </tr>
                        </c:forEach>

                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>