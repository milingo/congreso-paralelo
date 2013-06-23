<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<jsp:useBean id="votacion" type="org.congreso.model.Votacion"
	scope="request" />

<%
    if (request.getAttribute("totalPublico") != null) {
%>
<jsp:useBean id="totalPublico" type="org.congreso.model.TotalPublico"
	scope="request" />
<%
    }
%>

<%
    if (request.getAttribute("principalAlreadyVoted") != null) {
%>
<jsp:useBean id="principalAlreadyVoted" type="java.lang.Boolean"
	scope="request" />
<%
    }
%>


<br />

<div class="container-fluid">
	<div class="row-fluid">

		<div class="span2">
			<img
				src="${pageContext.request.contextPath}/resources/img/transparency.png"
				class="img-rounded">
		</div>

		<div class="span10">
			<div class="row">
				<h3>
					<b>${votacion.title}</b>
				</h3>
			</div>

			<div class="row">
				<c:if test="${not empty votacion.linkExpediente}">
					<div class="span2">
						<a href='${votacion.linkExpediente}'><b>Expediente</b></a>
					</div>
				</c:if>
				<c:if test="${not empty votacion.sesion && votacion.sesion > 0}">
					<div class="span2">
						 <b>Sesión </b><a href='${votacion.linkSesion}'>${votacion.sesion}</a>
					</div>
				</c:if>
				<c:if test="${not empty votacion.number && votacion.number > 0}">
					<div class="span2">
						  <b> Votación </b><a href='${votacion.linkDetalleVotacion}'>${votacion.number}</a>
					</div>
				</c:if>
				<c:if test="${not empty votacion.date}">
					<div class="span3">
						<b>Fecha: </b>
						<fmt:formatDate value="${votacion.date}" pattern="dd/MM/yyyy" />
					</div>
				</c:if>
			</div>
			
			<div class="row">
				<c:if test="${not empty votacion.parties}">
					<div class="span6">
						<b>Partidos: </b>
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
					</div>
				</c:if>
			</div>
		</div>

		<c:if
			test="${votacion.showVotesDiputados || votacion.challenge.status == 'CLOSED'}">
			<div class="row">
				<div class="span12">
					<c:if test="${votacion.votes.presentes > 0}">
						<c:if test="${votacion.votes.aFavor > votacion.votes.enContra}">
							<div class="span6">
								<b class="text-success">APROBADA</b> por los diputados
							</div>
						</c:if>
						<c:if test="${votacion.votes.aFavor <= votacion.votes.enContra}">
							<div class="span6">
								<b class="text-error">RECHAZADA</b> por los diputados
							</div>
						</c:if>
					</c:if>
				</div>
			</div>
		</c:if>

		<c:if test="${votacion.challenge.status == 'CLOSED'}">
			<div class="row">
				<div class="span12">
					<c:if
						test="${totalPublico.yes > 0 || totalPublico.no > 0 || totalPublico.abs > 0}">
						<c:if test="${totalPublico.yes > totalPublico.no}">
							<div class="span6">
								<b class="text-success">APROBADA</b> por los ciudadanos
							</div>
						</c:if>
						<c:if test="${totalPublico.yes <= totalPublico.no}">
							<div class="span6">
								<b class="text-error">RECHAZADA</b> por los ciudadanos
							</div>
						</c:if>
					</c:if>
				</div>
			</div>
		</c:if>

		<c:if test="${votacion.challenge.status == 'OPENED'}">
			<div class="row">
				<div class="span12">
					<span class="badge badge-success">Votación Abierta</span>
					<c:if
						test="${totalPublico.yes > 0 || totalPublico.no > 0 || totalPublico.abs > 0}">
		              Ya hay ${totalPublico.yes + totalPublico.no + totalPublico.abs} votantes
		            </c:if>
				</div>
			</div>
		</c:if>

		<c:if test="${votacion.challenge.status == 'CLOSED'}">
			<div class="row">
				<div class="span12">
					<span class="badge badge-inverse">Votación Cerrada</span>
					<c:if
						test="${totalPublico.yes > 0 || totalPublico.no > 0 || totalPublico.abs > 0}">
		              con ${totalPublico.yes + totalPublico.no + totalPublico.abs} votantes
		            </c:if>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="span12">${votacion.customComment}</div>
		</div>


		<div class="span2 offset10">
			<form:form class="form" method="get" action="votacion/${votacion.id}">
				<button class="btn-large btn-primary" type="submit"
					id="goToVotacion">Participa</button>
			</form:form>
		</div>

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
			'chartArea' : {
				left : 20,
				top : 40,
				width : 300,
				height : 300
			}
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
			'chartArea' : {
				left : 20,
				top : 40,
				width : 300,
				height : 300
			}
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document
				.getElementById('publico_chart_div_${votacion.id}'));
		chart.draw(data, options);
	}
</script>




