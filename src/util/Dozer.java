package util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class Dozer {

	static Mapper mapper=null;
	
	public static Mapper getMapper(){
		if(mapper==null){
			List<String> mappingFiles= new ArrayList<String>();
			mappingFiles.add("Mappings.xml");
			mapper = new DozerBeanMapper(mappingFiles);
		}
		return mapper;
	}
}
