<%-- 
    Document   : index
    Created on : Oct 23, 2020, 12:02:20 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/can_chinh.css">
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        
       
            <center>
            <h1>GIỎ HÀNG</h1>
            <table border="1" class="table">
                <thead>
                    <tr>
                        <th scope="col">MÃ SẢN PHẨM</th>
                        <th scope="col">TÊN SẢN PHẨM</th>
                        <th scope="col">HÌNH</th>
                        <th scope="col">ĐƠN GIÁ</th>
                        <th scope="col" colspan="3">SỐ LƯỢNG</th>
                        <th scope="col">THÀNH TIỀN</th>
                        <th scope="col">ACTION</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="i" items="${viewCart}">
                        <tr>
                            <td>${i.product.productID}</td>
                            <td>${i.product.name}</td>
                            <td>
                                <img src="images/${i.product.proImg}" width="60px" height="60px" />
                            </td>
                            <td>${i.product.price}</td>
                            <td><a href="cart/giam.htm?productID=${i.product.productID}">Giam</a></td>
                            <td>${i.soluong}</td>
                            <td><a href="cart/tang.htm?productID=${i.product.productID}">Tang</a></td>
                            <td>${i.soluong* i.product.price }</td>
                            <td><a href="cart/delete.htm?productID=${i.product.productID}">Delete</a></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </center>
            <br>

            

                <center>
                    <p class="btn btn-success">Tổng tiền: </p><p style="font-size: medium; font-weight: bold;"> <strong>${tongtien}</strong> </p>
            <br>
                <button type="button" class="btn btn-info">
                    <a class="mua" href="product/trangchu.htm">Mua tiếp</a>
                </button>

                <button type="button" class="btn btn-danger">
                    <a class="mua" href="cart/deleteAll.htm">Xóa tất cả sản phẩm</a>
                </button>

                    <a class="btn btn-warning" href="cart/thanhtoan.htm">Thanh toán</a>
                <center>
    </body>
</html>
