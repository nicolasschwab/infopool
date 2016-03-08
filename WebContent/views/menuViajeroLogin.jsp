<%@ page language="java" import="model.Usuario"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li><a href="#" onclick="javascript: mostrarNotificaciones()"><i class="fa fa-bell-o"></i><span id="circulo" class="circulo"></span></a></li>
<div id="notificaciones">
	<div class="flechaArriba"></div>
	<div id="header">Notificaciones</div>
	<div id="bodyNotificaciones"></div>
	<div class="footer">Ver Todas</div>
</div>							
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		<img src="resources/images/user.png" alt="" class="img-perfil"> <%= ((Usuario)session.getAttribute("usrLogin")).getUsuario() %> <span class="caret"></span>
	</a>
	<ul class="dropdown-menu">
		<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>>
			<s:url var="verPerfilViajero" action="verPerfilViajero">
				<s:param name="id" value="%{user.id}"></s:param>
			</s:url>
			<s:a href="%{verPerfilViajero}"><span class="fa fa-user" aria-hidden="true"> </span> <s:text name="menuviajero.perfil" /></s:a>
		</li>
		<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%>>
			<a href="misViajes"><span class="fa fa-road" aria-hidden="true"> </span> <s:text name="menuviajero.viajes" /></a>
		</li>
		<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%>>
			<a href="detalle"><span class="fa fa-comment" aria-hidden="true"> </span> <s:text name="menuviajero.mensajes" /></a>
		</li>												
		<li><a href='cerrarSesion' class="salir"><s:text name="login.salir" /></a></li>
	</ul>
</li>