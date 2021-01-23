<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">




<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link rel="stylesheet" href="css/bootstrap.min.css">



        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style>
            .tt{
                color: #bd4147;
                font-size: 50px;
                font-family: monospace;
                font-style: oblique;
            }

            .tx{
                color: coral;
                font-size: 30px;
                font-family: monospace;
                font-style: oblique;
            }
        </style>

    </head>

    <body>

        <div class="container">
            <img src="images/lavender.jpg"
                 width="1115px;" height="300px;"

                 >

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
                        aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a class="navbar-brand" href="product/trangchu.htm">Trang Chủ</a>
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link" href="login.htm">Đăng nhập</a>
                        </li>                  

                        <li class="nav-item">
                            <a class="nav-link" href="cart/index.htm">Giỏ hàng</a>
                        </li>


                </div>
            </nav>

            <br><br>
            
                
                    <div class="row">
                        <c:forEach var="d" items="${products}">
                            <div class="col-sm-3 col-lg-4 mb-4">
                                <div class="card">
                                    <img width="250px" height="250px" class="card-img-top img-fluid" src="images/${d.proImg}">
                                    <div class="card-body">
                                        <h3 class="h3">${d.name}</h3>                                      
                                        <div class="text-danger mb-3">${d.price}
                                            <sup>đ</sup>
                                        </div>

                                        <p class="">
                                            <a href="addToCart.htm?productID=${d.productID}">
                                                <button type="button" class="btn btn-primary">Mua ngay</button>
                                            </a>
                                        </p>
                                    </div>

                                </div>  

                            </div>
                        </c:forEach>
                    </div>
                


                <br><br><br>
                <footer class="footer-bottom-area">
                    <center>
                        <span class="text-muted"> &copy; 2020 TieuLinh. All Rights Reserved.</span>
                    </center>
                </footer>
    </body>

</html>
