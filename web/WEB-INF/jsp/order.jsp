<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order</title>
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
    <link rel="stylesheet" href="css/order.css"/>
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
    <h3 style="margin-left: 290px;font-size: 30px">Check Information</h3>
    <div class="center_left">
        <div class="perinfo" style="margin-top: 60px">
            <div class="info_header"><b>Personal Information</b></div>
            <div class="info_body">
                <p style="padding-top: 30px">Username:&nbsp;&nbsp;${user.username}</p>
                <p>Level:&nbsp;&nbsp;normal</p>
                <p>Identity:&nbsp;&nbsp;buyer</p>
                <p style="padding-bottom: 30px">Purchase time:&nbsp;&nbsp;${Usercount}</p>
            </div>
        </div>
        <div class="codeinfo" style="margin-top: 85px">
            <div class="info_header"><b>CODE Information</b></div>
            <div class="info_body">
                <p style="padding-top: 30px">Code Name:&nbsp;&nbsp;${codeModule.code_name}</p>
                <p>Author name:&nbsp;&nbsp;${userById.username}</p>
                <p>Classification:&nbsp;&nbsp;${codeModule.keyword1}</p>
                <p>Language:&nbsp;&nbsp;${codeModule.language}</p>
                <p>Purchase time:&nbsp;&nbsp;${Sellercount}</p>
                <p style="padding-bottom: 30px">Date:&nbsp;&nbsp;${codeModule.public_time}</p>
            </div>
        </div>
        <a href="" id="checkinfo"><div class="checkinfo">Check</div></a>
        <a href=""><div class="changeinfo">Change</div></a>
        <span class="tips" id="checktip"></span>
        <h2 style="color: #E68F10;margin-left: 20px;margin-top: 40px">Total Price:&nbsp;&nbsp;${codeModule.price}</h2>
    </div>
    <div class="xian"></div>
    <div class="center_right">
        <div class="Agreeinfo" style="margin-top: 60px;margin-left: 55px">
            <div class="info_header"><b>Agreement</b></div>
            <div class="info_body" style="height: 630px" id="agreement">
                <p>1:&nbsp;&nbsp;Personal information may not be sold</p>
                <p>2:&nbsp;&nbsp;The purchase is voluntary</p>
                <p>3:&nbsp;&nbsp;Not for commercial use</p>
                <p>4:&nbsp;&nbsp;The website does not promise any disputes</p>
                <p>6:&nbsp;&nbsp;The website does not guarantee any payment amount</p>
                <p>7:&nbsp;&nbsp;Don't brush list</p>
                <p>8:&nbsp;&nbsp;Every transaction is evaluated</p>
                <p>9:&nbsp;&nbsp;The code must not involve sensitive words</p>
                <p>10:&nbsp;&nbsp;Crack down on fake transactions</p>
            </div>
            <a href="" id="agreeinfo"><div class="imagree">I agree</div></a>
            <span class="tips" style="margin-left: 145px;margin-top: 10px" id="agreetip"></span>
        </div>
    </div>
    <a href="" id="buyitnow"><div class="buyitnow">Buy it now</div></a>
    <a href="code_market.action"><div class="backitnow">Back</div></a>
</div>
<div class="footer" style="margin: 230px auto 0;width: 950px">
    © 2018 Software Factory, Inc Terms Privacy Security Status Help
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Contact Software Factory API Training Shop Blog
    <div style="width: 800px;height: 1px;margin: 20px auto 0;background-color:#212436;"></div>
</div>

<script>
    //准备工作
    $("#checkinfo").click(function () {
        $("#checktip").html("Checked");
        return false;
    })
    $("#agreeinfo").click(function () {
        $("#agreetip").html("Agreed");
        return false;
    })
    //购买
    $("#buyitnow").click(function () {
        if ($("#checktip").html()=="") {
            alert("You need to make sure the information is correct");
        }else if($("#agreetip").html()==""){
            alert("You need to agree to the purchase terms");
        }else{
            window.location.href="order_buyCode.action?id=${codeModule.id}&seller_name=${userById.username}";
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