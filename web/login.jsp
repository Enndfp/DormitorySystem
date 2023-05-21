<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录界面</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap.css -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入 font-awesome.css -->
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- 引入 bootstrap.js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- 引入 jquery.js -->
    <script src="js/jquery.min.js"></script>
    <!-- 引入 style.css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="form row" style="height: 300px;">
        <form class="form-horizontal col-md-offset-3" id="login_form" action="/account?method=login" method="post">
            <h3 class="form-title">用户登录</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <span style="color: red;font-size: 13px;margin-left: -17px;">${usernameError}</span>
                    <input class="form-control required" required placeholder="请输入用户名" type="text"
                           name="username"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <span style="color: red;font-size: 13px;margin-left: -17px;">${passwordError}</span>
                    <input class="form-control required" required placeholder="请输入密码" type="password"
                           name="password"/>
                </div>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="type" checked value="systemAdmin" class="radio-inline"> 系统管理员
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="type" value="dormitoryAdmin" class="radio-inline"> 宿舍管理员
                    </label>
                </div>
                <div class="form-group col-md-offset-9">
                    <button type="submit" class="btn btn-success pull-left" name="submit">登录</button>
                    <button type="reset" class="btn btn-success pull-right" name="submit">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
