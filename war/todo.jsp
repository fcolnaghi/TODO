<%@ page language="java" contentType="text/html;"%>
<%@	page import="java.util.*" %>
<%@	page import="br.com.todo.model.Todo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html ng-app>
<head>
	<title>Create new To-Do</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body>
	<div id="wrapper" class="container">
		
		<br>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Criar novo To-Do <strong><%=request.getParameter("profile")%></strong></h3>
			</div>
			<div class="panel-body">
				<form method="post" action="todo">
					<input type="hidden" name="profile" value="<%=request.getParameter("profile")%>">
					<div class="form-group">
						<label for="title">Title</label>
						<input type="text" ng-require="true" class="form-control" id="title" name="title" placeholder="Título do TO-DO">
					</div>
					<div class="form-group">
						<label for="title">Description</label>
						<textarea ng-require="true" class="form-control" id="description" name="description" placeholder="Qual a atividade"></textarea>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-success pull-right" value="Adicionar To-Do">
					</div>
				</form>
			</div>
		</div>
		
		<hr>
	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Lista de TODOs</h3>
			</div>
			<div class="panel-body">
			
			<% 
				List<Todo> list = (List<Todo>) request.getAttribute("listaTodo");
	
				if(list != null && !list.isEmpty()) {
			%>
				<table class="table table-striped">
				<tr>
		          <th width="5%">#</th>
		          <th width="35%">Título</th>
		          <th width="50%">Descrição</th>
		          <th></th>
		        </tr>
			<%
					for(Todo todo : list) {
			%>
					<tr>
						<td><%=todo.getId()%></td>
						<td><%=todo.getTitle()%></td>
						<td><%=todo.getDescription()%></td>
						<td><a href="delete?id="<%=todo.getId()%> class="btn btn-danger pull-right">Excluir</a></td>
					</tr>
			<% 		}
				} else { %>
					<tr>
						<td colspan="4">Não existe TODO cadastrado!</td>
					</tr>
			<% 	} %>
			</table>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript" src="js/angular-1.3.12.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>
