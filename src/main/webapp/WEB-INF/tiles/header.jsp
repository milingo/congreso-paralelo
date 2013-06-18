<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
			</a> 
			<a class="brand" href='<s:url value="/"></s:url>'>El congreso paralelo</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href='<s:url value="/"></s:url>'>Votaciones</a></li>
					<security:authorize access="hasAnyRole('ROLE_ADMIN')">
					    <li><a href='<s:url value="/admin/votaciones"></s:url>'>Admin</a></li>
					</security:authorize>
				</ul>					
				<ul class="nav pull-right">
					<security:authorize access="!isAuthenticated()">
					    <li><a href='<s:url value="/signup"/>'> Sign Up </a>
						<li><a href='<s:url value="/signin"/>'> Sign in </a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li><a href='<s:url value="/logout"></s:url>'>Logout (<security:authentication property="principal.username"/>)</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>