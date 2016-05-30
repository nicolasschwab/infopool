package util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class Dozer {

	static Mapper mapper=null;
	
	public static Mapper getMapper(){
		if(mapper==null){
			mapper = new DozerBeanMapper();
		}
		return mapper;
	}
}
