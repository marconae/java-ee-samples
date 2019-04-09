package de.nae.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product implements Serializable {

    private static final long serialVersionUID = 5608183385484395265L;

    @Id
    @GeneratedValue
    private int dbId;

    @NotNull
    private String name;

    private Double price;



}
