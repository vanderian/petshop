package sk.vanderian.petshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gallery_items")
public class GalleryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String url;
}
