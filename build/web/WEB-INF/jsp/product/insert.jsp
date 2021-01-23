

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="<c:url value="resources/css/bootstrap.min.css"></c:url>" rel="stylesheet">
        <link href="<c:url value="resources/css/bootstrap.css"></c:url>" rel="stylesheet">
        <link href="<c:url value="resources/font-awesome/css/font-awesome.css"></c:url>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="resources/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css"/>
        <script src="<c:url value="resources/js/jquery.js"></c:url>" type="text/javascript"></script>
        <script src="<c:url value="resources/js/bootstrap.js"></c:url>" type="text/javascript"></script>
        <script src="<c:url value="resources/js/bootstrap.min.js"></c:url>" type="text/javascript"></script>
       
    </head>
    <body>
        <div class="container">
            <div class="row">
                ${message}
                
                <form:form action="product/insert.htm" modelAttribute="product" method="post"> 
                    <form role="form" class="col-md-6 go-right">
                        <h2>Thêm mới sản phẩm</h2>
                        <p>Hãy điền đầy đủ vào các text ở phía dưới</p>
                        
                        <div class="form-group">
                            <!--<textarea id="message" name="phone" class="form-control" required></textarea>-->
                            
                            <label for="message">Tên</label>
                            <form:input path="name" cssClass="form-control"/> 
                        </div>
                        
                        <div class="form-group">
                            <!--<input id="phone" name="phone" type="tel" class="form-control" required>-->
                            
                            <label for="phone">Giá</label>
                            <form:input path="price" cssClass="form-control"/> 
                        </div>           
                            
                        <div class="form-group">
                            <label for="phone">Số lượng</label>
                            <form:input path="amount" cssClass="form-control"/>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Hình</label>
                            <form:input type="file" path="proImg" cssClass="form-control"/>
                        </div>
                       
                        <div class="form-group">
                            <label for="phone">Danh Mục</label>
                            <!--<form:select path="categoryID" cssClass="form-control">
						 
                                <form:options items = "${category.getName()}" />
                            </form:select>-->
                            <form:select path="categoryID" name="categoryID" class="form-control">
                                <c:forEach var="x" items="${categoryInsert}">
                                    <form:option value="${x.categoryID}">${x.name}</form:option>>
                                    
                                </c:forEach>
                                
                            </form:select>>
                            
                        </div>
                        
                        <button class="btn btn-default">Thêm</button>
                    </form>
                </form:form>
                        
            </div>
        </div>

    </body>
</html>
