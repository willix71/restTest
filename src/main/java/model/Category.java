package model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Category extends AbstractEntity{
    private Category parent;
    private Date date;
    private List<CatType> types;
    
    public Category () {}

    public Category(String name, Category parent, CatType ...types) {
        super(name);
        this.parent = parent;
        
        this.types = Arrays.asList(types);
		for(CatType t:types) t.setCategory(this);
    }

    public Category(String name, Date date, Category parent, CatType ...types) {
        this(name,parent, types);
        this.date = date; 
    }
    
    public Category getParent() {return parent;}
    public void setParent(Category parent) {this.parent = parent;}
	public Date getDate() {return date;}
	public void setDate(Date date) {this.date = date;}
	public List<CatType> getTypes() {return types;}
	public void setTypes(List<CatType> types) {this.types = types;}
}