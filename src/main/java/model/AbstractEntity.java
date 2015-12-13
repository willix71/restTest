package model;

import java.util.UUID;

public class AbstractEntity {
    private String uid = UUID.randomUUID().toString();
    private long version = 1;
    private String name;
    
    public AbstractEntity() {}
    public AbstractEntity(String name) {this.name=name;}
    
    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}    
    public long getVersion() {return version;}
	public void setVersion(long version) {this.version = version;	}
	public String getName() {return name;}
    public void setName(String name) {this.name = name;}        
}