package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "shoes_shop", catalog = "")
public class RoleEntity
{
    private Integer idrole;
    private String roleName;

    @Id
    @Column(name = "idrole", nullable = false)
    public Integer getIdrole()
    {
        return idrole;
    }

    public void setIdrole(Integer idrole)
    {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "roleName", nullable = false, length = 45)
    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (idrole != null ? !idrole.equals(that.idrole) : that.idrole != null) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idrole != null ? idrole.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
