package net.alexdixon.noxidhraio.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Role {


    @Id
    @GeneratedValue
    private int id;

    private String role_name;

    private String role_desc;

    public int getId() {
        return id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
    public Role() {
    }
    public Role(String role_name, String role_desc) {
        this.role_name = role_name;
        this.role_desc = role_desc;
    }
}
