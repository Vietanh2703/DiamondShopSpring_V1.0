package com.anhntv.ecom.entities;

import com.anhntv.ecom.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @Column(columnDefinition = "VARCHAR(10000)")
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    //Many products can have one category
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDTO getDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setByteImg(img);
        dto.setCategoryId(category.getId());
        dto.setCategoryName(category.getName());
        return dto;
    }
}
