<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
</div>
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  <ul class="nav navbar-nav">
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%> class="active" <%}%>><a href="index.jsp"><span class="fa fa-home" aria-hidden="true"> </span> <s:text name="menu.inicio" /></a></li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>><a href="eventos"><span class="fa fa-calendar" aria-hidden="true"> </span> <s:text name="menuadmin.eventos" /></a></li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%>><a href="viajeros"><span class="fa fa-users" aria-hidden="true"> </span> <s:text name="menuadmin.viajeros" /></a></li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%>><a href="denuncias"><span class="fa fa-thumbs-down" aria-hidden="true"> </span> <s:text name="menuadmin.denuncias" /></a></li>
  </ul>
</div>       