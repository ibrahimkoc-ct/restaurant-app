package com.ba.entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql=
        "UPDATE Waiter "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
@Table(name = "Waiter")
public class Waiter extends BaseEntity implements Serializable {

    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private Long salary;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;
}
