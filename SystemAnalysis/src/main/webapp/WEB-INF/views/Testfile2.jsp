<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    
	<title>Update Product</title>
</head>
<body>
	<%@include file="navbar.jspf" %>
	<div class="container theme-showcase" role="main">
    
      <div class="jumbotron" >    
        <h1>產品管理系統</h1>
        <p class="lead">本系統為輔仁大學資訊管理學系之範例程式</p>
      </div>
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form method="post" action="billofpurchaseupdate" id="updateForm">
				   <input type="hidden" name="id" value="${update.getBopSerial()}">
				    <label>產品編號</label>
					<label>${update.getBopSerial()}</label>
<!-- will need this part later
				  	<div class="form-group">
					    <label for="articleCategory.id">分類</label>
				  		<select class="form-control" id="articleCategory.id" name="articleCategory.id">
				  		<c:forEach items="${articleCategoryList}" var="articleCategory">
					  		<option value="${articleCategory.id}" ${article.articleCategory.id==articleCategory.id?'selected':''}>
					  			${articleCategory.name}
				  			</option>
				  		</c:forEach>
						</select>
				  	</div>
-->

					<div class="form-group">
						<label>員工編號(驗貨人員)</label>
						<label>${update.getEmployeeID()}</label>
						<p class="help-block">產品敘述</p>
					</div>
					<div class="form-group">
						<label>時間</label>
						<label>${update.getDateTime()}</label>
						<p class="help-block">目前庫存數量</p>
					</div>
					<div class="form-group">
						<label>到貨狀況</label>
						<label>${update.isHasArrived()}</label>
						<select class="form-control" name="arrived">
						<option value="true">true</option>
						<option value="false">false</option>
						</select>
					</div>
					<div class="form-group">
						<label>總金額</label>
						<label>${update.getTotalAmount()}</label>
					</div>
					<div class="form-group">
						<label>付款人員編號</label>
						<label>${update.getAccountantId()}</label>
					</div>
					<div class="form-group">
						<label>付款狀況</label>
						<label>${update.isHasPaid()}</label>
						<select class="form-control" name="paid">
						<option value="true">true</option>
						<option value="false">false</option>
						</select>
					</div>
					<div class="form-group">
						<label>驗貨狀況</label>
						<label>${update.isPassed()}</label>
						<select class="form-control" name="passed">
						<option value="true">true</option>
						<option value="false">false</option>
						</select>
					</div>
					<div class="form-group">
						<label>備註</label>
						<input type="text" name="remark" placeholder="0" value="${update.getRemarks()}" required>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	</div><!-- /.container -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/jquery.validate.js" />"></script>
    <script src="<c:url value="/resources/js/jsadditional-methods.js" />"></script>
    <script src="<c:url value="/resources/js/messages_zh_TW.js" />"></script>
	<script>
	$("#updateForm").validate();
	</script>
</body>
</html>