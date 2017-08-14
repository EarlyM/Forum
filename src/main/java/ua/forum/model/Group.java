package ua.forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group implements Serializable {
    private int id;
    private String role;
    private List<Authorities> authorities;
    private Set<User> users;

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ROLE", nullable = false, length = 25)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (!role.equals(group.role)) return false;
        if (!authorities.equals(group.authorities)) return false;
        return users.equals(group.users);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + role.hashCode();
        result = 31 * result + authorities.hashCode();
        result = 31 * result + users.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", authorities=" + authorities +
                ", users=" + users +
                '}';
    }
}
