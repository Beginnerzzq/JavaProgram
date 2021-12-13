<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            font-size: 13px;
            line-height: 24px;
            color: #969595;
            font-weight: 400;
            background: #f0f0f0;
            text-align: center;
        }


        .circle {
            width: 70px;
            height: 70px;
            border-radius: 50%;
            background: linear-gradient(145deg, #cacaca, #f0f0f0);
            box-shadow: 14px 14px 42px #868686,
                -14px -14px 42px #ffffff;
            text-align: center;
            position: relative;
            left: 40%;
        }
        .circle img{
            padding: 20%;
        }

        #bigBox {
            margin: 0 auto;
            /* login框剧中 */
            margin-top: 150px;
            /* login框与顶部的距离 */
            padding: 20px 50px;
            /* login框内部的距离(内部与输入框和按钮的距离) */
            /* login框背景颜色和透明度 */
            width: 400px;
            height: 300px;
            border-radius: 10px;
            /* 圆角边 */
            text-align: center;
            /* 框内所有内容剧中 */
            position: relative;
        }

        #bigBox h1 {
            color: rgb(143, 140, 140);
            /* LOGIN字体颜色 */
        }

        #bigBox .inputBox {
            margin-top: 50px;
            /* 输入框顶部与LOGIN标题的间距 */
        }

        #bigBox .inputBox .inputText {
            margin-top: 20px;
            /* 输入框之间的距离 */
        }

        #bigBox .inputBox .inputText span {
            color: rgb(143, 140, 140);
            /* icon颜色 */
        }

        #bigBox .inputBox .inputText input {
            border: 0;
            /* 删除输入框边框 */
            padding: 10px 10px;
            /* 输入框内的间距 */
            border-bottom: 1px solid rgb(143, 140, 140);
            /* 输入框白色下划线 */
            background-color: #00000000;
            /* 输入框透明 */
            color: rgb(143, 140, 140);
            /* 输入字体的颜色 */
        }

        #bigBox .loginButton {
            margin-top: 30px;
            /* 按钮顶部与输入框的距离 */
            width: 150px;
            height: 25px;
            color: white;
            /* 按钮字体颜色 */
            border: 0;
            /* 删除按钮边框 */
            border-radius: 20px;
            /* 按钮圆角边 */
            background-image: linear-gradient(to right, #b8cbb8 0%, #aab6aa 0%, #a69bac 0%, #bdb4bc 33%, #c9c9c9 66%, #b8cbb8 100%);
            /* 按钮颜色 */
        }

        #bigBox >p >a{
            color: rgb(143, 140, 140);
        }

        @font-face {
            font-family: "iconfont";
            src: url('iconfont.eot?t=1591106386397');
            /* IE9 */
            src: url('iconfont.eot?t=1591106386397#iefix') format('embedded-opentype'),
                /* IE6-IE8 */
                url('data:application/x-font-woff2;charset=utf-8;base64,d09GMgABAAAAAAPkAAsAAAAACCgAAAOVAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHEIGVgCDBgqEDIM4ATYCJAMMCwgABCAFhG0HPBsEB1GUDUqS7IckiWBsgSKJ5iIQA3MI4geKAwAAlgIQD19j7f3dvUMtQRJNHn26aJJEYoiEToIQxUIheSdEpnsms/x+7VRC3R1ElWSaNOmT7YvvDbzSSY3SaIQIoTG0rs5YPKzkCKMdmFhnZl8Ihekb28ETv33fz7o4nDbF5zc4pzmmRl2AcUABjb0swQJKkHuIV7u0w15PoF6fKeXl/dOLUktBuwXiMPBUqZVSKTXZoVaolowt4kxdbTpJfpz6349fEyEhqWS078rBni1tfpx/3fAd/w7XLgJ7PAP4SWSMKynEbqlpRzUMjauqV0W1+uSqIqSxvP//+CxNUDX/8EiCqKKZjWCYCNNJsW1WLL5Bzlmzb65zFXW4U/ImqTtRmqq8rTVL84YCiXK964MkBwBChhC37GSmaAaSZCIxivE4l2FIEkKCE4+PJJNjXMSMJhepAICiEKKrGWaMhIBoAYhLZOh2Bu7SzZGtAqIe0SYcGUlQiUgCUTVo/TnJjVvc95HI5Yi3TFFIQg4HkA28Mz+KSzM0xiMcdSST3vj0UzjDecQXy46LKNurEblFc0L3gGbB7yd2/6rYfSSsEoO2lVGueUc76Qd5FBr4XjBRXGr52LLO3SgfmellnsiUfeLOjkZD4cMQHi4vxEFvmweWxo5OZvix0VJYMQTybB2X8TbsGqooxPqHm3j4sdDQ2N6Fh17hMYUVnLuDgqKukpbs4WGWHRlWt5QUdhL9OpNfZny4NlGbb7prFux5BkrDntnR4Yvm72lYnjz7vL23GnzeXB3LWuqPt09ct8c9GsyblVq+Zb7y8lN/4+H2amVv2PFX2y3g/ef9DBVFu3RqJVqzfyG7ZUnRuFSdi8KyWYY7O/KbDAlkCDr/xNbur7+lSyk2hFqBjZDU6IWsVj+yYMehosEUVNWag3pjDiY36MCqotRh1AdAaPUFSbNXyFr9IAv2Fyq6/UNVa/Ch3lbo52wwGJudLkYlaEG/QGCylCytdILsM/QeYsVJro+7QnaBF+Zn5rLRA0yRxxjgHr0FEQLiLIF92AzjOIOcsxCNzPgi+cbsLJW9aMZkSaljCUOKQBbQXoCAkUmRW1l0cp8/gzwPYgrXFFXpryDmBLWDeTPmWiAPRGmromu5xHnkWSAEAYRlEmAfDCg2QxmQl7cKIUPM8HsEchtmSTlqK5qZX5I83yqoR4/JkSJHUXMcuEDH2NoLTJSqBEslAAAA') format('woff2'),
                url('iconfont.woff?t=1591106386397') format('woff'),
                url('iconfont.ttf?t=1591106386397') format('truetype'),
                /* chrome, firefox, opera, Safari, Android, iOS 4.2+ */
                url('iconfont.svg?t=1591106386397#iconfont') format('svg');
            /* iOS 4.1- */
        }

        .iconfont {
            font-family: "iconfont" !important;
            font-size: 16px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .icon-visible:before {
            content: "\e6a2";
        }

        .icon-nickname:before {
            content: "\e6a0";
        }
    </style>
</head>

<body>
	<form action="changePassword" method="post">
	    <div id="bigBox">
	            <div class="circle">
	                <img src="img/person.png" alt="" width="40px" height="40px">
	            </div>
	        <h1 id="username">欢迎你，${requestScope.user.getUsername()}</h1>
	        <div class="inputBox">
	        	<div class="inputText">
	                <span class="iconfont icon-nickname"></span>
	                <input type="text" name="username" placeholder="Your username" value="${requestScope.user.getUsername()}"/>
	            </div>
	            <div class="inputText">
	                <span class="iconfont icon-nickname"></span>
	                <input type="text" name="passwordFirst" placeholder="Your Password"/>
	            </div>
	            <div class="inputText">
	                <span class="iconfont icon-nickname"></span>
	                <input type="text" name="passwordSecond" placeholder="New Password"/>
	            </div>
	        </div>
	        <input class="loginButton" type="submit" value="Change" />
	        <p><a href="deleteUser.jsp">点此注销账号！</a></p>
	    </div>
    </form>
</body>

</html>