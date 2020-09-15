package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_PRATICHE")
public class TipoPraticheEntity {

    private long id;

    private String tipoPratica;

    public TipoPraticheEntity() {
    }

    public TipoPraticheEntity(long id, String tipoPratica) {
        this.id = id;
        this.tipoPratica = tipoPratica;
    }

    public TipoPraticheEntity(String tipoPratica) {
        this.tipoPratica = tipoPratica;
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

    @Column(name = "tipo_pratica")
    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }
}
