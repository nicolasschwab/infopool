<%@ page language="java" import="model.Usuario"%>
<div class="header_bg">
    <div class="container">
        <div class="row header">
            <div class="logo navbar-left">
                <h1><a href="index.jsp" title="Info Pool"><img src="images/logoInfoPool.png"></a></h1>
            </div>
            <div class="h_search navbar-right">                                    
                
                  <% if (session.getAttribute("usrLogin") == null){  %>  
                        <a href='login.jsp' class="ingresar">Ingresar</a>
                        <a href='registro.jsp' class="registrar">Registrarse</a>
                    <% } else{ %>                        
                        Bienvenido <%= ((Usuario)session.getAttribute("usrLogin")).getUsuario() %>
                        <a href='cerrarSesion' class="salir">Cerrar sesi&oacute;n</a>
                 <% } %>
                
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>