package model;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Project {
    
    private int id;
    private String name;
    private String description;
    private List<Task> tasks;
    private Date createAt;
    private Date updateAt;  
    private static final Logger LOG = Logger.getLogger(Project.class.getName());

    public Project(int id, String name, String description, List<Task> tasks, Date createAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Project() {
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return name;
    }

    public void add(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
