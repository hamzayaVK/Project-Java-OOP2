package POJO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "orders_generator")
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_sequence", allocationSize = 1)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "datesent")
    private LocalDate dateSent;

    @Column(name = "datearrived")
    private LocalDate dateArrived;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "clientid")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "courierid")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "courierofficeid")
    private CourierOffice courierOffice;

    @OneToMany(mappedBy = "orders")
    private List<Product> productList = new ArrayList<>();

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", number=" + number +
                ", dateSent=" + dateSent +
                ", dateArrived=" + dateArrived +
                ", client=" + client +
                ", courier=" + courier +
                ", courierOffice=" + courierOffice +
                ", productList=" + productList.toString() +
                '}';
    }

    public List<Product> getAllProducts()
    {
        Configuration configuration = new org.hibernate.cfg.Configuration().addAnnotatedClass(Product.class);
        SessionFactory sf = configuration.buildSessionFactory();
        org.hibernate.Session session = sf.openSession();
        return session.createQuery("FROM Product", Product.class).getResultList();
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }

    public LocalDate getDateArrived() {
        return dateArrived;
    }

    public void setDateArrived(LocalDate dateArrived) {
        this.dateArrived = dateArrived;
    }

    public CourierOffice getCourierOffice() {
        return courierOffice;
    }

    public void setCourierOffice(CourierOffice courierOffice) {
        this.courierOffice = courierOffice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
