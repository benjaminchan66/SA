<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>通貨商進貨管理系統</title>

    <!-- Bootstrap Core CSS -->

</head>

<body>

    <div id="wrapper">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">商品清單</h1>
                </div>
                
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <!-- /.row -->
            <div class="row">
                
                    
                <div class="modal-body">
                    <form role="form" method="POST">
                        <div class="form-group">
                            <label>商品名稱</label>
                            <input class="form-control" placeholder="輸入商品名稱" name="productName">
                        </div>
                        <div class="form-group">
                            <label>商品價格</label>
                            <input class="form-control" placeholder="輸入商品價格" name="price">
                        </div>
                        <div class="form-group">
                            <label>選擇廠商</label>
                            <select class="form-control" name="supplierID">
                                <option value="1" selected="selected">Apple</option>
                                <option value="2">Sony</option>
                                <option value="3">HTC</option>
                                <option value="4">ADATA</option>
                                <option value="5">ONPRO</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
                

                <tbody>
                    <tr>
                        <td>001</td>
                        <td><p>Apple Lightning 8pin 原廠USB傳輸線</p></td>
                        <td>$349</td>
                        <td>Apple</td>
                        <td>89</td>
                        <td>供貨中</td>
                        <td>
                            <button type="button" class="btn btn-danger">斷貨</button>
                            <button type="button" class="btn btn-warning">修改</button>
                        </td>
                    </tr>
                </tbody>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    
</body>

</html>
