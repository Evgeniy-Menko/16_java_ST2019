<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 31.10.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.input_file input[type=file]').change(function () {
                var t = $(this).val();
                if (t.indexOf('C:\\fakepath\\') + 1) t = t.substr(12);
                var e = $(this).next().find('.fake_file_input');
                e.val(t);
            });
            $('.clear_input').click(function () {
                var a = $(this).parent();
                var e = a.find('.fake_file_input');
                var t = a.find('input[type=file]');
                t.replaceWith('<input type="file" name="" >');
                e.val('');
            });
        });
    </script>
    <style>

        .input_file {
            position: relative;
            margin: 0 0 10px 0;
        }

        .input_file input[type=file] {
            opacity: 0;
            filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0);
            position: relative;
            z-index: 2;
            cursor: pointer;
            height: 50px;
            width: 600px;
        }

        .fake_file {
            position: absolute;
            z-index: 1;
            top: 0;
            left: 0;
        }

        .fake_file input[type=button] {
            border-radius: 3px;

            height: 50px;
            background-color: #000000;
            border: none;
            color: #fff;
            font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande", "Lucida Sans", Arial, sans-serif;
            font-size: 13px;
            cursor: pointer;
        }

        .fake_file_input {
            height: 50px;
            border: 1px solid #ccc;
            border-radius: 4px;

            position: relative;
            top: 2px;
            font-size: 20px;
            width: 210px;
        }

        .custom-dropdown {
            position: relative;
            display: inline-block;
            vertical-align: middle;
            background-color: #000000;
            margin-top: 1px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 31px;

            /* demo only */
        }

        .custom-dropdown select {
            cursor: pointer;
            background-color: #ffffff;
            color: #000000;
            font-size: medium;
            padding: 1em;
            padding-right: 12.22em;
            border: 1px;
            height: 50px;
            border-radius: 4px;
            border-color: #ccc;

            /* hide default arrow in chrome OSX */
        }

        .custom-dropdown::before, .custom-dropdown::after {
            content: "";
            position: absolute;
            pointer-events: none;
        }

        .custom-dropdown::after { /*  Custom dropdown arrow */
            content: "\25BC";
            height: 3em;
            font-size: .925em;
            line-height: initial;
            right: 0.6em;
            top: 50%;
            margin-top: -.5em;
        }

        .custom-dropdown::before { /*  Custom dropdown arrow cover */
            width: 2em;
            right: 0;
            top: 0;
            bottom: 0;
            border-radius: 0 3px 3px 0;
        }

        .custom-dropdown select[disabled] {
            color: rgb(0, 0, 0);
        }

        .custom-dropdown select[disabled]::after {
            color: rgb(0, 0, 0);
        }

        .custom-dropdown::before {
            background-color: rgb(51, 51, 51);
        }

        .custom-dropdown::after {
            color: rgb(255, 255, 255);
        }

        .btn {
              border-radius: 3px;

              height: 50px;
              background-color: #000000;
              border: none;
              color: #fff;
              font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande", "Lucida Sans", Arial, sans-serif;
              font-size: 14px;
              cursor: pointer;
              margin-left: 20%;
              margin-top: 5%;
          }

        .mobileTable {
            width: 500px;
            height: 500px;
            position: absolute;
            top: 50%;
            left: 47%;
            margin: -125px 0 0 -125px;
        }

        .h1 {
            font-size: 20px;
        }

        h1 {
            font-size: 50px;
            margin-left: 780px;
            margin-top: 8%;
        }
    </style>

    <title>Home</title>
</head>
<body>
<h1>Task 4 XMl</h1>
<span>${result}</span>
<div class="mobileTable">
    <span class="h1">Choose file</span>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/home?action=result" method="post">
        <div class="input_file">
            <input type="file" name="file">
            <div class="fake_file"><input type="text" class="fake_file_input" required>
                <input type="button" value=" Choose ">
            </div>
        </div>
        <span class="h1">Choose parser</span>
        <br>

        <span class="custom-dropdown big">
            <select name="parse" required>
                <option selected value="DOM">DOM</option>
                <option value="SAX">SAX</option>
                <option value="STAX">STAX</option>
            </select>
            </span>
        <br>
        <button class="btn">   Parse file  </button>

    </form>
</div>

</body>
</html>
