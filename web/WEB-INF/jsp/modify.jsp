<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <title>Modify codeModule</title>
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
<div class="center" style=" margin-top: 100px;margin-left: 420px;width: 1000px;height: 1270px;">
    <form id="form_build" action="code_ModifyCodeModule.action" method="post" enctype="multipart/form-data">
        <h3><span class="overview">Modify codeModule</span></h3>
        <div style="width: 600px;height: 1px;background-color: #BBBBBB;margin-top: 18px;"></div>
        <div>
            <div class="repositories-1">
                <p>Code Module Name:</p>
                <input type="text" id="code_name" name="code_name" style="width: 200px" placeholder="code_name"
                       value="${codeModule.code_name}">
            </div>
            <div class="repositories-1">
                <p>Language:</p>
                <select id="code_type" name="language">
                    <option value="C">C</option>
                    <option value="Java">Java</option>
                    <option value="Python">Python</option>
                    <option value="C++">C++</option>
                    <option value="Php">Php</option>
                    <option value="Go">Go</option>
                    <option value="前端">前端</option>
                </select>
            </div>
            <div class="repositories-1">
                <p>Keyword:</p>
                <div class="keyword">
                    <input type="text" name="keyword1" value="${codeModule.keyword1}" placeholder="keyword1">
                    <input type="text" name="keyword2" value="${codeModule.keyword2}" placeholder="keyword2">
                    <input type="text" name="keyword3" value="${codeModule.keyword3}" placeholder="keyword3">
                    <input type="text" name="keyword4" value="${codeModule.keyword4}" placeholder="keyword4">
                </div>
            </div>
            <div class="repositories-1">
                <p>Price:</p>
                <input type="text" id="price" name="price" style="width: 100px" placeholder="price"
                       value="${codeModule.price}">&nbsp;&nbsp;Yuan
            </div>
            <div class="repositories-1" style="height:150px">
                <p>Function:</p>
                <textarea id="function" name="function" style="height: 80px;width: 550px;resize: none;"
                          placeholder="Please input function...">${codeModule.function}</textarea>
            </div>
            <div class="repositories-1" style="height:400px">
                <p>Detailed introduction: </p>
                <textarea id="info" name="info" style="height: 330px;width: 550px;resize: none;"
                          placeholder="Please input detailed introduction...">${codeModule.info}</textarea>
            </div>
        </div>
        <div class="repositories-1">
            <p>Code File:</p>
            <label for="fileupload" class="updata_file">Upload File</label>
            <input type="file" id="fileupload" name="upload" style="display: none">
            <span style="margin-left: 120px;color: #3F51B5" id="filename">${codeModule.code_file}</span>
        </div>

        <div style="display:inline-block;margin-top: 20px">
            <input type="submit" class="save" style="width:200px;margin-left: 55px" value="Submit" id="saveCode">
            <input type="reset" class="save" value="Back" id="back"
                   style="margin-left:90px;;background-color:#6A726A;width:200px;">
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

    //监听Back按钮
    $("#back").click(function () {
        window.location.href = "code_myModule.action";
    })

    //显示文件名
    $("#fileupload").change(function () {
        var file = $(this)[0].files[0];
        $("#filename").html(file.name);
    })

    //监听按钮提交表单
    $("#saveCode").click(function () {
        var file = $("#fileupload")[0].files[0];
        console.log(file);
        if ($("#code_name").val() == "") {
            alert("Please enter your name first");
        } else if ($("#keyword").val() == "") {
            alert("At least one keyword is required");
        } else if ($("#price").val() == "") {
            alert("The price cannot be empty");
        } else if ($("#function").val() == "") {
            alert("The function description cannot be empty");
        } //对文件大小验证
        else if (file != undefined && (file.size / 1024) > 2048) {
            alert("The maximum size of the uploaded file is 2M");
        } else if ($("#info").val() == "") {
            alert("The description cannot be empty");
        } else {
            $("#form_build").submit();
        }
        return false;
    })

    //数据的显示
    $("#code_type").val("${codeModule.language}");

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