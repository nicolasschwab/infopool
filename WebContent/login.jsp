<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>    
    <%@ include file="views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>

    <%@ include file="views/header.jsp"%>
   <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="99"/>
    </jsp:include>

    <div class="main_bg"><!-- start main -->    
        <div class="container">        
        	<div class="col-md-4 text-center centrar margentb2">
                <% String user = (String) session.getAttribute("perfil"); 
                if (user == null){ %>
                <h3><s:text name="login.ingresarlbl" /></h3>                
                <s:form cssClass="form-signin" theme="simple" role="form" action="validarLogin" validate="true">    		                                  
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                            <s:textfield cssClass="form-control" name="usuario"/>
                        </div>  
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('usuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('usuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                        
                            <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                            <s:password cssClass="form-control" name="clave"/>                            
                        </div>
                        <s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('clave') != null">
	                           	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('clave')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
                    </div>
                    <s:if test="getFieldErrors() != null">
	                    <s:if test="getFieldErrors().get('loginError') != null">
	                    	<div class="errorMessageL">
	                    		<s:property value="getFieldErrors().get('loginError')[0]" />
	                    	</div>
	                    </s:if>
	                </s:if>
                    <div class="form-group">
    		          <s:submit cssClass="btn btn-primary" value="%{getText('login.ingresar')}"/>
                    </div>
    		    </s:form>
    		    <% }%>
          </div>        
        </div>
    </div>
    
    <%@ include file= "views/footer.jsp" %>    
    
</body>
</html>