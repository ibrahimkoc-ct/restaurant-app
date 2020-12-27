package com.ba.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql=
        "UPDATE WAITER "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
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
