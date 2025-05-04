package dev.renandi.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "board_column_id", nullable = false)
    private BoardColumn boardColumn;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Block> blocks = new ArrayList<>();



    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
