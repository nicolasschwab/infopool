package actions;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class CustomImageBytesResults implements Result{

private static final long serialVersionUID = 1L;

	public void execute(ActionInvocation invocation) throws Exception {	
		ImageAction action = (ImageAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();		
		response.setContentType(action.getCustomContentType()); // retorna "image/jpeg"
		response.getOutputStream().write(action.getCustomImageInBytes());
		response.getOutputStream().flush();
	}
}