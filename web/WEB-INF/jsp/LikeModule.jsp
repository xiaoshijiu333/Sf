<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <title>myLikeModule</title>
    <link href="images/Logo.png" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/person_file/bootstrap.min2.css" rel="stylesheet">
    <script src="js/market_file/cdnbootcss.js"></script>
    <script src="js/market_file/cdsbootcss2.js"></script>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/paging.js"></script>
    <link rel="stylesheet" type="text/css" href="css/person_file/isotope.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/person_file/da-slider.css"/>
    <!-- Owl Carousel Assets -->
    <link rel="stylesheet" href="css/person_file/styles.css"/>
    <link rel="stylesheet" href="css/person_file/self-manage.css"/>
    <link rel="stylesheet" href="css/pageStyle.css">
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
<div class="main" style="height: 900px">
    <div class="main-left">
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
    <div style="width: 1px;height:730px;background-color: #BBBBBB;position: absolute;top: 43px;left: 340px;"></div>
    <div class="main-right" id="buy_person" style="height: 900px">
        <a href="code_myModule.action"><h3 style="display: inline-block"><span style="padding-bottom: 8px">myCreatModule</span></h3></a>
        <a href="order_BuyModule.action"><h3 style="display: inline-block"><span class="overview2">myBuyModule</span></h3></a>
        <a href="order_likeCode.action"><h3 style="display: inline-block"><span class="overview" style="margin-left: 35px">myLikeModule</span></h3></a>
        <div style="width: 600px;height: 1px;background-color: #BBBBBB;margin-top: 18px;"></div>
        <div style="overflow: hidden" class="CodeModule">
            <!--个人收藏的代码模块-->
            <c:forEach items="${pageBean.pageList}" var="code">
                <div class="repositories-1" style="height:95px">
                    <a href="code_CodeDetail.action?id=${code.codeModule.id}" target="_blank">${code.codeModule.code_name}</a>
                    <p style="display: inline-block;margin-left: 70px;margin-bottom: 0px">
                        ——${code.codeModule.language}
                    </p>
                    <div class="money">
                        <span>￥${code.codeModule.price}</span>
                    </div>
                    <%--防止功能描述过长--%>
                    <c:choose>
                        <c:when test="${f:length(code.codeModule.function)>30}">
                            <p style="color:#8B8878;font-size: 13px;margin-top: 6px">
                                    ${f:substring(code.codeModule.function,0,30)}...</p>
                        </c:when>
                        <c:otherwise>
                            <p style="color:#8B8878;font-size: 13px;margin-top: 6px">
                                    ${code.codeModule.function}</p>
                        </c:otherwise>
                    </c:choose>
                    <p class="time">${code.codeModule.public_time}</p>
                    <div class="management">
                        <a href="code_CodeDetail.action?id=${code.codeModule.id}" target="_blank">View</a>
                    </div>
                </div>
            </c:forEach>
            <div class="pagepage">
                <div class="page_div"></div>
            </div>
        </div>
    </div>
</div>
<div class="footer" >
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 900px;height: 1px;margin-top: 20px;background-color:#212436;margin-left: 30px;"></div>
</div >
<script>
    //分页
    $(".page_div").paging({
            pageNo: ${pageBean.currentPage},
            totalPage: ${pageBean.totalPage},
            totalSize: ${pageBean.totalCount},
            callback: function (num) {
                window.location.href = "order_likeCode.action?currentPage=" + num;
            }
        }
    )
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
