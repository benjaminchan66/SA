<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                    <h1 class="page-header">商品清單</h1>
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
                                    <h4>所有商品</h4>
                                </div>
                                <div class="col-lg-2 col-lg-offset-6">
                                    <button class="btn btn-default" data-toggle="modal" data-target="#myModal">新增商品</button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-search">
                                <thead>
                                    <tr>
                                        <th style="width: 25px">id</th>
                                        <th>名稱</th>
                                        <th>價格</th>
                                        <th>廠商</th>
                                        <th>庫存</th>
                                        <th>狀態</th>
                                        <th>動作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${productList}" var="product">
                                        <tr class="odd gradeA">
                                            <td>${product.getProductID()}</td>
                                            <td><p class="hideOverflow">${product.getProductName()}</p></td>
                                            <td>${product.getPrice()}</td>
                                            <td>${product.getSupplierID()}</td>
                                            <td>${product.getInventory()}</td>
                                            <td>${product.isInTheMarket()}</td>
                                            <td>
                                                <a class="btn btn-warning" href="updateProduct?id=${product.getProductID()}">修改</a>
                                            </td>
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
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">新增商品</h4>
                        </div>
                        <form role="form" action="productList">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>商品名稱</label>
                                    <input class="form-control" placeholder="輸入商品名稱" name="productName" required="required">
                                </div>
                                <div class="form-group">
                                    <label>商品價格</label>
                                    <input class="form-control" placeholder="輸入商品價格" name="productPrice" required="required">
                                </div>
                                <div class="form-group">
                                    <label>選擇廠商</label>
                                    <select class="form-control" name="productSupplier">
                                        <c:forEach items="${supplierList}" var="supplier">
                                            <option value="supplierList.id">supplierList.name</option>
                                        </c:forEach>
                                    </select>
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

            <!-- Modal -->
            <div class="modal fade" id="myModalChange" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">新增商品</h4>
                        </div>
                        <form role="form" action="productList">
                            <div class="modal-body">
                                    <div class="form-group">
                                        <label>商品名稱</label>
                                        <input class="form-control" placeholder="輸入商品名稱" value="Apple Lightning 8pin 原廠USB傳輸線" required="required">
                                    </div>
                                    <div class="form-group">
                                        <label>商品價格</label>
                                        <input class="form-control" placeholder="輸入商品價格" value="349" required="required">
                                    </div>
                                    <div class="form-group">
                                        <label>選擇廠商</label>
                                        <select class="form-control">
                                            <option value="1">Apple</option>
                                            <option value="2">Sony</option>
                                            <option value="3" selected="selected">HTC</option>
                                            <option value="4">ADATA</option>
                                            <option value="5">ONPRO</option>
                                        </select>
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
