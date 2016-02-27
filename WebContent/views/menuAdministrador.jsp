<%@ taglib prefix="s" uri="/struts-tags"%>

<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%> class="active" <%}%>><a href="Inicio"><span class="fa fa-home" aria-hidden="true"> </span> <s:text name="menu.inicio" /></a></li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>><a href="eventos"><span class="fa fa-calendar" aria-hidden="true"> </span> <s:text name="menuadmin.eventos" /></a></li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%>><a href="viajeros"><span class="fa fa-users" aria-hidden="true"> </span> <s:text name="menuadmin.viajeros" /></a></li>