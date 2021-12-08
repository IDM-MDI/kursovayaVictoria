package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer", schema = "shoes_shop", catalog = "")
public class ManufacturerEntity
{
    private Integer idmanufacturer;
    private String manufacturerName;
    private String phoneNumber;
    private String manufacturerMail;
    private Integer manufacturerCountry;

    @Id
    @Column(name = "idmanufacturer", nullable = false)
    public Integer getIdmanufacturer()
    {
        return idmanufacturer;
    }

    public void setIdmanufacturer(Integer idmanufacturer)
    {
        this.idmanufacturer = idmanufacturer;
    }

    @Basic
    @Column(name = "manufacturerName", nullable = false, length = 45)
    public String getManufacturerName()
    {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = true, length = 45)
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "manufacturerMail", nullable = true, length = 45)
    public String getManufacturerMail()
    {
        return manufacturerMail;
    }

    public void setManufacturerMail(String manufacturerMail)
    {
        this.manufacturerMail = manufacturerMail;
    }

    @Basic
    @Column(name = "manufacturerCountry", nullable = true)
    public Integer getManufacturerCountry()
    {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(Integer manufacturerCountry)
    {
        this.manufacturerCountry = manufacturerCountry;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManufacturerEntity that = (ManufacturerEntity) o;

        if (idmanufacturer != null ? !idmanufacturer.equals(that.idmanufacturer) : that.idmanufacturer != null)
            return false;
        if (manufacturerName != null ? !manufacturerName.equals(that.manufacturerName) : that.manufacturerName != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (manufacturerMail != null ? !manufacturerMail.equals(that.manufacturerMail) : that.manufacturerMail != null)
            return false;
        if (manufacturerCountry != null ? !manufacturerCountry.equals(that.manufacturerCountry) : that.manufacturerCountry != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idmanufacturer != null ? idmanufacturer.hashCode() : 0;
        result = 31 * result + (manufacturerName != null ? manufacturerName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (manufacturerMail != null ? manufacturerMail.hashCode() : 0);
        result = 31 * result + (manufacturerCountry != null ? manufacturerCountry.hashCode() : 0);
        return result;
    }
}
