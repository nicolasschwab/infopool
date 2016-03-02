<%@ page language="java" import="model.Usuario"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="navbar" class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<% if  (session.getAttribute("usrLogin") != null){  %>
			<% if  (((Usuario)session.getAttribute("usrLogin")).soyAdministrador()){ %>
				<%@ include file="menuAdministrador.jsp"%>
			<% } else{%>
				<%@ include file="menuViajero.jsp"%>
			<% }
           } else{%>								
			<jsp:include page="menuVisitante.jsp">
				<jsp:param name="itemActivo" value="${param.itemActivo}"/>				
			</jsp:include>		
		<% }%>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<% if (session.getAttribute("usrLogin") == null){  %>
			<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 87) {%> class="active" <%}%>><a href='iniciarSesion'><s:text name="login.ingresar" /></a></li>
			<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 88) {%> class="active" <%}%>><a href='registrarUsuario'><s:text name="login.registrarse" /></a></li>
		<% } else{ %>	
			<% if  (((Usuario)session.getAttribute("usrLogin")).soyAdministrador()){ %>
				<%@ include file="menuAdministradorLogin.jsp"%>
			<% } else{%>
				<%@ include file="menuViajeroLogin.jsp"%>
			<% }
		   } %>				
	</ul>
</div>