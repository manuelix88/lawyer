package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "AVVOCATI_DELEGATI")
public class AvvocatoDelegatoEntity {

    private long id;

    private String avvocatoDelegato;

    public AvvocatoDelegatoEntity() {
    }

    public AvvocatoDelegatoEntity(long id, String avvocatoDelegato) {
        this.id = id;
        this.avvocatoDelegato = avvocatoDelegato;
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

    @Column(name = "avvocato_delegato")
    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }
}
