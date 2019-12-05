package couriercompany;

import address.Address;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "couriercompany")
public class CourierCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "couriercompany_generator")
    @SequenceGenerator(name = "couriercompany_generator", sequenceName = "couriercompany_sequence", allocationSize = 1)
    private int id;

    @OneToMany
    private List<CourierOffice> courierOfficeList;

    @OneToOne
    @JoinColumn(name = "addressid")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CourierOffice> getCourierOfficeList() {
        return courierOfficeList;
    }

    public void setCourierOfficeList(List<CourierOffice> courierOfficeList) {
        this.courierOfficeList = courierOfficeList;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "CourierCompany{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
