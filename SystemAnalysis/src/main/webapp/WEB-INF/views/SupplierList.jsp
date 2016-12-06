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
                            <div class="col-lg-2">
                                <h4>所有廠商</h4>
                            </div>
                            <div class="col-lg-8"></div>
                            <button class="btn btn-default" data-toggle="modal" data-target="#myModal" style="margin-left: 70px">新增廠商</button>
                        </div>
                        
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">新增廠商</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form role="form">
                                            <div class="form-group">
                                                <label>廠商名稱</label>
                                                <input class="form-control" placeholder="輸入廠商名稱">
                                            </div>
                                            <div class="form-group">
                                                <label>聯絡電話</label>
                                                <input class="form-control" placeholder="輸入聯絡電話">
                                            </div>
                                            <div class="form-group">
                                                <label>廠商地址</label>
                                                <input class="form-control" placeholder="輸入廠商地址">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>
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
                                        <th style="width: 104px">動作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="odd gradeA">
                                        <td>001</td>
                                        <td><p>Apple</p></td>
                                        <td>02 8772 3353</td>
                                        <td>105台北市松山區復興南路一段39號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>002</td>
                                        <td><p>ADATA</p></td>
                                        <td>02 8780 1000</td>
                                        <td class="center">236新北市土城區金城路三段244號</td>
                                        <td>B</td>
                                        <td>
                                            <button type="button" class="btn btn-info">升級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>003</td>
                                        <td><p>ONPRO</p></td>
                                        <td>02 7729 1310</td>
                                        <td class="center">238樹林區保安二街5號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>004</td>
                                        <td><p>HTC</td>
                                        <td>02 2723 1757</td></td>
                                        <td class="center">408台中市南屯區文心南三路230巷17號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>005</td>
                                        <td><p>SONY</p></td>
                                        <td>02 8780 5566</td>
                                        <td class="center">408台中市南屯區大業路188號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>006</td>
                                        <td><p>FLIR</p></td>
                                        <td>02 8780 8111</td>
                                        <td class="center">701台南市東區勝利路20-11號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>007</td>
                                        <td><p>GoPro</p></td>
                                        <td>02 8789 3388</td>
                                        <td class="center">709台南市安南區頂安街379巷1號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>008</td>
                                        <td><p>Twelve South</p></td>
                                        <td>02 8789 5911</td>
                                        <td class="center">Google</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>009</td>
                                        <td><p>Google</td>
                                        <td>02 8729 6000</td></td>
                                        <td class="center">110台北市信義區信義路五段7號台北101大樓</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>010</td>
                                        <td><p>統亞電子科技股份有限公司</p></td>
                                        <td>02 2758 8008</td>
                                        <td class="center">702安南區頂安街406號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>011</td>
                                        <td><p>TTTTTT</td>
                                        <td>02 2753 5968</td></td>
                                        <td class="center">702安南區海佃路二段531號</td>
                                        <td>A</td>
                                        <td>
                                            <button type="button" class="btn btn-danger">降級</button>
                                            <button type="button" class="btn btn-warning">修改</button>
                                        </td>
                                    </tr>
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
