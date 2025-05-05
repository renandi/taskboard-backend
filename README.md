# Taskboard - backend

Project developed based on an exercise suggested during a backend course from DIO using  Spring Boot, JPA and MySQL.

[Click here to see instructions and project requirements](https://github.com/digitalinnovationone/exercicios-java-basico/blob/main/projetos/4%20-%20T%C3%A9cnicas%20Avan%C3%A7adas%2C%20Padr%C3%B5es%20e%20Persist%C3%AAncia%20(Literalmente).md)


---

## Class diagram

``` mermaid
classDiagram
direction TB
    class Board {
	    -Long id
	    -String name
	    -List boardColumns
    }

    class BoardColumn {
	    -Long id
	    -String name
	    -String kind
	    -Integer columnOrder
	    -List cardList
    }

    class Card {
	    -Long id
	    -String title
	    -String description
	    -OffsetDateTime createdAt
	    -List blocks
    }

    class Block {
	    -Long id
	    -OffsetDateTime blockIn
	    -String blockCause
	    -OffsetDateTime unblockIn
	    -String unblockCause
    }

    Board "1" *-- "*" BoardColumn
    BoardColumn "1" o-- "0..*" Card
    Card "1" o-- "0..*" Block


```
