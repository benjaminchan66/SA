<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="row">
			<br>
			<div class="col-md-12">
	  			<a class="btn btn-primary" href="insertProduct">新增</a>
				<table class="table">
				  	<tr>
				  		<th>進貨單編號</th>
				  		<th>員工編號(驗貨人員)</th>
				  		<th>時間</th>
				  		<th>到貨狀況</th>
				  		<th>總金額</th>
				  		<th>付款人員編號</th>
				  		<th>付款狀況</th>
				  		<th>驗貨狀況</th>
				  		<th>備註</th>
				  	</tr>
				    
					  	<tr>
			
					  		<td>${billofpurchasedetail.getBopSerial()}</td>
					  		<td>${billofpurchasedetail.getEmployeeID()}</td>
					  		<td>${billofpurchasedetail.getDateTime()}</td>
					  		<td>${billofpurchasedetail.isHasArrived()}</td>
					  		<td>${billofpurchasedetail.getTotalAmount() }</td>
					  		<td>${billofpurchasedetail.getAccountantId()}</td>
					  		<td>${billofpurchasedetail.isHasPaid()}</td>
					  		<td>${billofpurchasedetail.isPassed()}</td>
					  		<td>${billofpurchasedetail.getRemarks()}</td>
					  		<td><a class="btn btn-default"  href="billofpurchaseupdate?id=${billofpurchasedetail.getBopSerial()}">更新</a></td>
					  		
					  	</tr>
				  	
				</table>
				<a href="bopList">Back</a>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">刪除</h4>
                </div>
                <div class="modal-body">
                    <p>確認刪除後，相關之訊息也將刪除</p>
                </div>
                <div class="modal-footer">
	                <form id="deleteForm" action="deleteProduct" method="post">
	            		<input type="hidden" name="id" id="deleteID">
	                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                    <button type="submit" class="btn btn-danger">確認刪除</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>