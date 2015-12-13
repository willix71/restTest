package model;

public class AbstractDTO {
    private String uid;
    private long version;
    private String name;

    public AbstractDTO() {}
    public AbstractDTO(String name) {this.name=name;}
    
    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}
    public long getVersion() {return version;}
	public void setVersion(long version) {this.version = version;	}
	public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}