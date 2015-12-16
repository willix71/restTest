package json;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.AbstractDTO;
import model.CategoryDTO;
import model.CatTypeDTO;
public class ParsingJsonTest {

	@Test
	public void simpleTest() throws Exception {
		CategoryDTO dto = new CategoryDTO("child","20030201 000000",new AbstractDTO("parent"), new CatTypeDTO(1), new CatTypeDTO(2));
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		String json = mapper.writeValueAsString(dto);
		System.out.println(json);
			
		CategoryDTO dto2 = mapper.readValue(json, CategoryDTO.class);
		String json2 = mapper.writeValueAsString(dto2);
		System.out.println(json2);
		
		Assert.assertEquals(json, json2);
	}
}
