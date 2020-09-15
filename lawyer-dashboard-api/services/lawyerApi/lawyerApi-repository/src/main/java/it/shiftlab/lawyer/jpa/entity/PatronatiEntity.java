package it.shiftlab.lawyer.jpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "PATRONATI")
public class PatronatiEntity {


    private long id;


    private String patronato;

    public PatronatiEntity() {
    }

    public PatronatiEntity(long id, String patronato) {
        this.id = id;
        this.patronato = patronato;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "patronato")
    public String getPatronato() {
        return patronato;
    }

    public void setPatronato(String patronato) {
        this.patronato = patronato;
    }
}
