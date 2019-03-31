<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link href="images/Logo.png" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/person_file/bootstrap.min2.css" rel="stylesheet">
    <script src="js/market_file/cdnbootcss.js"></script>
    <script src="js/market_file/cdsbootcss2.js"></script>
    <script src="js/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/person_file/isotope.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/person_file/da-slider.css"/>
    <!-- Owl Carousel Assets -->
    <link rel="stylesheet" href="css/person_file/styles.css"/>
    <link rel="stylesheet" href="css/person_file/self-manage.css"/>
    <link rel="stylesheet" href="css/CodeDetail.css"/>
    <style>
        .cenetr_right span {
            color: #585656;
        }

        .center_left span {
            color: #101010;
            font-size: 18px;
        }

        .cenetr_right .center_top2 .context {
            width: 795px;
            height: 400px;
            overflow-y: auto;
            overflow-x: hidden;
            border: 2px solid #BBBBBB;
            border-radius: 9px;
        }

        .cenetr_right .center_top2 .context a{
            font-size: 17px;
        }
    </style>
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
<div class="center" style="min-height: 650px;width: 1040px;margin: 120px auto 0">
    <div class="center_left">
        <c:choose>
            <c:when test="${empty codeuser.head_image}">
                <img src="images/market_file/user.png" class="codeuserimg">
            </c:when>
            <c:otherwise>
                <img src="upload/${codeuser.head_image}" class="codeuserimg">
            </c:otherwise>
        </c:choose>
        <div class="left_bottom">
            <span style="margin-left: 0">${codeuser.username}</span>
            <a href="code_otherModule.action?uid=${codeuser.id}"><img src="images/home1.png" alt="" class="mes1"></a>
            <a href=""><img src="images/mes.png" alt="" class="mes2"></a>
        </div>
    </div>
    <div class="cenetr_right" style="width: 780px">
        <div class="center_top" style="overflow: hidden">
            <a href="" id="back"><img src="images/back.png" alt=""><span style="margin-left: 10px">back</span></a>
            <div style="float: right">
                <img src="images/message2.png" style="margin-left: 10px">
                <span>Chat&nbsp;&nbsp; With&nbsp;&nbsp; ${codeuser.username}</span>
            </div>
        </div>
        <div class="center_top2">
            <div class="context">
                <div id="message">
                    <c:forEach items="${messageList}" var="mes">
                        <c:choose>
                            <c:when test="${mes.fromUser.id == user.id}">
                                <div class="message2">
                                    <p style="margin-right: 8px">${mes.time}</p>
                                    <span>${mes.context}</span>
                                    <c:choose>
                                        <c:when test="${empty user.head_image}">
                                            <img src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="upload/${user.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="message">
                                    <a href="code_otherModule.action?uid=${codeuser.id}">
                                        <c:choose>
                                            <c:when test="${empty mes.fromUser.head_image}">
                                                <img class="user-img" src="images/market_file/user.png">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="user-img" src="upload/${mes.fromUser.head_image}">
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                    <span>${mes.context}</span>
                                    <p style="margin-left: 8px">${mes.time}</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
            <div class="bottom">
                <a href="" id="picfile"><img src="images/pic.png" alt="" class="picimg"></a>
                <input type="file" style="display: none" id="inputfile">
                <input type="text" name="message.context" id="context">
                <button class="btn btn-success" id="send">Send</button>
            </div>
        </div>
    </div>
</div>
<div class="footer" style="margin-top: 40px;margin-left: 280px;width: 1000px">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 800px;height: 1px;margin-top: 20px;background-color:#212436;margin-left: 85px;"></div>
</div>

<script>
    //点击图片上传文件
    $("#picfile").click(function () {
        //触发input
        $("#inputfile").trigger("click");
        return false;
    })
    //发送图片
    $("#inputfile").change(function () {
        //注意这里不能写错。。。
        var file = $(this)[0].files[0];
        var formData = new FormData();
        formData.append("upload", file);
        formData.append("to_id", ${codeuser.id});
        //对文件类型进行判断
        var index = file.name.lastIndexOf(".");
        var type = file.name.substring(index);
        if (type != ".jpg" && type != ".png") {
            alert("只能上传jpg和png格式的图片！！");
            return false;
        }
        //对图片大小验证
        if ((file.size / 1024) > 2048) {
            alert("上传图片的最大尺寸为2M！！");
            return false;
        }
        $.ajax({
            url: "user_Mespic.action",
            data: formData,
            type: "post",
            datatype: "json",
            contentType: false,
            processData: false,
            success: function (mes) {
                var data=JSON.parse(mes);
                $("#message").append(
                    "<div class='message2'>" +
                    "<p style='margin-right: 8px'>" + data.time + "</p>" +
                    "<span>图片：<a href='messagepic/"+ data.context +"' target=\"_blank\">"
                    + data.context + "</span>" +
                    <c:choose>
                    <c:when test='${empty user.head_image}'>
                    "<img src='images/market_file/user.png'>" +
                    </c:when>
                    <c:otherwise>
                    "<img src='upload/${user.head_image}'>" +
                    </c:otherwise>
                    </c:choose>
                    "</div>"
                )
            }
        })
    });
    //发送私信
    $("#send").click(function () {
        var context = $("#context").val();
        if (context == "") {
            alert("The sent content cannot be empty");
        } else {
            $.ajax({
                url: "user_SendMessage.action",
                data: {"to_id":${codeuser.id}, "context": context},
                type: "post",
                datatype: "text",
                success: function (data) {
                    $("#message").append(
                        "<div class='message2'>" +
                        "<p style='margin-right: 8px'>" + data + "</p>" +
                        "<span>" + context + "</span>" +
                        <c:choose>
                        <c:when test='${empty user.head_image}'>
                        "<img src='images/market_file/user.png'>" +
                        </c:when>
                        <c:otherwise>
                        "<img src='upload/${user.head_image}'>" +
                        </c:otherwise>
                        </c:choose>
                        "</div>"
                    )
                    $("#context").val("");
                }
            })
        }
    })
    //返回按钮
    $("#back").click(function () {
        window.history.back(-1);
        return false;
    })
    $(function () {
        //每二秒查询一下是否有未读信息，实时显示未读信息
        setInterval(function () {
            $.ajax({
                url: "user_OneMessage.action",
                type: "post",
                datatype: "json",
                data: {"from_id":${codeuser.id}},
                success: function (data) {
                    if (null != data && "" != data) {
                        $.each(JSON.parse(data), function (i, obj) {
                            $("#message").append(
                                "<div class='message'>" +
                                <c:choose>
                                <c:when test='${empty codeuser.head_image}'>
                                "<img src='images/market_file/user.png'>" +
                                </c:when>
                                <c:otherwise>
                                "<img src='upload/${codeuser.head_image}'>" +
                                </c:otherwise>
                                </c:choose>
                                "<span>" + obj.context + "</span>" +
                                "<p style='margin-right: 8px'>" + obj.time + "</p>" +
                                "</div>"
                            )
                        })
                    }
                }
            })
        }, 2000)

        //滚动条到最低端
        $('.context').scrollTop( $('.context')[0].scrollHeight);
    })
</script>

</body>
</html>