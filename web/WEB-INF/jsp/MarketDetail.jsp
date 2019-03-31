<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CodeDetail</title>
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
                    <c:choose>
                        <c:when test="${empty user}">
                            <li><a href="login.html" class=""><img src="images/key.png" class="coinimg">LOG-IN</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="user_loginOut.action" class=""><img src="images/key.png" class="coinimg">LOG-OUT</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="sf_aq.action" class="" target="_blank"><img src="images/aq.png" class="coinimg">A&Q</a></li>
                    <li><a href="sf_contact.action" class="" target="_blank"><img src="images/weixin.png" class="coinimg">CONTACT</a></li>
                    <li>
                        <c:if test="${!empty user}">
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
                        </c:if>
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
<div class="center">
    <div class="center_top">
        <img src="images/note.png">
        <span>${CodeDetail.code_name}&nbsp;&nbsp;/&nbsp;&nbsp;${codeuser.username}</span>
        <img src="images/pen.png" class="penimg">
        <span class="Language">${CodeDetail.language}</span>
        <span style="margin-left: 60px">￥${CodeDetail.price}</span>
        <c:choose>
            <c:when test="${empty user}">
                <a href="" id="likea"><img src="images/like.png" style="width: 35px;height: 35px;margin-left: 90px" class="like"></a>
            </c:when>
            <c:otherwise>
                <c:if test="${CodeDetail.user_id != user.id}">
                    <a href="" id="likea"><img src="images/like.png" style="width: 35px;height: 35px;margin-left: 90px" class="like"></a>
                </c:if>
            </c:otherwise>
        </c:choose>

        <span style="margin-left: 50px;color: red" id="hadbuy"></span>

        <c:choose>
            <c:when test="${empty user}">
                <a href="user_ToMessage.action?from_id=${CodeDetail.user_id}" title="take a talk">
                    <img src="images/message.png" style="margin-left: 50px">
                </a>
            </c:when>
            <c:otherwise>
                <c:if test="${CodeDetail.user_id != user.id}">
                    <a href="user_ToMessage.action?from_id=${CodeDetail.user_id}" title="take a talk">
                        <img src="images/message.png" style="margin-left: 50px">
                    </a>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="center_top2" style="margin-top: 40px">
        <div class="first"></div>
        <div class="description">Code Description</div>
        <span style="margin-left: 50px"><a href="" id="codefile">${CodeDetail.code_file}</a></span>
        <div class="context" id="context" style="display: none">
            ${context}
        </div>
        <div style="margin-top: 20px;border-bottom: 4px solid #E5E5E5;width: 700px"></div>
    </div>
    <div class="center_top3" style="margin-top: 35px">
        <div class="func">Key Word&nbsp;&nbsp;&nbsp;&nbsp;<b>></b></div>
        <div style="margin-top: 15px">
            <span>${CodeDetail.keyword1}</span>
            <span>${CodeDetail.keyword2}</span>
            <span>${CodeDetail.keyword3}</span>
            <span>${CodeDetail.keyword4}</span>
        </div>
    </div>
    <div style="margin-top: 20px">
        <div class="func">Function Description&nbsp;&nbsp;&nbsp;&nbsp;<b>></b></div>
        <textarea style="height: 150px">${CodeDetail.function}</textarea>
    </div>
    <div style="margin-top: 35px">
        <div class="func">Detailed Introduction&nbsp;&nbsp;&nbsp;&nbsp;<b>></b></div>
        <textarea style="height: 250px;">${CodeDetail.info}</textarea>
    </div>
    <div style="margin-top: 35px;overflow: hidden">
        <div class="accept">
            <div class="trans">Code Transactions</div>
            <div style="float: left;margin-left: 50px;margin-top: 15px">
                <input type="radio" name="accept" value="yes" id="accept">&nbsp;&nbsp;I accept all agreements relating
                to code transactions on
                this site <br>
                <input type="radio" name="accept" value="no">&nbsp;&nbsp;I don't accept.
            </div>
        </div>
        <a href="" id="buy">
            <div class="buy">BUY IT NOW</div>
        </a>
    </div>
</div>
<div class="footer" style="margin: 100px auto 0;width: 1000px">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 900px;height: 1px;margin: 20px auto 0;background-color:#212436"></div>
</div>
<script>
    $(function () {
        if (!${empty user}){
            //显示是否已经购买
            $.ajax({
                url: "order_IsBuy.action",
                data: {id:${CodeDetail.id}},
                type: "POST",
                dataType: "text",
                success: function (data) {
                    if (data.toString() == "no") {
                        $("#hadbuy").html("");
                    }
                    else {
                        $("#hadbuy").html("已购买");
                    }
                }
            })
            if ($(".like").is(":visible")) {
                //显示收藏和未收藏
                $.ajax({
                    url: "order_IsLike.action",
                    data: {id:${CodeDetail.id}},
                    type: "POST",
                    dataType: "text",
                    success: function (data) {
                        if (data.toString() == "yes") {
                            $(".like").attr("src", "images/like2.png");
                        }
                    }
                })
            }
        }
    })

    //点击收藏或者取消收藏
    $("#likea").click(function () {
        if (${empty user}){
            alert("If you are not logged in, click ok to log in");
            window.location.href="login.html";
        }else {
            if ($(".like").attr("src") == "images/like.png") {
                //收藏
                $.ajax({
                    url: "order_AddLikeCode.action",
                    data: {id:${CodeDetail.id}},
                    type: "POST",
                    dataType: "text",
                    success: function () {
                        alert("Collection of success");
                        window.location.reload();
                    }
                })
            } else {
                //取消收藏
                $.ajax({
                    url: "order_DeleteLikeCode.action",
                    data: {id:${CodeDetail.id}},
                    type: "POST",
                    dataType: "text",
                    success: function () {
                        alert("Cancel the collection successfully");
                        window.location.reload();
                    }
                })
            }
        }
        return false;
    })

    //没有购买不能看文件
    $("#codefile").click(function () {
        if (${empty user}){
            alert("If you are not logged in, click ok to log in");
            window.location.href="login.html";
        }else {
            if ("${codeuser.username}" == "${user.username}") {
                if ($("#context").is(":visible")) {
                    $("#context").hide();
                } else {
                    $("#context").show();
                }
            } else {
                if ($("#hadbuy").html() == "") {
                    alert("Can't read documents without buying");
                } else {
                    if ($("#context").is(":visible")) {
                        $("#context").hide();
                    } else {
                        $("#context").show();
                    }
                }
            }
        }
        return false;
    })

    //购买前需要先接受承诺以及避免重复购买
    $("#buy").click(function () {
        if (${empty user}){
            alert("If you are not logged in, click ok to log in");
            window.location.href="login.html";
        }else {
            if (!$("#accept").is(":checked")) {
                alert("Please accept the purchase commitment first");
            } else if ("${codeuser.username}" == "${user.username}") {
                alert("It's yours. You don't have to buy it");
            } else {
                if ($("#hadbuy").html() == "") {
                    window.location.href = "order_ToBuy.action?id=${CodeDetail.id}";
                } else {
                    alert("You have already bought it. You cannot repeat it");
                }
            }
        }
        return false;
    })
    $(function () {
        //过滤未登录情况
        if (!${empty user}) {
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
        }
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