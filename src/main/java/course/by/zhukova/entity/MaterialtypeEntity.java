package course.by.zhukova.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "materialtype", schema = "shoes_shop", catalog = "")
public class MaterialtypeEntity
{
    private Integer idmaterialtype;
    private String materialtypeName;
    private Collection<MaterialEntity> materialsByIdmaterialtype;
    private Collection<MaterialEntity> materialsByIdmaterialtype_0;
    private Collection<MaterialEntity> materialsByIdmaterialtype_1;
    private Collection<MaterialEntity> materialsByIdmaterialtype_2;

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

    @OneToMany(mappedBy = "materialtypeByMaterialInside")
    public Collection<MaterialEntity> getMaterialsByIdmaterialtype()
    {
        return materialsByIdmaterialtype;
    }

    public void setMaterialsByIdmaterialtype(Collection<MaterialEntity> materialsByIdmaterialtype)
    {
        this.materialsByIdmaterialtype = materialsByIdmaterialtype;
    }

    @OneToMany(mappedBy = "materialtypeByMaterialOutside")
    public Collection<MaterialEntity> getMaterialsByIdmaterialtype_0()
    {
        return materialsByIdmaterialtype_0;
    }

    public void setMaterialsByIdmaterialtype_0(Collection<MaterialEntity> materialsByIdmaterialtype_0)
    {
        this.materialsByIdmaterialtype_0 = materialsByIdmaterialtype_0;
    }

    @OneToMany(mappedBy = "materialtypeByMaterialSole")
    public Collection<MaterialEntity> getMaterialsByIdmaterialtype_1()
    {
        return materialsByIdmaterialtype_1;
    }

    public void setMaterialsByIdmaterialtype_1(Collection<MaterialEntity> materialsByIdmaterialtype_1)
    {
        this.materialsByIdmaterialtype_1 = materialsByIdmaterialtype_1;
    }

    @OneToMany(mappedBy = "materialtypeByMaterialUpper")
    public Collection<MaterialEntity> getMaterialsByIdmaterialtype_2()
    {
        return materialsByIdmaterialtype_2;
    }

    public void setMaterialsByIdmaterialtype_2(Collection<MaterialEntity> materialsByIdmaterialtype_2)
    {
        this.materialsByIdmaterialtype_2 = materialsByIdmaterialtype_2;
    }
}
