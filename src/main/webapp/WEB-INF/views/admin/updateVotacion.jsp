<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.congreso.model.Party" %>


<a href='<s:url value="/admin/votaciones" />'> Votaciones </a>

<br />
<br />

<h2>Modificar</h2>

<form:form modelAttribute="votacionForm" method="post"
	action="${votacion.id}/update" enctype="multipart/form-data" >
	<fieldset>
		<!--legend>Crear Votación</legend-->

		<p>
		    <form:label for="title" path="title">Título</form:label>
			<form:input path="title" value="${votacion.title}" />
		    <form:errors path="title" element="p" class="text-error" />
		    
		    <form:label for="comment" path="comment">Comentarios</form:label>
			<form:textarea path="comment"/>
			<form:errors path="comment" element="p" class="text-error" />
			    
		    <c:set var="parties" value="<%= Party.values() %>"/>
			<form:label for="selectedParties" path="selectedParties">Presentado por:</form:label>
			<form:select path="selectedParties" multiple="true" id="partySelect" size="8">
			    <form:options items="${parties}" />
			</form:select>
			<form:errors path="selectedParties" element="p" class="text-error" />
			
			<form:label for="linkExpediente" path="linkExpediente">Link al Texto del expediente</form:label>
			<form:input path="linkExpediente" value="${votacion.linkExpediente}" />
			<form:errors path="linkExpediente" element="p" class="text-error" />
				
			<form:label for="linkSesion" path="linkSesion">Link a la sesión</form:label>
			<form:input path="linkSesion" value="${votacion.linkSesion}" />
			<form:errors path="linkSesion" element="p" class="text-error" />
			
			<form:label for="linkDetalleVotacion" path="linkDetalleVotacion">Link detallado a la votación</form:label>
			<form:input path="linkDetalleVotacion" value="${votacion.linkDetalleVotacion}" />
			<form:errors path="linkDetalleVotacion" element="p" class="text-error" />
			
			<form:label for="xmlVotacion" path="xmlVotacion">Fichero XML</form:label>
		    <form:input path="xmlVotacion" type="file" />
			<form:errors path="xmlVotacion" element="p" class="text-error" />
		</p>
		
		
		<c:set var="visible" value=""/>
		<c:set var="showVotesDiputados" value=""/>
		<c:set var="showVotesPublic" value=""/>
		<c:if test="${votacion.visible}">
			    <c:set var="visible" value="${votacion.visible}"/>
	    </c:if>
		<c:if test="${votacion.showVotesDiputados}">
	            <c:set var="showVotesDiputados" value="${votacion.showVotesDiputados}"/>
	    </c:if>
		<c:if test="${votacion.showVotesPublic}">
			    <c:set var="showVotesPublic" value="${votacion.showVotesPublic}"/>
		</c:if>
		
		
		<table class="table table-condensed">
			<tr>
				<td><form:checkbox path="visible" checked="${visible}" /></td>
				<td>Visible</td>
			</tr>
			<tr>
				<td><form:checkbox path="showVotesDiputados"
						checked="${showVotesDiputados}" /></td>
				<td>Enseñar datos de diputados en cuanto estén disponibles (o
					esperar a que se cierre la votación pública)</td>
			</tr>
			<tr>
				<td><form:checkbox path="showVotesPublic"
						checked="${showVotesPublic}" /></td>
				<td>Enseñar datos del público en cuanto estén disponibles (o
					esperar a que se cierre la votación pública)</td>
			</tr>
		</table>

		<button class="btn btn-success" type="submit">Modificar</button>

	</fieldset>
</form:form>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$("#partySelect option").each(function () {
	  if ('${votacion.parties}'.indexOf($(this).text()) > 0) {
	    $(this).attr('selected', true)
	  }
	});
</script>




<h3>Votación Pública</h3>

<c:if test="${not empty votacion.challenge}">
	<table class="table table-condensed">
		<tr>
			<td>Estado:</td>
			<td>${votacion.challenge.status}</td>
			<td><c:if test="${votacion.challenge.status == 'INACTIVE'}">
					<form:form method="post" action="${votacion.id}/challenge/open">
						<button class="btn btn btn-info" type="submit">Abrir</button>
					</form:form>
				</c:if> 
				<c:if test="${votacion.challenge.status == 'OPENED'}">
					<form:form method="post" action="${votacion.id}/challenge/close">
						<button class="btn btn btn-info" type="submit">Cerrar</button>
					</form:form>
					<form:form method="post" action="${votacion.id}/challenge/inactive">
						<button class="btn btn btn-info" type="submit">Desactivar</button>
					</form:form>
				</c:if> 
				<c:if test="${votacion.challenge.status == 'CLOSED'}">
					<form:form method="post" action="${votacion.id}/challenge/reopen">
						<button class="btn btn btn-info" type="submit">Reopen</button>
					</form:form>
				</c:if></td>
		</tr>
	</table>
</c:if>



<h3>Borrar</h3>

<form:form method="post" action="${votacion.id}/delete" onsubmit="return confirm('Estas seguro de querer borrar esta votación?');">
	<button class="btn btn btn-danger" type="submit">Borrar</button>
</form:form>

<br />
<br />
<br />
<br />




<h2>Preview</h2>

<!--jsp:include page="votacion.jsp"-->
<!--jsp:param name="votacion" value="${votacion}" /-->
<!--jsp:param name="votosDiputados" value="${votes}" /-->
<!--/jsp:include-->

<!--c:set var="votacion" value="${votacion}" scope="request" /-->
<!--c:set var="votosDiputados" value="${votosDiputados}" scope="request" /-->
<!--jsp:include page="votacion.jsp"/-->


<jsp:include page="../votacion/votacion.jsp" />





