package dev.renandi.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String kind;

    private Integer columnOrder;

    @ManyToOne
    @JoinColumn(name="board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cardList = new ArrayList<>();



    public BoardColumn(String name, String kind, Integer columnOrder, Board board) {
        this.name = name;
        this.kind = kind;
        this.columnOrder = columnOrder;
        this.board = board;
    }
}
