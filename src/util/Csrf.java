package util;

import java.util.Map;
import java.util.UUID;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Csrf implements SessionAware{

	private SessionMap<String, Object> sessionMap;
	
	public SessionMap<String, Object> getSession() {
		return sessionMap;
	}
	public void setSession(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public static String getTokenId(){
		if(SessionUtil.getTokenId()!=null){
			return SessionUtil.getTokenId();
		}else{
			String tokenId=UUID.randomUUID().toString().replaceAll("-","");
			SessionUtil.getSession().setAttribute("tokenId", tokenId);
			return tokenId;
		}
	}
	
	public static String getTokenValue(){
		if(SessionUtil.getTokenValue()!=null){
			return SessionUtil.getTokenValue();
		}else{
			String tokenValue=UUID.randomUUID().toString().replaceAll("-","");
			SessionUtil.getSession().setAttribute("tokenValue", tokenValue);
			return tokenValue;
		}
	}
	
	public static boolean checkValues(String id,String value){
		if(getTokenId().equals(id)){
			if(getTokenValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
}
