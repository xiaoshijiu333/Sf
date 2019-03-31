<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <title>Person</title>
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
<div class="main">
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
        <a class="edit-infor" href=""
           style="display: inline-block; text-align: center" id="edit_info">Edit info</a>
    </div>
    <div style="width: 1px;height:730px;background-color: #BBBBBB;position: absolute;top: 43px;left: 340px;"></div>
    <div class="main-right" id="buy_person" style="height: 900px">
        <a href="code_myModule.action"><h3 style="display: inline-block"><span class="overview">myCreatModule</span></h3></a>
        <a href="code_CreatCode.action" title="Creat a codeModule" target="_blank"><img
                style="width:15px;height:15px;margin-top: -7px;margin-left: 20px" src="images/person_file/add.png"></a>
        <a href="order_BuyModule.action"><h3 style="display: inline-block"><span class="overview2">myBuyModule</span></h3></a>
        <a href="order_likeCode.action"><h3 style="display: inline-block"><span class="overview2">myLikeModule</span></h3></a>
        <div style="width: 600px;height: 1px;background-color: #BBBBBB;margin-top: 18px;"></div>
        <div style="overflow: hidden" class="CodeModule">
            <!--个人代码模块-->
            <c:forEach items="${pageBean.pageList}" var="code">
                <div class="repositories-1" style="height:95px">
                    <a href="code_ViewCode.action?id=${code.id}" target="_blank">${code.code_name}</a>
                    <p style="display: inline-block;margin-left: 70px;margin-bottom: 0px">
                        ——${code.language}
                    </p>
                    <div class="money">
                        <span>￥${code.price}</span>
                    </div>
                    <%--防止功能描述过长--%>
                    <c:choose>
                        <c:when test="${f:length(code.function)>30}">
                            <p style="color:#8B8878;font-size: 13px;margin-top: 6px">
                                    ${f:substring(code.function,0,30)}...</p>
                        </c:when>
                        <c:otherwise>
                            <p style="color:#8B8878;font-size: 13px;margin-top: 6px">
                                    ${code.function}</p>
                        </c:otherwise>
                    </c:choose>
                    <p class="time">${code.public_time}</p>
                    <div class="management">
                        <a href="code_deleteCode.action?id=${code.id}">Delete</a>
                        <a href="code_ModifyCode.action?id=${code.id}">Modify</a>
                        <a href="code_ViewCode.action?id=${code.id}" target="_blank">View</a>
                    </div>
                </div>
            </c:forEach>
            <div class="pagepage">
                <div class="page_div"></div>
            </div>
        </div>
    </div>
    <div class="main-right" style="display: none" id="edit_person">
        <h3><span class="overview">Information Editing</span></h3>
        <div style="width: 600px;height: 1px;background-color: #BBBBBB;margin-top: 18px;"></div>
        <div>
            <form id="edit_form" action="user_update.action" method="post">
                <div class="repositories-1">
                    <p>Photo</p>
                    <label for="fileupload" class="updata_file">修改头像</label>
                    <input type="file" id="fileupload" style="display: none">
                </div>
                <div class="repositories-1">
                    <p>Username</p>
                    <input type="text" id="username" name="username" value="${user.username}"/>
                    <span id="error" style="margin-left: 15px;color: red;font-size: 18px"></span>
                </div>
                <div class="repositories-1">
                    <p>Sex</p>
                    <input type="radio" value="男" name="sex" id="sex1">Male
                    <input type="radio" value="女" name="sex" id="sex2" style="margin-left: 20px">Female
                </div>
                <div class="repositories-1">
                    <p>Age</p>
                    <input type="text" id="age" name="age" value="${user.age}" style="width: 50px;"/>
                </div>
                <div class="repositories-1">
                    <p>Age</p>
                    <input type="text" id="name" name="name" value="${user.name}">
                </div>
                <div class="repositories-1">
                    <p>Address</p>
                    <input type="text" id="address" name="address" value="${user.address}"
                           style="width: 300px;"/>
                </div>
                <div class="repositories-1">
                    <p>School</p>
                    <input type="text" id="school" name="school" value="${user.school}" style="width: 300px;"/>
                </div>
                <div class="repositories-1">
                    <p>Phone</p>
                    <input type="text" id="phone" name="phone" value="${user.phone}"/>
                </div>
                <div class="repositories-1">
                    <p>Email</p>
                    <input type="text" id="email" name="email" value="${user.email}"/>
                    <span id="error2" style="margin-left: 15px;color: red;font-size: 18px"></span>
                </div>
                <div class="repositories-1" style="height: 150px">
                    <p>Personal introduction</p>
                    <textarea id="self_info" name="self_info" rows="4" cols="80">${user.self_info}</textarea>
                </div>
                <div style="text-align:center">
                    <input type="submit" id="save" value="Save" class="activity-time" style="width: 150px;">
                    <input type="submit" id="back" value="Back" class="activity-time"
                           style="width: 150px;margin-left: 100px">
                </div>
            </form>
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
                window.location.href = "code_myModule?currentPage=" + num;
            }
        }
    )
    //点击编辑资料，进行跳转
    $("#edit_info").click(function () {
        $("#buy_person").fadeOut();
        $("#edit_person").fadeIn();
        $("#edit_info").attr("style", "text-decoration: none;text-align: center");
        return false;
    })
    //点击back返回
    $("#back").click(function () {
        $("#edit_person").fadeOut();
        $("#buy_person").fadeIn();
        return false;
    })

    //点击提交修改信息
    $("#save").click(function () {
        if ($("#error2").html() == "" && $("#error").html() == "") {
            $("#edit_form").submit();
        }
        return false;
    })

    //性别的判断
    if (${empty user.sex} || ${user.sex eq '男'}) {
        $("#sex1").attr("checked", "checked");
    } else {
        $("#sex2").attr("checked", "checked");
    }

    //上传头像
    $("#fileupload").change(function () {
        //注意这里不能写错。。。
        var file = $(this)[0].files[0];
        var formData = new FormData();
        formData.append("upload", file);
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
            url: "user_UserPicture.action",
            data: formData,
            dataType: "text",
            type: "post",
            contentType: false,
            processData: false,
            success: function (data) {
                $("#nophoto").fadeOut();
                $("#user_picture").attr("src", "upload/" + data);
            }
        })
    });

    //监听username和email的失去焦点
    $("#username").blur(function () {
        var username = $("#username").val();
        var re = /^[a-zA-Z_]{6,18}$/;
        if (username == "") {
            $("#error").html("Please input username.");
            return false;
        }
        else if (username.length < 6 || username.length > 18) {
            $("#error").html("Format error, length should be 6-18 characters");
            return false;
        }
        else if (!re.test(username)) {
            $("#error").html("Format error, only English letters and underscores");
            return false;
        }
        else {
            //用户名是否唯一的判断
            $.ajax({
                url: "sf_OneUname.action",
                data: {username: username},
                datatype: "text",
                success: function (data) {
                    if (data.toString() == "oneUname") {
                        $("#error").html("Username registered! Please re-enter");
                        return false;
                    } else {
                        $("#error").html("");
                        return true;
                    }
                }
            })
        }
    })
    //电子邮箱
    $("#email").blur(function () {
        var email = $("#email").val();
        var re = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
        if (email == "") {
            $("#error2").html("Please input email.");
            return false;
        } else if (!re.test(email)) {
            $("#error2").html("Email Format Input Error.");
            return false;
        } else {
            //邮箱是否唯一的判断
            $.ajax({
                url: "sf_OneUname.action",
                data: {username: email},
                datatype: "text",
                success: function (data) {
                    if (data.toString() == "oneUname") {
                        $("#error2").html("Email registered! Please re-enter");
                        return false;
                    } else {
                        $("#error2").html("");
                        return true;
                    }
                }
            })
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
