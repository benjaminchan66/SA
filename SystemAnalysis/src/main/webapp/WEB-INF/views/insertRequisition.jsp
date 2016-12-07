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
                    <h1 class="page-header">開立請購單</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <form class="form-inline" action="" method="POST">
                            
                                <div class="col-lg-3 form-group">
                                    <h4>請購商品</h4>
                                </div>
                                
                                <div class="col-lg-7">
                                    <label class="h4" style="margin-right: 5px">廠商:</label>
                                    <select class="form-control">
                                        <option value="1">Apple</option>
                                        <option value="2">Sony</option>
                                        <option value="3">HTC</option>
                                        <option value="4">ADATA</option>
                                        <option value="5">ONPRO</option>
                                    </select>
                                    <button type="submit" class="btn btn-default btn-hide" id="btn-view">選擇</button>
                                </div>
                                
                                
                                
                            </form>
                            <a href="previewDetailRequisition"><button class="btn btn-primary">明細預覽</button></a>
                        </div>
                        

                        <div class="panel-body">
                            <!-- <form action="RequisitionDetails.html"> -->
                                <table width="100%" class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>名稱</th>
                                            <th>價格</th>
                                            <th>動作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Apple Lightning 8pin 原廠USB傳輸線</td>
                                            <td>$349</td>
                                            <td><button class="btn btn-default">訂購</button></td>
                                        </tr>
                                        <tr>
                                            <td>Apple TV 64G</td>
                                            <td>$6790</td>
                                            <td><button class="btn btn-default">訂購</button></td>
                                        </tr>
                                        <tr>
                                            <td>Apple iPhone 5 原廠耳機 帶線控麥克風耳機 盒裝</td>
                                            <td>$499</td>
                                            <td><button class="btn btn-default">訂購</button></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="col-lg-11"></div>
                                
                            <!-- </form> -->
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
