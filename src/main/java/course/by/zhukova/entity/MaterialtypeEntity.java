package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "materialtype", schema = "shoes_shop", catalog = "")
public class MaterialtypeEntity
{
    private Integer idmaterialtype;
    private String materialtypeName;

    @Id
    @Column(name = "idmaterialtype", nullable = false)
    public Integer getIdmaterialtype()
    {
        return idmaterialtype;
    }

    public void setIdmaterialtype(Integer idmaterialtype)
    {
        this.idmaterialtype = idmaterialtype;
    }

    @Basic
    @Column(name = "materialtypeName", nullable = false, length = 45)
    public String getMaterialtypeName()
    {
        return materialtypeName;
    }

    public void setMaterialtypeName(String materialtypeName)
    {
        this.materialtypeName = materialtypeName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialtypeEntity that = (MaterialtypeEntity) o;

        if (idmaterialtype != null ? !idmaterialtype.equals(that.idmaterialtype) : that.idmaterialtype != null)
            return false;
        if (materialtypeName != null ? !materialtypeName.equals(that.materialtypeName) : that.materialtypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idmaterialtype != null ? idmaterialtype.hashCode() : 0;
        result = 31 * result + (materialtypeName != null ? materialtypeName.hashCode() : 0);
        return result;
    }
}
