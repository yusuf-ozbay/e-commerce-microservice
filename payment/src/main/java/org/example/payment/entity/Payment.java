package org.example.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Builder tasarım desenini kullanarak Payment nesnelerinin daha okunabilir ve düzenli bir şekilde oluşturulmasını sağlar.
@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false, length = 36)
    private String id;
    private String basketId;
    private String cardNumber;
    private String month;
    private String year;
    private Double amount;

}
