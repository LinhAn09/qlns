

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/can_chinh.css">
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>

        <base href="${pageContext.servletContext.contextPath}/">
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap.css" rel="stylesheet">
        <link href="resources/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <style>
            .panel-table .panel-body{
                padding:0;
            }

            .panel-table .panel-body .table-bordered{
                border-style: none;
                margin:0;
            }

            .panel-table .panel-body .table-bordered > thead > tr > th:first-of-type {
                text-align:center;
                width: 100px;
            }

            .panel-table .panel-body .table-bordered > thead > tr > th:last-of-type,
            .panel-table .panel-body .table-bordered > tbody > tr > td:last-of-type {
                border-right: 0px;
            }

            .panel-table .panel-body .table-bordered > thead > tr > th:first-of-type,
            .panel-table .panel-body .table-bordered > tbody > tr > td:first-of-type {
                border-left: 0px;
            }

            .panel-table .panel-body .table-bordered > tbody > tr:first-of-type > td{
                border-bottom: 0px;
            }

            .panel-table .panel-body .table-bordered > thead > tr:first-of-type > th{
                border-top: 0px;
            }

            .panel-table .panel-footer .pagination{
                margin:0; 
            }

            /*
            used to vertically center elements, may need modification if you're not using default sizes.
            */
            .panel-table .panel-footer .col{
                line-height: 34px;
                height: 34px;
            }

            .panel-table .panel-heading .col h3{
                line-height: 30px;
                height: 30px;
            }

            .panel-table .panel-body .table-bordered > tbody > tr > td{
                line-height: 34px;
            }
            
            .he{
                height: 50px;
                background-color: #ce8483;
            }
        </style>
    </head>
    <body>
        ${a}
        <div class="container">
            
            <div class="row">
                <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ffe8a1">
                
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">

                    <div class="panel-heading">
                            <div class="row">

                                <div class="col col-xs-2 ">
                                    <a href="product/trangchu.htm" class="">Trang chủ</a>
                                </div>
                                
                                <div class="col col-xs-2 ">
                                    <a href="user/index.htm?page=1" class="">Quản lý tài khoản</a>
                                </div>
                                
                                <div class="col col-xs-2 ">
                                    <a href="product/index.htm?page=1" class="">Quản lý sản phẩm</a>
                                </div>
                                
                                <div class="col col-xs-2 ">
                                    <a href="category/index.htm?page=1" class="">Quản lý danh mục</a>
                                </div>
                            </div>
                    </div>

                </div>
            </nav>
                
                <div class="col-md-10 col-md-offset-1">

                    <div class="panel panel-default panel-table">
                        <div class="panel-heading">
                            <div class="row">

                                <div class="col col-xs-6 ">
                                    <a href="user/insert.htm" class="btn btn-sm btn-primary btn-create">Thêm mới</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-list">
                                <thead>
                                    <tr>
                                        <th align="center">Delete</th>
                                        <th align="center">Update</th>
                                        <th style="text-align: center">Username</th>
                                        <th style="text-align: center">Password</th>
                                    </tr> 
                                </thead>
                                <tbody>
                                    <c:forEach var="u" items="${users}">
                                        <tr>
                                            <td align="center">
                                                <a class="btn btn-danger" href="user/delete.htm?username=${u.username}"><em class="fa fa-trash"></em></a>
                                            </td>
                                            
                                            <td align="center">
                                                <a class="btn btn-info" href="user/update.htm?username=${u.username}"><em class="fa fa-refresh"></em></a>
                                            </td>
                                            
                                            <td class="hidden-xs" align="center">${u.username}</td>
                                           
                                            <td align="center">${u.password}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col col-xs-4">Trang ${pages} của ${totalpage}
                                </div>
                                <div class="col col-xs-8">
                                    <ul class="pagination hidden-xs pull-right">

                                        <c:set var="totalpage" value="${totalpage}"></c:set>
                                        <c:set var="pages" value="${pages}"></c:set>
                                        <%
                                            String totalpage = String.valueOf(pageContext.getAttribute("totalpage"));
                                            String pages = String.valueOf(pageContext.getAttribute("pages"));
                                            int sotrang = Integer.parseInt(totalpage);
                                            int trang = Integer.parseInt(pages);
                                        %>
                                        <%
                                            if (trang > 1) {
                                        %>
                                        <li><a href="user/index.htm?page=<%=(trang - 1)%>">«</a></li>
                                            <%
                                                }
                                            %>
                                            <%
                                                for (int i = 1; i <= sotrang; i++) {
                                            %>
                                        <li <%if (trang == i) {%>class="active"<%}%>><a href="user/index.htm?page=<%=i%>"><%=i%></a></li>
                                            <%
                                                }
                                            %>
                                            <%
                                                if (trang != sotrang) {
                                            %>
                                        <li><a href="user/index.htm?page=<%=trang + 1%>">»</a></li>
                                            <%
                                                }
                                            %>

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <br><br><br><br><br><br>

        <footer class="footer">
            <center>
                <span class="text-muted"> Copyright &copy; HoaHoa, Ho Chi Minh, Viet Nam. All rights reserved.</span>
            </center>
        </footer>
    </body>
</html>
