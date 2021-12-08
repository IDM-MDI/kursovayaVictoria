package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "shoes_shop", catalog = "")
public class UserEntity
{
    private Integer iduser;
    private String userName;
    private String userLogin;
    private String userPass;
    private Integer userRoleId;
    private String userCreateTime;

    @Id
    @Column(name = "iduser", nullable = false)
    public Integer getIduser()
    {
        return iduser;
    }

    public void setIduser(Integer iduser)
    {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 45)
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userLogin", nullable = false, length = 45)
    public String getUserLogin()
    {
        return userLogin;
    }

    public void setUserLogin(String userLogin)
    {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "userPass", nullable = false, length = 45)
    public String getUserPass()
    {
        return userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "userRoleID", nullable = true)
    public Integer getUserRoleId()
    {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "userCreateTime", nullable = true, length = 45)
    public String getUserCreateTime()
    {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime)
    {
        this.userCreateTime = userCreateTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (iduser != null ? !iduser.equals(that.iduser) : that.iduser != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPass != null ? !userPass.equals(that.userPass) : that.userPass != null) return false;
        if (userRoleId != null ? !userRoleId.equals(that.userRoleId) : that.userRoleId != null) return false;
        if (userCreateTime != null ? !userCreateTime.equals(that.userCreateTime) : that.userCreateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = iduser != null ? iduser.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (userRoleId != null ? userRoleId.hashCode() : 0);
        result = 31 * result + (userCreateTime != null ? userCreateTime.hashCode() : 0);
        return result;
    }
}
