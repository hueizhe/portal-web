
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


<nav class="navbar navbar-fixed-top navbar-has-shadow">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <img src="" class="navbar-brand__img" alt="">
            </a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li><a data-track="" data-track-location="header" href="/product/">Product</a></li>
                <li><a data-track="" data-track-location="header" href="/customers/">Customers</a></li>
                <li><a data-track="" data-track-location="header" href="/community/">Community</a></li>
                <li><a data-track="" data-track-location="header" href="/pricing/">Pricing</a></li>
                <li><a data-track="" data-track-location="header" href="/secret/">Secret Page</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/help/">Knowledge Base</a></li>
                        <li><a href="">Forum</a></li>
                        <li><a href="">Slack</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a data-track="" data-track-location="header" href="login">Login</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>





<main>
    <section class="section section--hero">
        <div class="container">
            <div class="row">
                <div class="col-md-5">

                </div>
                <div class="col-md-7 hidden-xs hidden-sm">
                    <div class="browser-container hidden-xs">
                        <img src="/assets/images/elements/browser-header.png">
                        <video width="100%" height="100%" autoplay loop muted id="main-video">
                            <source src="/assets/images/elements/redash-intro-720.mp4" type="video/mp4">
                            <source src="/assets/images/elements/redash-intro-720.ogv" type="video/ogg">
                            <source src="/assets/images/elements/redash-intro-720.webm" type="video/webm">
                            Your browser does not support the video tag.
                        </video>
                    </div>
                </div>
            </div>
        </div><!-- container -->
    </section><!-- section -->





</main>

<footer class="footer">
    <div class="container">
        <div class="row">

            <div class="col-xs-4 col-md-2 col-sm-3 footer__item">
                <h3 class="footer__title">Redash</h3>
                <ul class="footer__list">

                    <li class="footer__list-item">
                        <a data-track data-track-location='footer' href="/product/"
                           class="footer__list-link">Product</a>
                    </li>

                    <li class="footer__list-item">
                        <a data-track data-track-location='footer' href="/integrations/" class="footer__list-link">Integrations</a>
                    </li>

                    <li class="footer__list-item">
                        <a data-track data-track-location='footer' href="/slack" class="footer__list-link">Slack Bot</a>
                    </li>

                    <li class="footer__list-item">
                        <a data-track data-track-location='footer' href="/customers/" class="footer__list-link">Customers</a>
                    </li>

                    <li class="footer__list-item">
                        <a data-track data-track-location='footer' href="/pricing/"
                           class="footer__list-link">Pricing</a>
                    </li>

                </ul><!-- footer__list -->
            </div><!-- col -->
        </div>
    </div>
    </div><!-- container -->
</footer>














<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
