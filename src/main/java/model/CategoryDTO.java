package model;

import java.util.Arrays;
import java.util.List;

public class CategoryDTO extends AbstractDTO {
    private AbstractDTO parent;
    private String date;
    private List<CatTypeDTO> types;
    
    public CategoryDTO () {}

    public CategoryDTO(String name, AbstractDTO parent, CatTypeDTO ...types) {
        super(name);
        this.parent = parent;
        
        setTypes(types);
    }
    
    public CategoryDTO(String name, String date, AbstractDTO parent, CatTypeDTO ...types) {
        this(name,parent,types);
        this.date = date;        
    }
    
    public AbstractDTO getParent() {return parent;}
    public void setParent(AbstractDTO parent) {this.parent = parent;}
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public List<CatTypeDTO> getTypes() {return types;}
	public void setTypes(List<CatTypeDTO> types) {this.types = types;}
	public void setTypes(CatTypeDTO... types) {
		this.types = Arrays.asList(types);
	}
}