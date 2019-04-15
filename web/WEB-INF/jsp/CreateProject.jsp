<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<html lang="en">
<head>
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
<div class="createProject">
    <div class="create_top">
        <span style="border-bottom: #E02B31 2px solid;">${user.username}</span>
        <span>/</span>
        <span class="repertoryname">${repertory.name}</span>
    </div>
    <div class="create_second">
        <div><> Code</div>
        <div>Description</div>
        <div>Code Resources</div>
        <div>Code Edit</div>
        <div>Other instructions</div>
    </div>
    <div class="create_thrid">
        <span>No description, website, or topics provided.</span>
        <div>Add a topic</div>
    </div>
    <div class="create_four clearfix">
        <div class="four_left">
            <p>Description</p>
            <textarea placeholder="Add a detailed description" class="form-control"
                      id="info">${repertory.info}</textarea>
        </div>
        <div class="four_right">
            <p>Website</p>
            <textarea placeholder="Website for this repository(optional)" class="form-control"></textarea>
            <button class="btn savebtn">Save</button>
            <span>or</span>
            <button class="btn cancelbtn">Cancel</button>
        </div>
    </div>
    <div class="create_five clearfix">
        <p>Code Resources</p>
        <div class="five_left">
            <c:forEach items="${threeModule}" var="th">
                <div class="onth">
                        <%--截取一下名字过长--%>
                    <c:choose>
                        <c:when test="${f:length(th.code_name)>7}">
                            <span>${f:substring(th.code_name,0,7)}...</span>
                        </c:when>
                        <c:otherwise>
                            <span>${th.code_name}</span>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${f:length(th.keyword1)>5}">
                            <span>${f:substring(th.keyword1,0,5)}...</span>
                        </c:when>
                        <c:otherwise>
                            <span>${th.keyword1}</span>
                        </c:otherwise>
                    </c:choose>
                    <span>${th.language}</span>
                    <a class="btn more" href="code_ViewCode.action?id=${th.id}" target="_blank">more</a>
                    <button class="btn use" id="${th.id}">use</button>
                </div>
            </c:forEach>
            <a class="btn resourse" href="code_myModule.action" target="_blank">More code resources</a>
            <a class="btn buy" href="code_market.action" target="_blank">Buy more codes</a>
        </div>
        <div class="five_right">
            <div>
                <p>Function type</p>
                <select class="form-control">
                    <option value="All" selected="selected">1&nbsp;&nbsp;&nbsp;All</option>
                    <option value="1">2&nbsp;&nbsp;&nbsp; Machine</option>
                    <option value="2">3&nbsp;&nbsp;&nbsp;Engineering</option>
                    <option value="3">4&nbsp;&nbsp;&nbsp; Artificial intelligence</option>
                </select>
            </div>
            <div>
                <p>Language</p>
                <select class="form-control" id="language">
                    <option value="All">1&nbsp;&nbsp;&nbsp;All</option>
                    <option value="C">2&nbsp;&nbsp;&nbsp; C</option>
                    <option value="Java">3&nbsp;&nbsp;&nbsp;Java</option>
                    <option value="Python">4&nbsp;&nbsp;&nbsp;Python</option>
                </select>
            </div>
            <div style="margin-bottom: 6px">
                <p>Label</p>
                <input type="text" placeholder="Add a label for your repository" class="form-control">
            </div>
        </div>
    </div>
    <div class="create_six">
        <p style="display: inline-block">Code Edit</p>
        <label for="upload" id="uploadlabel">选择文件</label>
        <input type="file" id="upload" style="display: none" webkitdirectory>
        <span style="margin: 0 40px" id="filenums"></span>
        <button style="background: #B4B3B2;display: none" id="uploadbutton">确认上传</button>
        <span style="margin: 0 40px;color: red" id="success"></span>
        <div class="form-control" id="filetext">
            <c:forEach items="${threeModule}" var="th2">
                <div class="${th2.id}" id="${th2.code_file}">
                    <span>${th2.code_name}</span>
                    <button class="remove">Remove</button>
                    <a href="code_ViewCode.action?id=${th2.id}" target="_blank">
                        <button>Test</button>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="create_seven clearfix">
        <p>Other instructions</p>
        <textarea style="height: 200px" placeholder="Add instructions" class="form-control"></textarea>
        <button class="upload" id="save">Upload</button>
        <button class="cancel">Cancel</button>
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

        var files = [];

        //语言分类检索的数据显示
        var mark = "<s:property value="#parameters.type"/>";
        if (mark == "") {
            $("#language").val("All");
        } else {
            $("#language").val(mark);
        }

        //语言分类检索
        $("#language").change(function () {
            var language = $(this).val();
            if (language != "All") {
                window.location.href = "code_CreateProject2.action?type=" + language;
            } else {
                window.location.href = "code_CreateProject2.action";
            }
        })

        //监听使用该代码模块的按钮
        $(".onth").on("click", "button", function () {
            //拿到当前id
            var id = $(this).attr("id");
            //添加的显示
            $("." + id).show();
        })

        //监听remove
        $("#filetext").on("click", ".remove", function () {
            $(this).parent().hide();
        })

        //上传文件夹
        $("#upload").change(function () {
            //获取文件夹，以及文件夹中的文件数量
            files = $(this)[0].files;
            var len = files.length;
            //超过50个文件，不予处理
            if (len > 50) {
                alert("Upload a maximum of 50 files！！")
                return;
            }
            //文件超过5M则不处理
            for (var i = 0; i < len; i++) {
                //对每个文件大小做限制
                if ((files[i].size / 1024) > 5120) {
                    alert("The maximum size of a single file is 5M！！")
                    return;
                }
            }
            //显示信息
            $("#filenums").html(len + "个文件");
            $("#uploadbutton").show();
        })

        //确认上传按钮监听
        $(".create_six").on("click", "#uploadbutton", function () {
            var formdata = new FormData();
            //遍历文件夹中的所有文件
            for (var i = 0; i < files.length; i++) {
                //每个文件都放到formdata中
                formdata.append("uploadfile", files[i]);
            }

            //上传给服务器
            $.ajax({
                url: "code_uploadFiles.action",
                type: "POST",
                data: formdata,
                contentType: false,
                processData: false,
                success: function () {
                    //隐藏和信息回显
                    $("#uploadbutton").hide();
                    $("#success").html("上传成功");
                }
            });
        })

        //保存仓库
        $("#save").click(function () {
            var info = $("#info").val();
            //描述信息缺失
            if (info == "") {
                alert("Lack of descriptive information");
                return;
            }
            //文件没有上传
            if ($("#uploadbutton").is(":visible")) {
                alert("You have files not uploaded");
                return;
            }
            var codefiles = [];
            $.each($("#filetext div"), function (i, obj) {
                if ($(this).is(":visible")) {
                    var codefile = $(this).attr("id");
                    codefiles.push(codefile);
                }
            })

            //仓库至少需要一个文件
            if (codefiles.length == 0 && $("#filenums").html() == "") {
                alert("The repository needs at least one file");
                return;
            }

            //发送给服务器
            $.ajax({
                url: "code_Saverep.action",
                type: "POST",
                data: {infos: info, codefiles: codefiles},
                dataType: "text",
                traditional: true,
                success: function () {
                    window.location.href = "code_project.action";
                }
            });
        })

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

    })

</script>
</body>
</html>