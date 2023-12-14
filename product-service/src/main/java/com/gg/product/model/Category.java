package com.gg.product.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull private String name;

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;
}
