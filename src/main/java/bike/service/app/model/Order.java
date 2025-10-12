package bike.service.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OrderService")
public class Order {

    public enum Status {
       NEW, ACTIVE, DONE;
    } 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @OneToOne
    @JoinColumn(name = "mechanic",referencedColumnName = "last_name")
    private Users mechanic;
    @Column(name = "service")
    private String service;
    @Column(name = "price", nullable = true)
    private Double price;
    @Column(name = "comment")
    private String comment;
    @Column(name = "bikeModel")
    private String bikeModel;
    @Column(name = "client")
    private String client;
    @Column(name = "addby")
    private String addByUser;
    @Column(name = "donebyuser ")
    private String doneByUser;
    @Column(name = "date")
    private String date;
    @Column(name = "deliveryDate")
    private String deliveryDate;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    // private List<Services> services;
}