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
                    <h1 class="page-header"><i class="fa fa-building fa-fw"></i>廠商</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3">
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-file-text-o fa-fw"></i>廠商資料
                        </div>
                        <div>
                            <!-- <center> -->
                                <form role="form" action="updateSupplier" method="POST">
                                	<input type="hidden" name="supplierID" value="${supplierID}">
                                    <div class="modal-body">
                                            <div class="form-group">
                                                <label>廠商名稱</label>
                                                <input class="form-control" name="supplierName" value="${supplierName}" placeholder="輸入廠商名稱" required="required">
                                            </div>
                                            <div class="form-group">
                                                <label>聯絡電話</label>
                                                <input class="form-control" name="supplierPhone" value="${supplierPhone}" placeholder="輸入聯絡電話 - 0288444222" required="required">
                                            </div>
                                            <div class="form-group">
                                                <label>廠商地址</label>
                                                <input class="form-control" name="supplierAddress" value="${supplierAddress}" placeholder="輸入廠商地址" required="required">
                                            </div>
                                            <div class="form-group">
                                           	<sec:authorize access ="hasRole('ROLE_USER_god') or hasRole('ROLE_USER_procurement') or hasRole('ROLE_USER_director')">
                                                <c:choose>
                                                	<c:when test="${isClassA}">
    	                                                <label>分級: </label>
    	                                                <label class="radio-inline">
    	                                                    <input type="radio" name="supplierLevel" id="optionsRadiosInline1" value="A" checked required>A
    	                                                </label>
    	                                                <label class="radio-inline">
    	                                                    <input type="radio" name="supplierLevel" id="optionsRadiosInline2" value="B">B
    	                                                </label>
    	                                            </c:when>
    	                                            <c:otherwise>
    	                                            	<label>分級: </label>
    	                                                <label class="radio-inline">
    	                                                    <input type="radio" name="supplierLevel" id="optionsRadiosInline1" value="A" required>A
    	                                                </label>
    	                                                <label class="radio-inline">
    	                                                    <input type="radio" name="supplierLevel" id="optionsRadiosInline2" value="B" checked>B
    	                                                </label>
    	                                            </c:otherwise>
                                                </c:choose>
                                            </sec:authorize>
                                            </div>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="SupplierList" class="btn btn-warning">返回</a>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>
                                </form>
                            <!-- </center> -->
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
