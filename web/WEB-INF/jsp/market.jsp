<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Market</title>
    <link href="images/Logo.png" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/market_file/bootstrap.min2.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/market_file/isotope.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/market_file/da-slider.css"/>
    <link rel="stylesheet" href="css/person_file/styles.css"/>
    <link rel="stylesheet" href="css/market_file/styles.css"/>
    <link rel="stylesheet" href="css/market_file/The-code-base.css"/>
    <link rel="stylesheet" href="css/market.css"/>
    <link rel="stylesheet" href="css/pageStyle.css"/>

    <script src="js/market_file/cdnbootcss.js"></script>
    <script src="js/market_file/cdsbootcss2.js"></script>
    <script src="js/paging.js"></script>
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

<div class="main">
    <h1>Code Market</h1>
    <div class="main-search">
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search"
                       value="<s:property value="#parameters.keyname"/>" id="keyname">
            </div>
            <!--
            搜索框
            -->
            <a href="" id="imgsearch"><img class="header-searchimg" src="images/market_file/header.search.png"></a>
        </form>
    </div>
    <div class="fun-select">
        <img src="images/market_file/add-sign.png">
        Functional classification
        <select class="fun-functional" size="1">
            <option value="1" selected="selected">1&nbsp;&nbsp;&nbsp; Machine</option>
            <option value="2">2&nbsp;&nbsp;&nbsp;Engineering</option>
            <option value="3">3&nbsp;&nbsp;&nbsp; Artificial intelligence</option>
        </select>
        <img class="fun-img2" src="images/market_file/add-sign.png">
        <p>Language classification</p>
        <select class="fun-functional funal2" size="1" id="language">
            <option value="All">1&nbsp;&nbsp;&nbsp;All</option>
            <option value="C">2&nbsp;&nbsp;&nbsp; C</option>
            <option value="Java">3&nbsp;&nbsp;&nbsp;Java</option>
            <option value="Python">4&nbsp;&nbsp;&nbsp;Python</option>
        </select>
        <img class="fun-img3" src="images/market_file/add-sign.png">
        <p>Code Type</p>
        <select class="fun-functional funal2" size="1">
            <option value="1" selected="selected">1&nbsp;&nbsp;&nbsp; Web</option>
            <option value="2">2&nbsp;&nbsp;&nbsp; APP</option>
            <option value="3">3&nbsp;&nbsp;&nbsp; Applet of WeChat</option>
        </select>
    </div>

    <!-- 打印代码模块列表 -->
    <div class="center_right">
        <div>The best code base designed for developers</div>
        <div class="scoreprice">
            <span>Price</span>
            <a href="" id="up" title="Ascending by price">
                <div class="triangle"></div>
            </a>
            <a href="" id="down" title="Descending by price">
                <div class="triangle-down"></div>
            </a>
            <%--隐藏一个参数--%>
            <input type="hidden" id="price" value="<s:property value="#parameters.Porder"/>">
        </div>
        <div class="scoreprice" style="margin-right: 140px">
            <span>Score</span>
            <a href="">
                <div class="triangle"></div>
            </a>
            <a href="">
                <div class="triangle-down"></div>
            </a>
        </div>

        <c:forEach items="${modulePageBean.pageList}" var="code">
            <div class="contextList">
                <a class="codename" href="code_CodeDetail.action?id=${code.id}">${code.code_name}</a>
                    <%--防止功能描述过长--%>
                <c:choose>
                    <c:when test="${f:length(code.function)>25}">
                        <p style="margin-bottom: 0px;color:#8B8878;font-size: 14px;">
                                ${f:substring(code.function,0,25)}...</p>
                    </c:when>
                    <c:otherwise>
                        <p style="margin-bottom: 0px;color:#8B8878;font-size: 14px;">
                                ${code.function}</p>
                    </c:otherwise>
                </c:choose>
                <p class="language">${code.language}</p>
                <p>${code.public_time}</p>
                <div class="row-more"><a href="code_CodeDetail.action?id=${code.id}">MORE</a></div>
                <span class="rmb">￥${code.price}</span>
                <c:choose>
                    <c:when test="${empty user}">
                        <div class="row-buy"><a href="">BUY</a></div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${code.user_id == user.id}">
                                <div class="row-buy"><a href="" onclick="tips()">BUY</a></div>
                            </c:when>
                            <c:otherwise>
                                <div class="row-buy"><a href="#" class="${code.id}">BUY</a></div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>


        <div class="pagepage" style="margin-right: -70px;margin-top: 30px;">
            <div class="page_div"></div>
        </div>
    </div>
</div>
<div class="tcb-footer">
               <span>© 2018 Software Factory, Inc  Terms  Privacy  Security  Status  Help
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Contact Software Factory  API  Training  Shop  Blog
               </span>
</div>

<script>
    $(function () {
        //语言分类检索的数据显示
        var mark = "<s:property value="#parameters.type"/>";
        if (mark == "") {
            $("#language").val("All");
        } else {
            $("#language").val(mark);
        }
    })
    //分页
    $(".page_div").paging({

            pageNo: ${modulePageBean.currentPage},
            totalPage:${modulePageBean.totalPage},
            totalSize: ${modulePageBean.totalCount},
            callback: function (num) {
                var keyname = $("#keyname").val();
                var type = $("#language").val();
                var price = $("#price").val();
                if (keyname != "") {
                    window.location.href = "code_market.action?currentPage=" + num + "&keyname=" + keyname;
                } else if (type != "All") {
                    window.location.href = "code_market.action?currentPage=" + num + "&type=" + type;
                } else if (price != "") {
                    window.location.href = "code_market.action?currentPage=" + num + "&Porder=" + price;
                } else {
                    window.location.href = "code_market.action?currentPage=" + num;
                }
            }
        }
    )

    //点击搜索代码
    $("#imgsearch").click(function () {
        var keyname = $("#keyname").val();
        if (keyname == "") {
            alert("Please enter in the query first");
        } else {
            window.location.href = "code_market.action?keyname=" + keyname;
        }
        return false;
    })

    //不能购买自己的
    function tips() {
        alert("It's yours. You don't have to buy it");
        return false;
    }

    //是否已经购买
    $(".row-buy").on("click", "a", function () {
        if (${empty user}){
            alert("If you are not logged in, click ok to log in");
            window.location.href="login.html";
        }else {
            var id = $(this).attr("class");
            if (id != undefined) {
                $.ajax({
                    url: "order_IsBuy.action",
                    data: {id: id},
                    type: "POST",
                    dataType: "text",
                    success: function (data) {
                        if (data.toString() == "no") {
                            window.location.href = "order_ToBuy.action?id=" + id;
                        }
                        else {
                            alert("You have already bought it. You cannot repeat it");
                        }
                    }
                })
            }
        }
        return false;
    })
    //语言分类检索
    $("#language").change(function () {
        var language = $(this).val();
        if (language != "All") {
            window.location.href = "code_market.action?type=" + language;
        } else {
            window.location.href = "code_market.action";
        }
    })
    //按价格排序
    $("#up").click(function () {
        window.location.href = "code_market.action?Porder=up";
        return false;
    })
    //按价格降序
    $("#down").click(function () {
        window.location.href = "code_market.action?Porder=down";
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