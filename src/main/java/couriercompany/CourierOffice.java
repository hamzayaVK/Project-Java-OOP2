package couriercompany;

import address.Address;
import order_package.Orders;
import users.Courier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courieroffice")
public class CourierOffice {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "courieroffice_generator")
    @SequenceGenerator(name = "courieroffice_generator", sequenceName = "courieroffice_sequence", allocationSize = 1)
    private int id;

    @OneToMany(mappedBy = "courierOffice")
    private List<Courier> courierList = new ArrayList<>();

    @OneToMany(mappedBy = "courierOffice")
    private List<Orders> ordersList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "addressid")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "companyid")
    private CourierCompany courierCompany;

    public int getId() {
        return id;
    }

    public CourierCompany getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(CourierCompany courierCompany) {
        this.courierCompany = courierCompany;
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<Courier> courierList) {
        this.courierList = courierList;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CourierOffice{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
