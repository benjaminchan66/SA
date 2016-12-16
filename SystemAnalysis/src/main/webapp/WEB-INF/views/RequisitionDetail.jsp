<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>通貨商進貨管理系統</title>

<!-- Bootstrap Core CSS -->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link href="resources/dist/css/style.css" rel="stylesheet"
	type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="wrapper">

		<%@include file="navbar.jspf"%>
		
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">請購單明細</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-8"><h5>廠商: ${prSupplier}</h5></div>
                                <div class="col-lg-3"><h5>廠商分級: ${prSupplierGrade}</h5></div>
                                <div><h5 class="col-lg-8">開單人: ${prEmployeeId}</h5></div>
                                <div><h5 class="col-lg-3">開單日期: ${prDate}</h5></div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form action="listRequisition">
                                <table width="100%" class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>名稱</th>
                                            <th>價格</th>
                                            <th>數量</th>
                                            <th>金額</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${prContent}" var="item">
                                            <tr>
                                                <td>${item.getProductID()}</td>
                                                <td>${item.getProductName()}</td>
                                                <td>$ ${item.getProductPrice()}</td>
                                                <td>${item.getPurchasingAmount()}</td>
                                                <td>$ ${item.getTotalPrice()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="col-lg-12">
                                	<span class="pull-right"><h4>總計: $ ${prListAmount}</h4></span>
                                </div>
                                
                            </form>
                            <div class="col-lg-4 col-lg-offset-8">
                            	<span class="pull-right">
                                    <sec:authorize access ="hasRole('ROLE_USER_god') or hasRole('ROLE_USER_director')">
		                            	<a type="button" href="confirmRequisition?id=${prSerial}" class="btn btn-info">請購單確認</a>
		                            	<a href="cancelRequisition?id=${prSerial}" class="btn btn-danger">退回請購單</a>
                                    </sec:authorize>
	                            	<a href="listRequisition" class="btn btn-warning">關閉</a>
                            	</span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

	</div>

	<!-- jQuery -->
	<script src="resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="resources/vendor/raphael/raphael.min.js"></script>
	<script src="resources/vendor/morrisjs/morris.min.js"></script>
	<script src="resources/data/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/dist/js/sb-admin-2.js"></script>
</body>
</html>
