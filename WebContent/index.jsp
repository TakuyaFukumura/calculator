<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "dto.OldCalculatedData" %>
<% String display = (String)request.getAttribute("displayデータ"); %>
<% OldCalculatedData oldCalculatedData = (OldCalculatedData)session.getAttribute("最後の演算式"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>計算機</title>
		<link rel="stylesheet" type="text/css" href="design.css">
	</head>
	<body class="container">

		<form action="index" method="POST">
			<% if(display == null) display = "0"; %>
			<% if(oldCalculatedData != null) if(!oldCalculatedData.isErrorFlag()) display = "E"; %>
			<div class="display"><nobr><%= display %></nobr></div>

			<input type="hidden" name="display" value="<%= display %>">
			<table>
				<tr>
					<th><input type="submit" name="clickData" value="ＡＣ" class="btn-flat-border2"></th>
					<th></th>
					<th></th>
					<th><input type="submit" name="clickData" value="÷" class="btn-flat-border2"></th>

				</tr>
				<tr>
					<td><input type="submit" name="clickData" value="7" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="8" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="9" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="×" class="btn-flat-border2"></td>
				</tr>
				<tr>
					<td><input type="submit" name="clickData" value="4" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="5" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="6" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="-" class="btn-flat-border2"></td>
				</tr>
				<tr>
					<td><input type="submit" name="clickData" value="1" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="2" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="3" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="＋" class="btn-flat-border2"></td>
				</tr>
				<tr>
					<td><input type="submit" name="clickData" value="." class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="0" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="Ｃ" class="btn-flat-border"></td>
					<td><input type="submit" name="clickData" value="＝" class="btn-flat-border2"></td>
				</tr>
			</table>
		</form>

	</body>
</html>