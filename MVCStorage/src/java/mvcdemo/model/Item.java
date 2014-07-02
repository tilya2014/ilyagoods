package mvcdemo.model;

public class Item {

    private String name;
    private String imgName;
    private String description;
    private int count;
    private int id;
    private int categoryID;

    public Item(int id, String name, String imgName, String description, int count, int categoryID) {
        this.id = id;
        this.name = name;
        this.imgName = imgName;
        this.description = description;
        this.count = count;
        this.categoryID = categoryID;
    }

    public Item() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
