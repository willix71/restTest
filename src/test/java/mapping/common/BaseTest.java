package mapping.common;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import model.AbstractDTO;
import model.Category;
import model.CategoryDTO;
import model.CatType;
import model.CatTypeDTO;
import w.utils.DateUtils;

public abstract class BaseTest {

	protected abstract CategoryDTO toDto(Category source);
	protected abstract Category fromDto(CategoryDTO source);
	protected abstract void fromDto(CategoryDTO source, Category destination);
	
//	protected abstract <S,D> D convert(S s, Class<D> d);
//	
//	protected abstract <S,D> D convert(S s, D d);
	
	@Test
    public void simpleTest() {
        Category entity = new Category("Test1", DateUtils.toDate(1,2,2003), null);

        CategoryDTO dto = toDto(entity);

        Assert.assertEquals("Test1", dto.getName());
        Assert.assertEquals(1, dto.getVersion());
        Assert.assertEquals(entity.getUid(), dto.getUid());
        Assert.assertEquals("20030201 000000", dto.getDate());
    }

    @Test
    public void withParentTest() {
        Category entity = new Category("child",new Category("parent", new Category("root", null)));

        CategoryDTO dto = toDto(entity);

        Assert.assertEquals("child", dto.getName());      
        Assert.assertEquals(dto.getUid(), dto.getUid());
        Assert.assertNotNull(dto.getParent()); // my child should have a parent dto
        Assert.assertTrue(!(dto.getParent() instanceof CategoryDTO)); // and it should be an AbstractDTO to stop
    }
    
    @Test
    public void reverseMapTest() {
    	CategoryDTO dto = new CategoryDTO("child","20030201 000000",new AbstractDTO("parent"));

        Category entity = fromDto(dto);

        Assert.assertEquals("child", entity.getName());
        Assert.assertEquals(dto.getUid(), entity.getUid());
        Assert.assertNotNull(entity.getParent()); // my child should have a parent dto
        Assert.assertTrue(entity.getParent() instanceof Category); // and it should be an AbstractDTO to stop
        
        // the version should not have changed
        Assert.assertEquals(1,entity.getVersion());
        Assert.assertEquals(DateUtils.toDate(1,2,2003), entity.getDate());
    }
    
    @Test
    public void listTest() {
    	Category entity = new Category("child",new Category("parent", new Category("root", null)), new CatType(2), new CatType(3));
    	
    	 CategoryDTO dto = toDto(entity);

         Assert.assertEquals("child", dto.getName());      
         Assert.assertEquals(dto.getUid(), dto.getUid());
         Assert.assertEquals(2, dto.getTypes().size());
    }
    
    /**
     * 
     */
    @Test
    public void reverseListTest() {
    	CategoryDTO dto = new CategoryDTO("child",new AbstractDTO("parent"), new CatTypeDTO(2), new CatTypeDTO(3));
    	
        Category entity = fromDto(dto);

        Assert.assertEquals("child", entity.getName());
        Assert.assertEquals(dto.getUid(), entity.getUid());
        Assert.assertNotNull(entity.getParent()); // my child should have a parent dto
        Assert.assertTrue(entity.getParent() instanceof Category); // and it should be an AbstractDTO to stop
        
        // the version should not have changed
        Assert.assertEquals(1,entity.getVersion());
        
        Assert.assertEquals(2, entity.getTypes().size());
//        CatType catType = entity.getTypes().get(0);
//        Assert.assertEquals(entity,catType.getCategory());
    }
    
	@Test
    public void fillingExistingTypeTest() {
		CategoryDTO dto = new CategoryDTO("child",new AbstractDTO("parent"));
        Category entity = new Category();
        fromDto(dto, entity);

        Assert.assertEquals("child", entity.getName());
        Assert.assertEquals(1, entity.getVersion());
        Assert.assertEquals(entity.getUid(), dto.getUid());
        Assert.assertEquals("parent", entity.getParent().getName());
    }
	
    @Test
    @Ignore
    public void fromMapTest() {
    	Map<String,Object> p = new HashMap<String, Object>();
    	p.put("name", "parent");

    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("name", "child");
    	map.put("parent", p);
    	
    	Category cat = null; //getModelMapper().map(map,Category.class);
    	Assert.assertEquals("child", cat.getName());
    	Assert.assertNotNull(cat.getParent());
    	Assert.assertEquals("parent", cat.getName());
    }
    
}
