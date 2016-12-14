<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
                    <h1 class="page-header">進貨單: ${bopSerial}</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
           	<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-8"><h5>廠商: ${bopSupplier}</h5></div>
                                <div class="col-lg-3"><h5>總金額: ${bopTotalAmount}</h5></div>
                                <div class="col-lg-8"><h5>開單人: ${bopEmployee}</h5></div>
                                <div class="col-lg-3"><h5>開單日期: ${bopTime}</h5></div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>商品編號</th>
                                        <th>商品名稱</th>
                                        <th>價格</th>
                                        <th>數量</th>
                                        <th>金額</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${productList}" var="product">
	                                    <tr class="odd gradeA">
	                                        <td>${product.getProductID()}</td>
	                                        <td><font class="hideOverflow">${product.getProductName()}</font></td>
	                                        <td>$${product.getTotalPrice()}</td>
	                                        <td>${product.getPurchasingAmount()}</td>
	                                        <td>$${product.getTotalPrice()}</td>
	                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="modal-footer">
                                <form class="form-inline" action="updateBOP?id=${bopSerial}" method="POST">
                                    <div class="row">
                                        <div class="col-lg-8">
                                            <span class="pull-left">
                                                <label>備註: </label>
                                                <input class="form-control" name="bopRemark" value="${bopRemark}" placeholder="輸入備註" style="width: 260px">
                                                <label>付款狀態: </label>

                                                <c:choose>
                                                	<c:when test="${isHasPaid}">
		                                                <label class="radio-inline">
		                                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="true" checked>已付款
		                                                </label>
		                                                <label class="radio-inline">
		                                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="False">未付款
		                                                </label>
		                                            </c:when>
		                                            <c:otherwise>
		                                            	<label class="radio-inline">
		                                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="true">已付款
		                                                </label>
		                                                <label class="radio-inline">
		                                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="False" checked>未付款
		                                                </label>
		                                            </c:otherwise>
		                                        </c:choose>
                                                <button type="submit" class="btn btn-info">修改</button>
                                            </span>
                                        </div>
                                        <!-- <div class="col-lg-3">
                                            
                                        </div> -->
                                        <div class="col-lg-3 col-lg-offset-1">
                                            <a href="StockIn?id=${bopSerial}" class="btn btn-success">入庫</a>
                                            <a href="Order" class="btn btn-warning">關閉</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
