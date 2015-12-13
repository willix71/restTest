package model;

public class CatType extends AbstractEntity {

	private Integer something;
	private Category category;
	
	public CatType() {}
	
	public CatType(Integer something) {
		super();
		this.something = something;
	}
	
	public CatType(Integer something, Category category) {
		super();
		this.something = something;
		this.category = category;
	}

	public Integer getSomething() {return something;}
	public void setSomething(Integer something) {this.something = something;}
	public Category getCategory() {return category;}
	public void setCategory(Category category) {this.category = category;}
}
