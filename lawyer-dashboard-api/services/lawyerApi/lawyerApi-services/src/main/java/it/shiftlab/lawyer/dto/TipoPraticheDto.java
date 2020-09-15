package it.shiftlab.lawyer.dto;

public class TipoPraticheDto {
    private long id;

    private String tipoPratica;

    public TipoPraticheDto() {
    }

    public TipoPraticheDto(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    public TipoPraticheDto(long id, String tipoPratica) {
        this.id = id;
        this.tipoPratica = tipoPratica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }
}
