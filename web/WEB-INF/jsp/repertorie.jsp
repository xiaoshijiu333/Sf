<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create a new repository</title>
    <link href="images/Logo.png" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/person_file/bootstrap.min2.css" rel="stylesheet">
    <script src="js/market_file/cdnbootcss.js"></script>
    <script src="js/market_file/cdsbootcss2.js"></script>
    <script src="js/jquery-2.1.4.min.js"></script>
    <!-- Owl Carousel Assets -->
    <link rel="stylesheet" href="css/person_file/styles.css"/>
    <link rel="stylesheet" href="css/person_file/self-manage.css"/>
    <link rel="stylesheet" href="css/repertorie/repertorie.css"/>
</head>
<body>
<header class="header">
    <div class="container">
        <nav class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <a href="index.html" class="navbar-brand scroll-top logo"><b>Software Factory</b></a>
            </div>
            <!--/.navbar-header-->
            <div id="main-nav" class="collapse navbar-collapse">
                <ul class="nav navbar-nav" id="mainNav">
                    <li><a href="index.html" class="" target="_blank"><img src="images/home.png" class="coinimg">HOME</a></li>
                    <li><a href="code_market.action" class="" target="_blank"><img src="images/market.png" class="coinimg">MARKET</a></li>
                    <li><a href="code_CreatCode.action" class="" target="_blank"><img src="images/file.png" class="coinimg">BUILD</a></li>
                    <li><a href="code_project.action" class="" target="_blank"><img src="images/project.png" class="coinimg">PROJECT</a></li>
                    <li><a href="user_loginOut.action" class=""><img src="images/key.png" class="coinimg">LOG-OUT</a></li>
                    <li><a href="sf_aq.action" class="" target="_blank"><img src="images/aq.png" class="coinimg">A&Q</a></li>
                    <li><a href="sf_contact.action" class="" target="_blank"><img src="images/weixin.png" class="coinimg">CONTACT</a></li>
                    <li>
                        <div class="dropdown1">
                            <div class="btn-group" style="margin-top: -7px">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="background: #242229">
                                    <c:choose>
                                        <c:when test="${empty user.head_image}">
                                            <img class="user-img" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="user-img" src="upload/${user.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="code_Creatrep.action">Create a Repository</a>
                                    </li>
                                    <li>
                                        <a href="code_myModule.action">My CodeModules</a>
                                    </li>
                                    <li>
                                        <a href="order_BuyModule.action">Bought CodeModules</a>
                                    </li>
                                    <li>
                                        <a href="order_likeCode.action">Liked CodeModules</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a href="user_threeMessage.action">Recent Message</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <!--/.navbar-collapse-->
        </nav>
        <!--/.navbar-->
    </div>
    <!--/.container-->
    <a href="" style="color: red;font-size: 19px;position: absolute;top: 10px;right: 110px" id="mesnum"></a>
</header>
<!--/.header-->
<div class="center">
    <div class="center_top">
        <h2>Create a new repository</h2>
        <p>A repository contains all the files for your project, including the revision history</p>
    </div>
    <form action="code_Saverep.action" method="post" id="Saverepertorie">
        <div class="center_top2">
            <div class="div">
                <p>Owner</p>
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <c:choose>
                            <c:when test="${empty user.head_image}">
                                <img src="images/market_file/user.png">&nbsp;&nbsp;${user.username}
                            </c:when>
                            <c:otherwise>
                                <img src="upload/${user.head_image}">&nbsp;&nbsp;${user.username}
                            </c:otherwise>
                        </c:choose>
                        <img src="images/xiala.png" style="width: 15px;height: 15px">
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="" onclick="return false">
                                <c:choose>
                                    <c:when test="${empty user.head_image}">
                                        <img src="images/market_file/user.png">&nbsp;&nbsp;${user.username}
                                    </c:when>
                                    <c:otherwise>
                                        <img src="upload/${user.head_image}">&nbsp;&nbsp;${user.username}
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="div">
                <p style="margin-top: 43px"><b>/</b></p>
            </div>
            <div class="div">
                <p style="margin-left: 5px">Repository name</p>
                <input type="text" placeholder="Add a repository name" name="repertory.name" id="repertoriename" required="required">
            </div>
        </div>
        <p style="color: #8B8878;margin-top: 10px">
            Great repository names are short and memorable. Need inspiration? How about silver-tribble
        </p>
        <div class="center_top3">
            <p>Description (optional)</p>
            <input type="text" placeholder="Add a brief description" style="width: 700px" name="repertory.info"
                   id="repertorieinfo" required="required">
        </div>
        <div class="center_top4">
            <div>
                <input type="radio" value="public" name="repertory.status" checked="checked"><img src="images/public.png">
                <div style="float: right;margin-right: 450px">
                    <p style="color: #7D9EC0;font-size: 20px">Public</p>
                    <p style="margin-top: -10px">Anyone can see this repository. You choose who can commit</p>
                </div>
            </div>
            <div style="margin-top: 15px">
                <input type="radio" value="private" name="repertory.status"><img src="images/private.png">
                <div style="float: right;margin-right: 485px">
                    <p style="color: #7D9EC0;font-size: 20px">Private</p>
                    <p style="margin-top: -10px">You choose who can see and commit to this repository</p>
                </div>
            </div>
        </div>
        <div class="center_top4" style="height: 75px">
            <input type="checkbox">
            <span style="color: #7D9EC0;font-size: 20px;margin-left: 10px">Initialize this repository with a README</span>
            <p style="margin-left: 25px">This will let you immediately clone the repository to your computer.
                Skip this step if you are importing an existing repository</p>
        </div>
        <div class="center_top5">
            <input type="submit" value="Creat a repertorie" id="createrepertorie">
        </div>
    </form>

</div>
<div class="footer">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 900px;height: 1px;margin-top: 20px;background-color:#212436;margin-left: 30px;"></div>
</div>
<script>
    //监听添加仓库按钮
    $("#createrepertorie").click(function () {
        if($("#repertoriename").val()!="" && $("#repertorieinfo").val()!="" ){
            $("#Saverepertorie").submit();
            return false;
        }
    })
    $(function () {
        //每五秒查询一下是否有未读信息
        setInterval(function () {
            $.ajax({
                url:"user_QueryMessage.action",
                type:"post",
                datatype:"text",
                success:function (data) {
                    if (data!="") {
                        $("#mesnum").html(1);
                        $("#mesnum").attr("class",data);
                    }
                }
            })
        },5000)
    })
    //进入未读信息界面
    $("#mesnum").click(function () {
        var id=$("#mesnum").attr("class");
        window.location.href="user_ToMessage.action?from_id="+id;
        return false;
    })
</script>
</body>
</html>
