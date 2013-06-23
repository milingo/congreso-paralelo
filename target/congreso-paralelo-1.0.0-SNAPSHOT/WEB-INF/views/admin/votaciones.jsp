<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.congreso.model.Party" %>

<html>
<head>
</head>


<body>

	<h2>Crear Nueva Votación</h2>

	<form:form modelAttribute="votacionForm" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<!--legend>Crear Votación</legend-->

			<p>
			    <form:label for="title" path="title">Título</form:label>
			    <form:input path="title"/>
			    <form:errors path="title" element="p" class="text-error" />
			
			    <form:label for="comment" path="comment">Comentario</form:label>
				<form:textarea path="comment" />
				<form:errors path="comment" element="p" class="text-error" />

			    <c:set var="parties" value="<%= Party.values() %>"/>
			    <form:label for="selectedParties" path="selectedParties">Presentado por:</form:label>
			    <form:select path="selectedParties" multiple="true" id="partySelect" size="8">
			        <form:options items="${parties}" />
			    </form:select>
			    <form:errors path="selectedParties" element="p" class="text-error" />
			    
			    <form:label for="linkExpediente" path="linkExpediente">Link al Texto del expediente</form:label>
			    <form:input path="linkExpediente"/>
			    <form:errors path="linkExpediente" element="p" class="text-error" />
				
				<form:label for="linkSesion" path="linkSesion">Link a la sesión</form:label>
				<form:input path="linkSesion" />
				<form:errors path="linkSesion" element="p" class="text-error" />
				
				<form:label for="linkDetalleVotacion" path="linkDetalleVotacion">Link detallado a la votación</form:label>
				<form:input path="linkDetalleVotacion" />
				<form:errors path="linkDetalleVotacion" element="p" class="text-error" />
				
				<form:label for="xmlVotacion" path="xmlVotacion">Fichero XML</form:label>
				<form:input path="xmlVotacion" type="file" />
				<form:errors path="xmlVotacion" element="p" class="text-error" />
			</p>

			<button class="btn btn-large btn-success" type="submit">Crear</button>

		</fieldset>
	</form:form>

	<br />
	<br />
	<br />



	<h2>Votaciones</h2>

	<div>
		<table class="table table-hover">
			<c:if test="${not empty votaciones}">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Comentarios</th>
						<th>Sesion</th>
						<th>Votacion</th>
						<th>Fecha</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${votaciones}" var="votacion" varStatus="loop">
						<tr class="info"
							onclick="document.location = '${pageContext.request.contextPath}/admin/votacion/${votacion.id}';">
							<td>${votacion.title}</td>
							<td>${votacion.customComment}</td>
							<td>${votacion.sesion}</td>
							<td>${votacion.number}</td>
							<td><fmt:formatDate value="${votacion.date}" pattern="dd/MM/yyyy" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
	</div>


	<!--link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootswatch/2.3.0/spacelab/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css"-->
	<script src="js/bootstrap.min.js"></script>





</body>
</html>
