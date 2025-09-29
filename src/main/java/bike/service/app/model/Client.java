package bike.service.app.model;

import javax.validation.constraints.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clientId")
    private int clientId;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "phoneNumber")
    @Pattern(regexp = "^[0-9+\\- ]{7,15}$", message = "WRONG PHONE NUMBER!!!")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

}