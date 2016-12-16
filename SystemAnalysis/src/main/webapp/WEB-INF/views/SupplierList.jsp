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

<!-- DataTables CSS -->
<link href="resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

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
                    <h1 class="page-header">廠商清單</h1>
                </div>
                
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-4">
                                    <h4>所有廠商</h4>
                                </div>
                                <div class="col-lg-2 col-lg-offset-6">
                                	<sec:authorize access ="hasRole('ROLE_USER_god') or hasRole('ROLE_USER_procurement') or hasRole('ROLE_USER_director')">
                                    	<button class="btn btn-default" data-toggle="modal" data-target="#myModal">新增廠商</button>
                                    </sec:authorize>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form role="form" action="newSupplier" method="POST">
	                                    <div class="modal-header">
	                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                                        <h4 class="modal-title" id="myModalLabel">新增廠商</h4>
	                                    </div>
	                                    <div class="modal-body">
	                                    	<div class="form-group">
	                                           	<label>廠商名稱</label>
	                                            <input class="form-control" placeholder="輸入廠商名稱" name="supplierName">
	                                        </div>
	                                        <div class="form-group">
	                                            <label>聯絡電話</label>
	                                            <input class="form-control" placeholder="輸入聯絡電話" name="supplierPhone">
	                                        </div>
	                                        <div class="form-group">
	                                            <label>廠商地址</label>
	                                            <input class="form-control" placeholder="輸入廠商地址" name="supplierAddress">
	                                        </div>
	                                    </div>
	                                    <div class="modal-footer">
	                                        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
	                                        <button type="submit" class="btn btn-primary">Save</button>
	                                    </div>
                                    </form>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-search">
                                <thead>
                                    <tr>
                                        <th style="width: 30px">id</th>
                                        <th>名稱</th>
                                        <th style="width: 67px">聯絡電話</th>
                                        <th>廠商地址</th>
                                        <th style="width: 38px">分級</th>
                                        <sec:authorize access ="hasRole('ROLE_USER_god') or hasRole('ROLE_USER_procurement') or hasRole('ROLE_USER_director')">
                                        	<th style="width: 104px">動作</th>
                                        </sec:authorize>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${supplierList}" var="supplier">
                                        <tr class="odd gradeA">
                                            <td>${supplier.getSupplierID()}</td>
                                            <td><p>${supplier.getSupplierName()}</p></td>
                                            <td>${supplier.getPhone()}</td>
                                            <td>${supplier.getAddress()}</td>
                                            <td>${supplier.getLevel()}</td>
                                            <sec:authorize access ="hasRole('ROLE_USER_god') or hasRole('ROLE_USER_procurement') or hasRole('ROLE_USER_director')">
	                                            <td>
	                                                <a class="btn btn-warning" href="updateSupplier?id=${supplier.getSupplierID()}">修改</a>
	                                            </td>
	                                        </sec:authorize>
                                        </tr>
									</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
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
	
    <!-- DataTables JavaScript -->
    <script src="resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="resources/vendor/datatables-responsive/dataTables.responsive.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/dist/js/sb-admin-2.js"></script>

	<script>
    $(document).ready(function() {
        $('#dataTables-search').DataTable({
            responsive: true
        });
    });
    </script>
</body>
</html>
