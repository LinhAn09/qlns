package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



//1 tự viết thì code đẹp

@Entity
@Table(name = "Categorys")
public class Categorys {

    @Id
    private int categoryID; // khóa chính
    private String name;

    public Categorys() {
    }

    public Categorys(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    
    
}
