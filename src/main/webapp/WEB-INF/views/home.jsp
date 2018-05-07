
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>Home Page</h1>

<div class="collapse navbar-collapse" id="navbar-collapse">
    <ul class="nav navbar-nav navbar-left">
        <li><a data-track="" data-track-location="header" href="/product/">Product</a></li>
        <li><a data-track="" data-track-location="header" href="/customers/">Customers</a></li>
        <li><a data-track="" data-track-location="header" href="/community/">Community</a></li>
        <li><a data-track="" data-track-location="header" href="/pricing/">Pricing</a></li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/help/">Knowledge Base</a></li>
                <li><a href="https://discuss.redash.io/">Forum</a></li>
                <li><a href="http://slack.redash.io/">Slack</a></li>
            </ul>
        </li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
        <li><a data-track="" data-track-location="header" href="https://app.redash.io/">Login</a></li>
        <li><a data-track="" data-track-location="header" href="https://app.redash.io/">Logout</a></li>
    </ul>
</div>

<a href="secret">Secret Page</a>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
