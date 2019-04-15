<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CreatProject</title>
    <link href="images/Logo.png" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/person_file/bootstrap.min2.css" rel="stylesheet">
    <script src="js/market_file/cdnbootcss.js"></script>
    <script src="js/market_file/cdsbootcss2.js"></script>
    <script src="js/jquery-2.1.4.min.js"></script>
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
                    <li><a href="index.html" class="" target="_blank"><img src="images/home.png"
                                                                           class="coinimg">HOME</a></li>
                    <li><a href="code_market.action" class="" target="_blank"><img src="images/market.png"
                                                                                   class="coinimg">MARKET</a></li>
                    <li><a href="code_CreatCode.action" class="" target="_blank"><img src="images/file.png"
                                                                                      class="coinimg">BUILD</a></li>
                    <li><a href="code_project.action" class="" target="_blank"><img src="images/project.png"
                                                                                    class="coinimg">PROJECT</a></li>
                    <li><a href="user_loginOut.action" class=""><img src="images/key.png" class="coinimg">LOG-OUT</a>
                    </li>
                    <li><a href="sf_aq.action" class="" target="_blank"><img src="images/aq.png" class="coinimg">A&Q</a>
                    </li>
                    <li><a href="sf_contact.action" class="" target="_blank"><img src="images/weixin.png"
                                                                                  class="coinimg">CONTACT</a></li>
                    <li>
                        <div class="dropdown1">
                            <div class="btn-group" style="margin-top: -7px">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        style="background: #242229">
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
        </nav>
    </div>
    <a href="" style="color: red;font-size: 19px;position: absolute;top: 10px;right: 110px" id="mesnum"></a>
</header>
<div class="createProject" style="min-height: 450px">
    <div class="create_top">
        <span style="border-bottom: #E02B31 2px solid;">${user.username}</span>
        <span>/</span>
        <a href="code_proDetail.action?pid=${repertoryById.id}"><span class="repertoryname">${repertoryById.name}</span></a>
    </div>
    <div class="create_second">
        <div><> Code</div>
        <div>Description</div>
        <div>Code Resources</div>
        <div>Code Edit</div>
        <div>Other instructions</div>
    </div>
    <div class="create_four">
        <p style="margin: 30px 0">${repertoryById.info}</p>
    </div>
    <div class="create_five clearfix">
        <p style="margin-bottom:20px ">Code Resources</p>
        <div class="files">
            <c:forEach items="${filenames}" var="file">
                <div>
                    <a href="code_fileDetail.action?pid=${repertoryById.id}&filename=${file}">
                        <img src="images/book.png" alt="">
                        <span>${file}</span>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="footer">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 900px;height: 1px;margin-top: 20px;background-color:#212436;margin-left: 30px;"></div>
</div>
<script>
    $(function () {
        //每五秒查询一下是否有未读信息
        setInterval(function () {
            $.ajax({
                url: "user_QueryMessage.action",
                type: "post",
                datatype: "text",
                success: function (data) {
                    if (data != "") {
                        $("#mesnum").html(1);
                        $("#mesnum").attr("class", data);
                    }
                }
            })
        }, 5000)
    })
    //进入未读信息界面
    $("#mesnum").click(function () {
        var id = $("#mesnum").attr("class");
        window.location.href = "user_ToMessage.action?from_id=" + id;
        return false;
    })
</script>

</body>
</html>