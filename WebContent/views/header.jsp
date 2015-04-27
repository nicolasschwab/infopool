<%@ page language="java" import="model.Usuario"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="header_bg">
    <div class="container">
        <div class="row header">
            <div class="logo navbar-left">
                <h1><a href="index.jsp" title="Info Pool"><img src="images/logoInfoPool.png"></a></h1>
            </div>
            <div class="h_search navbar-right">                                    
                
                  <% if (session.getAttribute("usrLogin") == null){  %>  
                        <a href='login.jsp' class="ingresar"><s:text name="login.ingresar" /></a>
                        <a href='registro.jsp' class="registrar"><s:text name="login.registrarse" /></a>
                    <% } else{ %>                        
                        <s:text name="login.bienvenido" /> <%= ((Usuario)session.getAttribute("usrLogin")).getUsuario() %>
                        <a href='cerrarSesion' class="salir"><s:text name="login.salir" /></a>
                 <% } %>
                
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>