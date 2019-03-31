<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Record Message</title>
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
    <link rel="stylesheet" href="css/recordMes.css"/>
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
<div class="center">
    <div class="center_top">
        <p>Message Record</p>
        <form action="">
            <input type="text" placeholder="Search" id="inputname">
            <a href="" id="search"><img src="images/market_file/header.search.png" alt=""></a>
        </form>
    </div>
    <div class="center_left">
        <div class="photo-behind"></div>
        <div class="photo">
            <c:choose>
                <c:when test="${empty user.head_image}">
                    <img src="" id="user_picture">
                    <p id="nophoto">No Photo</p>
                </c:when>
                <c:otherwise>
                    <img src="upload/${user.head_image}" id="user_picture">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="name">
            <p>Username:&nbsp;&nbsp;${user.username}</p>
            <p>Email:&nbsp;&nbsp;${user.email}</p>
            <p>Age:&nbsp;&nbsp;${user.age}</p>
            <p>Address:&nbsp;&nbsp;${user.address}</p>
        </div>

        <div class="bio">
            <p>self_info:</p>
            <div class="intro">
                <c:choose>
                    <c:when test="${empty user.self_info}">
                        <p style="margin-top: 5px">
                            You haven't written your profile yet...
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p style="margin-top: 8px;margin-left: 8px">${user.self_info}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="center_right">
        <h3>最近三天内</h3>
        <ul style="list-style: none" id="three">
            <c:choose>
                <c:when test="${empty threeMessage}">
                    <div class="right_top"></div>
                    <div class="right_context" style="text-indent: 10px">最近三天没有信息记录...</div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${threeMessage}" var="mes" varStatus="status">
                        <li class="${status.index}" id="w${status.index}" style="display: none">
                            <div class="right_top">
                                <c:choose>
                                    <c:when test="${empty mes.fromUser.head_image}">
                                        <img class="codeuserimg" src="images/market_file/user.png">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="codeuserimg" src="upload/${mes.fromUser.head_image}">
                                    </c:otherwise>
                                </c:choose>
                                <div class="left_bottom">
                                    <span style="margin-left: 0">${mes.fromUser.username}</span>
                                    <a href="code_otherModule.action?uid=${mes.fromUser.id}"><img src="images/home1.png" alt="" class="mes1"></a>
                                    <a href="user_ToMessage.action?from_id=${mes.fromUser.id}"><img src="images/mes.png" alt="" class="mes2"></a>
                                </div>
                            </div>
                            <div class="right_context">
                                <div class="message">
                                    <c:choose>
                                        <c:when test="${empty mes.fromUser.head_image}">
                                            <img class="codeuserimg" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="codeuserimg" src="upload/${mes.fromUser.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                    <span>${mes.context}</span>
                                    <p>${mes.time}</p>
                                </div>
                            </div>
                            <p class="messagetime">Date:${mes.time}</p>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
        <a href="" id="left"><img src="images/left2.png" alt="" class="leftpic"></a>
        <a href="" id="right"><img src="images/right2.png" alt="" class="rightpic"></a>
    </div>
    <div class="center_right" style="margin-top: 40px">
        <div style="overflow: hidden">
            <h3 style="float: left">过去一周内</h3>
            <div style="float: left;width: 560px;margin-left: 120px;">
                <a href="" id="leftimg"><img src="images/left3.png" alt="" class="leftpic2"></a>
                <c:choose>
                    <c:when test="${empty weekmes}">
                        <a href="" id="rightimg"><img src="images/right3.png" alt=""
                                                      class="rightpic2" style="margin-top: 20px;"></a>
                    </c:when>
                    <c:otherwise>
                        <ul style="list-style: none" id="weekimg">
                            <c:forEach items="${weekmes}" var="wmes" varStatus="status">
                                <li class="${status.index}" id="i${status.index}" style="display: none">
                                    <c:choose>
                                        <c:when test="${empty wmes.head_image}">
                                            <img class="otherimg" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="otherimg" src="upload/${wmes.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                        <a href="" id="rightimg"><img src="images/right3.png" alt="" class="rightpic2"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <ul style="list-style: none" id="week">
            <c:choose>
                <c:when test="${empty WeekMessage}">
                    <div class="right_top" style="margin-top: 30px"></div>
                    <div class="right_context" style="margin-top: 30px;text-indent: 10px">过去一周内没有信息记录...</div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${WeekMessage}" var="week" varStatus="status">
                        <li class="${status.index}" id="f${status.index}" style="display: none">
                            <div class="right_top" style="margin-top: 30px">
                                <c:choose>
                                    <c:when test="${empty week.fromUser.head_image}">
                                        <img class="codeuserimg" src="images/market_file/user.png">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="codeuserimg" src="upload/${week.fromUser.head_image}">
                                    </c:otherwise>
                                </c:choose>
                                <div class="left_bottom">
                                    <span style="margin-left: 0">${week.fromUser.username}</span>
                                    <a href="code_otherModule.action?uid=${week.fromUser.id}"><img
                                            src="images/home1.png" alt="" class="mes1"></a>
                                    <a href="user_ToMessage.action?from_id=${week.fromUser.id}"><img
                                            src="images/mes.png" alt="" class="mes2"></a>
                                </div>
                            </div>
                            <div class="right_context" style="margin-top: 30px">
                                <div class="message">
                                    <c:choose>
                                        <c:when test="${empty week.fromUser.head_image}">
                                            <img class="codeuserimg" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="codeuserimg" src="upload/${week.fromUser.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                    <span>${week.context}</span>
                                    <p>${week.time}</p>
                                </div>
                            </div>
                            <p class="messagetime">Date:${week.time}</p>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
        <a href="" id="left2"><img src="images/left2.png" alt="" class="leftpic"></a>
        <a href="" id="right2"><img src="images/right2.png" alt="" class="rightpic"></a>
    </div>
    <div class="center_right" style="margin-top: 40px">
        <div style="overflow: hidden">
            <h3 style="float: left">更久之前</h3>
            <div style="float: left;width: 560px;margin-left: 135px;">
                <a href="" id="leftimg2"><img src="images/left3.png" alt="" class="leftpic2"></a>
                <c:choose>
                    <c:when test="${empty beforemes}">
                        <a href="" id="rightimg2"><img src="images/right3.png" alt=""
                                                      class="rightpic2" style="margin-top: 20px;"></a>
                    </c:when>
                    <c:otherwise>
                        <ul style="list-style: none" id="beforeimg">
                            <c:forEach items="${beforemes}" var="bmes" varStatus="status">
                                <li class="${status.index}" id="m${status.index}" style="display: none">
                                    <c:choose>
                                        <c:when test="${empty bmes.head_image}">
                                            <img class="otherimg" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="otherimg" src="upload/${bmes.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                        <a href="" id="rightimg2"><img src="images/right3.png" alt="" class="rightpic2"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <ul style="list-style: none" id="before">
            <c:choose>
                <c:when test="${empty BeforeMessage}">
                    <div class="right_top" style="margin-top: 30px"></div>
                    <div class="right_context" style="margin-top: 30px;text-indent: 10px">没有更久之前的信息记录...</div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${BeforeMessage}" var="before" varStatus="status">
                        <li class="${status.index}" id="g${status.index}" style="display: none">
                            <div class="right_top" style="margin-top: 30px">
                                <c:choose>
                                    <c:when test="${empty before.fromUser.head_image}">
                                        <img class="codeuserimg" src="images/market_file/user.png">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="codeuserimg" src="upload/${before.fromUser.head_image}">
                                    </c:otherwise>
                                </c:choose>
                                <div class="left_bottom">
                                    <span style="margin-left: 0">${before.fromUser.username}</span>
                                    <a href="code_otherModule.action?uid=${before.fromUser.id}"><img
                                            src="images/home1.png" alt="" class="mes1"></a>
                                    <a href="user_ToMessage.action?from_id=${before.fromUser.id}"><img
                                            src="images/mes.png" alt="" class="mes2"></a>
                                </div>
                            </div>
                            <div class="right_context" style="margin-top: 30px">
                                <div class="message">
                                    <c:choose>
                                        <c:when test="${empty before.fromUser.head_image}">
                                            <img class="codeuserimg" src="images/market_file/user.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="codeuserimg" src="upload/${before.fromUser.head_image}">
                                        </c:otherwise>
                                    </c:choose>
                                    <span>${before.context}</span>
                                    <p>${before.time}</p>
                                </div>
                            </div>
                            <p class="messagetime">Date:${before.time}</p>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
        <a href="" id="left3"><img src="images/left2.png" alt="" class="leftpic"></a>
        <a href="" id="right3"><img src="images/right2.png" alt="" class="rightpic"></a>
    </div>
</div>
<div class="footer" style="margin-top: 70px;margin-left: 280px;width: 1000px">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 800px;height: 1px;margin-top: 20px;background-color:#212436;margin-left: 85px;"></div>
</div>
<script>
    $(function () {
        //显示第一个
        $("#w0").show();
        $("#f0").show();
        $("#g0").show();
        $("#m0").show();
        $("#m1").show();
        $("#m2").show();
        $("#i0").show();
        $("#i1").show();
        $("#i2").show();
    })
    //切换
    $("#right").click(function () {
        var length = $("#three li").length;
        length = parseInt(length);
        $.each($("#three li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == length - 1) {
                    alert("This is the last piece of message");
                } else {
                    $(this).hide();
                    var id = num + 1;
                    $("#w" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#left").click(function () {
        $.each($("#three li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == 0) {
                    alert("That's the first message");
                } else {
                    $(this).hide();
                    var id = num - 1;
                    $("#w" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#right2").click(function () {
        var length = $("#week li").length;
        length = parseInt(length);
        $.each($("#week li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == length - 1) {
                    alert("This is the last piece of message");
                } else {
                    $(this).hide();
                    var id = num + 1;
                    $("#f" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#left2").click(function () {
        $.each($("#week li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == 0) {
                    alert("That's the first message");
                } else {
                    $(this).hide();
                    var id = num - 1;
                    $("#f" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#right3").click(function () {
        var length = $("#before li").length;
        length = parseInt(length);
        $.each($("#before li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == length - 1) {
                    alert("This is the last piece of message");
                } else {
                    $(this).hide();
                    var id = num + 1;
                    $("#g" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#left3").click(function () {
        $.each($("#before li"), function () {
            if ($(this).is(":visible")) {
                var num = $(this).attr("class");
                num = parseInt(num);
                if (num == 0) {
                    alert("That's the first message");
                } else {
                    $(this).hide();
                    var id = num - 1;
                    $("#g" + id).show();
                }
                return false;
            }
        })
        return false;
    })
    $("#rightimg").click(function () {
        var length = $("#weekimg li").length;
        length = parseInt(length);
        if (length > 3) {
            $.each($("#weekimg li"), function () {
                if ($(this).is(":visible")) {
                    var num = $(this).attr("class");
                    num = parseInt(num);
                    if (num == length - 3) {
                        alert("This is the last piece of message");
                    } else {
                        var id = num + 3;
                        var bid = num + 2;
                        $("#i" + bid).hide();
                        $("#i" + id).show();
                    }
                    return false;
                }
            })
        }
        return false;
    })
    $("#leftimg").click(function () {
        var length = $("#weekimg li").length;
        length = parseInt(length);
        if (length > 3) {
            $.each($("#weekimg li"), function () {
                if ($(this).is(":visible")) {
                    var num = $(this).attr("class");
                    num = parseInt(num);
                    if (num == 0) {
                        alert("That's the first message");
                    } else {
                        $(this).hide();
                        var id = num - 1;
                        $("#i" + id).show();
                    }
                    return false;
                }
            })
        }
        return false;
    })
    $("#rightimg2").click(function () {
        var length = $("#beforeimg li").length;
        length = parseInt(length);
        if (length > 3) {
            $.each($("#beforeimg li"), function () {
                if ($(this).is(":visible")) {
                    var num = $(this).attr("class");
                    num = parseInt(num);
                    if (num == length - 3) {
                        alert("This is the last piece of message");
                    } else {
                        var id = num + 3;
                        var bid = num + 2;
                        $("#m" + bid).hide();
                        $("#m" + id).show();
                    }
                    return false;
                }
            })
        }
        return false;
    })
    $("#leftimg2").click(function () {
        var length = $("#beforeimg li").length;
        length = parseInt(length);
        if (length > 3) {
            $.each($("#beforeimg li"), function () {
                if ($(this).is(":visible")) {
                    var num = $(this).attr("class");
                    num = parseInt(num);
                    if (num == 0) {
                        alert("That's the first message");
                    } else {
                        $(this).hide();
                        var id = num - 1;
                        $("#m" + id).show();
                    }
                    return false;
                }
            })
        }
        return false;
    })
    //搜索私信
    $("#search").click(function () {
        var name = $("#inputname").val();
        if (name == "") {
            alert("Please enter the content first");
        }
        else {
            $.ajax({
                url: "user_search.action",
                data: {username: name},
                type: "POST",
                dataType: "text",
                success: function (data) {
                    if (data.toString() == "err") {
                        alert("No such user");
                    }
                    else {
                        window.location.href = "user_ToMessage.action?from_id=" + data;
                    }
                }
            })
        }
        return false;
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