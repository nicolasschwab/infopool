<%@ taglib prefix="s" uri="/struts-tags"%>

<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%> class="active" <%}%>>
	<a href="Inicio"><span class="fa fa-home" aria-hidden="true"> </span> <s:text name="menu.inicio" /></a>
</li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>>
	<s:url var="verPerfilViajero" action="verPerfilViajero">
		<s:param name="id" value="%{user.id}"></s:param>
	</s:url>
	<s:a href="%{verPerfilViajero}"><span class="fa fa-user" aria-hidden="true"> </span> <s:text name="menuviajero.perfil" /></s:a>
</li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%>>
	<a href="Viajes"><span class="fa fa-road" aria-hidden="true"> </span> <s:text name="menuviajero.viajes" /></a>
</li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%>>
	<a href="detalle"><span class="fa fa-comment" aria-hidden="true"> </span> <s:text name="menuviajero.mensajes" /></a>
</li>
<li>
	<a href="#" onclick="javascript: mostrarNotificaciones()"><i class="fa fa-bell-o"></i><span id="circulo" class="circulo"></span></a>
</li>

<div id="notificaciones">
	<div class="flechaArriba"></div>
	<div id="header">Notificaciones</div>
	<div id="bodyNotificaciones"></div>
	<div class="footer">Ver Todas</div>
</div>
