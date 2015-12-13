package model;

public class CatTypeDTO extends AbstractDTO {

	private Integer something;

	public CatTypeDTO() {}
	
	public CatTypeDTO(Integer something) {
		super();
		this.something = something;
	}
	public Integer getSomething() {return something;}
	public void setSomething(Integer something) {this.something = something;}
}
