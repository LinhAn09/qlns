

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                
                <form:form action="category/update.htm" modelAttribute="category" method="post"> 
                    <form role="form" class="col-md-6 go-right">
                        <h2>Cập nhật danh mục</h2>
                        <p>Hãy điền đầy đủ vào các text ở phía dưới</p>
                        
                        <div class="form-group">
                            <!--<input id="name" name="name" type="text" class="form-control" required>-->
                            <label for="name">ID</label>
                            <form:input path="categoryID" readonly="true" cssClass="form-control"/> 
                            
                        </div>
                        
                        <div class="form-group">
                            <!--<input id="phone" name="phone" type="tel" class="form-control" required>-->
                            
                            <label for="phone">Tên</label>
                            <form:input path="name" cssClass="form-control"/> 
                        </div>                       
                        <button class="btn btn-default">Update</button>
                    </form>
                </form:form>
                        
            </div>
        </div>

    </body>
</html>
