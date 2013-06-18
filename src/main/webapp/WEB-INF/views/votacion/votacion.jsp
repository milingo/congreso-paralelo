<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:useBean id="votacion" type="org.congreso.model.Votacion" scope="request" />

<% if (request.getAttribute("totalPublico") != null) {%>
   <jsp:useBean id="totalPublico" type="org.congreso.model.TotalPublico" scope="request" />  
<% } %>

<% if (request.getAttribute("principalAlreadyVoted") != null) {%>
   <jsp:useBean id="principalAlreadyVoted" type="java.lang.Boolean" scope="request" />  
<% } %>


<br/>


<div class="row-fluid">
    
 <div class="span10">
	<table class="table table-condensed">
		<c:if test="${not empty votacion.sesion && votacion.sesion > 0}">
		<tr>
			<td class="span2"><b>Sesión: </b></td>
			<td><a href='${votacion.linkSesion}'>${votacion.sesion}</a></td>
		</tr>
		</c:if>

        <c:if test="${not empty votacion.number && votacion.number > 0}">
		<tr>
			<td class="span2"><b>Votación: </b></td>
			<td><a href='${votacion.linkDetalleVotacion}'>${votacion.number}</a></td>
		</tr>
		</c:if>
		
        <c:if test="${not empty votacion.linkExpediente}">
		<tr>
			<td class="span2"><a href='${votacion.linkExpediente}'><b>Expediente</b></a></td>
			<td></td>
		</tr>
		</c:if>
		
		<c:if test="${not empty votacion.parties}">
		<tr>
			<td class="span2"><b>Partidos: </b></td>
			<td>
			<c:forEach items="${votacion.parties}" var="party" varStatus="loop">
				<c:if test="${loop.last}">
					<%-- <img src="${pageContext.request.contextPath}/resources/img/${party}.jpg" width="30" height="30" class="img-rounded">  --%>
			        ${party} 
				</c:if>
				<c:if test="${not loop.last}">
					<%-- <img src="${pageContext.request.contextPath}/resources/img/${party}.jpg" width="30" height="30" class="img-rounded">  --%>
			        ${party},   
				</c:if>
		    </c:forEach>
		    </td>
		</tr>
		</c:if>

        <c:if test="${not empty votacion.date}">
		<tr>
			<td class="span2"><b>Fecha: </b></td>
			<td><fmt:formatDate value="${votacion.date}" pattern="dd/MM/yyyy" /></td>
		</tr>
		</c:if>
	</table>
	
	${votacion.titulo}
	<blockquote>${votacion.customComment}</blockquote>

    <table class="table table-borderless">
		<tr>
		    <c:if test="${votacion.showVotesDiputados || votacion.challenge.status == 'CLOSED'}">
		        <c:if test="${votacion.votes.presentes > 0}">
			        <td class="span5" id="diputados_chart_div_${votacion.id}"></td>
			    </c:if>
			</c:if>
			
			<c:if test="${votacion.showVotesPublic || votacion.challenge.status == 'CLOSED'}">
			    <c:if test="${totalPublico.yes > 0 || totalPublico.no > 0 || totalPublico.abs > 0}">
			        <td class="span5" id="publico_chart_div_${votacion.id}"></td>
<%-- 			        <td class="span5">Número de votantes: ${totalPublico.yes + totalPublico.no + totalPublico.abs}</td> --%>
			    </c:if>
			</c:if>
		</tr>
		<tr>
		      <td class="span5">
		      <c:if test="${votacion.showVotesDiputados || votacion.challenge.status == 'CLOSED'}">
		          <c:if test="${votacion.votes.presentes > 0}">
		              #diputados: ${votacion.votes.presentes}
		          </c:if>
		       </c:if>
		       </td>
		       <td class="span5">
		       <c:if test="${votacion.showVotesPublic || votacion.challenge.status == 'CLOSED' || votacion.challenge.status == 'OPENED'}">
			      <c:if test="${totalPublico.yes > 0 || totalPublico.no > 0 || totalPublico.abs > 0}">
		              #votantes: ${totalPublico.yes + totalPublico.no + totalPublico.abs}
		          </c:if>
		       </c:if>
		       </td>
		</tr>
	</table>
	
</div>









<div class="span2">

   <c:if test="${votacion.challenge.status == 'OPENED'}">
   
      <%-- <security:authorize access="isAuthenticated()"> --%>
      <c:if test="${not principalAlreadyVoted}">
	       <h4>Tu Voto</h4>
           <form:form class="form" method="post" modelAttribute="voteForm" action="${pageContext.request.contextPath}/votacion/${votacion.id}/vote">
<%-- 	<form:input path="name" class="input-block-level" placeholder="Nombre" /> --%>
<%-- 	<form:errors path="name" element="p" class="text-error"/>  --%>
<%--     <form:input path="surname" class="input-block-level" placeholder="Apellido" /> --%>
<%-- 	<form:errors path="surname" element="p" class="text-error"/>  --%>
<%-- 	<form:input path="email" class="input-block-level" placeholder="Email" />  --%>
<%-- 	<form:errors path="email" element="p" class="text-error"/>  --%>
<%-- 	<form:input path="postalCode" class="input-block-level" placeholder="Código Postal" />  --%>
<%-- 	<form:errors path="postalCode" element="p" class="text-error"/>  --%>
				<label class="radio">
			        <input type="radio" name="vote" id="yes" value="yes">
			         Sí
			    </label>
			    <label class="radio">
			         <input type="radio" name="vote" id="no" value="no">
			         No
			    </label>
			    <label class="radio">
			         <input type="radio" name="vote" id="abs" value="abs">
			         Abstención
			    </label>
			
				<button class="btn btn-primary" type="submit" id="vota">Vota</button>
          </form:form>
      </c:if>
      
      <c:if test="${principalAlreadyVoted}">
         Gracias por tu voto!
      </c:if>
      <%--	</security:authorize> --%>

   </c:if> <%-- OPENED --%>

   <c:if test="${votacion.challenge.status == 'CLOSED'}">
      La votación está cerrada. Éstos son los resultados finales.
   </c:if> <%-- CLOSED --%>
	
</div>
    
    
    
    
    
    
    
    
    
    
    
    
</div>




<!--table class="table table-condensed">
	<c:if test="${not empty votosDiputados}">
		<c:forEach items="${votosDiputados}" var="voto" varStatus="loop">
			<tr>
				<td>${voto.diputado.name}</td>
				<td>${voto.diputado.seat}</td>
				<td>${voto.voto}</td>
			</tr>
		</c:forEach>
	</c:if>
</table-->



<!--script type="text/javascript"
	src="<c:url value="/resources/js/protovis/protovis.min.js" />"></script>
<script type="text/javascript+protovis">
  new pv.Panel()
      .width(150)
      .height(150)
    .anchor("center").add(pv.Label)
      .text("Hello, world!")
    .root.render();
</script>


<script type="text/javascript"
	src="<c:url value="/resources/js/graphael/raphael-min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/graphael/g.pie-min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/graphael/g.raphael-min.js" />"></script>


<script type="text/javascript">
	var r = Raphael(10, 50, 640, 480);
	r.piechart(320, 240, 100, [ 55, 20, 13, 32, 5, 1, 2 ]);
</script-->


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

	// Load the Visualization API and the piechart package.
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(drawChartDiputados);
	google.setOnLoadCallback(drawChartPublico);

	
	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChartDiputados() {
		
		var aFavor = parseInt('${votacion.votes.aFavor}', 10);
		var enContra = parseInt('${votacion.votes.enContra}', 10);
		var abstenciones = parseInt('${votacion.votes.abstenciones}', 10);
		var noVotan = parseInt('${votacion.votes.noVotan}', 10);

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Voto');
		data.addColumn('number', 'Porcentaje');
		data.addRows([ [ 'Si', aFavor ], [ 'No', enContra ],
				[ 'Abstenciones', abstenciones ], [ 'No Votan', noVotan ] ]);

		// Set chart options
		var options = {
			'title' : 'Diputados',
			'width' : 0,
			'height' : 0,
			//'colors' : [ '#596326', '#871917', '#fcd994', '#c73730' ],
		    'backgroundColor' : '#eeeeee',
		    'chartArea' : {left:20,top:40,width:300,height:300}
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document
				.getElementById('diputados_chart_div_${votacion.id}'));
		chart.draw(data, options);
	}
	
	
	
	
	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChartPublico() {
		
		var aFavor = parseInt('${totalPublico.yes}', 10);
		var enContra = parseInt('${totalPublico.no}', 10);
		var abstenciones = parseInt('${totalPublico.abs}', 10);

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Voto');
		data.addColumn('number', 'Porcentaje');
		data.addRows([ [ 'Si', aFavor ], [ 'No', enContra ],
				[ 'Abstenciones', abstenciones ] ]);

		// Set chart options
		var options = {
			'title' : 'Ciudadanos',
			'width' : 0,
			'height' : 0,
			//'colors' : [ '#596326', '#871917', '#fcd994', '#c73730' ],
		    'backgroundColor' : '#eeeeee',
		    'chartArea' : {left:20,top:40,width:300,height:300}
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document
				.getElementById('publico_chart_div_${votacion.id}'));
		chart.draw(data, options);
	}

	
	window.onload = function() {
		if($("#yes").attr('checked') || $("#no").attr('checked') || $("#abs").attr('checked')) {
			$("#vota").attr('disabled', false);	
		} else {
			$("#vota").attr('disabled', true);
		}
	};
	
	document.getElementById('yes').addEventListener('click', function () {
		$("#vota").attr('disabled', false);
	});
	document.getElementById('no').addEventListener('click', function () {
		$("#vota").attr('disabled', false);
	});
	document.getElementById('abs').addEventListener('click', function () {
		$("#vota").attr('disabled', false);
	});
	
</script>




