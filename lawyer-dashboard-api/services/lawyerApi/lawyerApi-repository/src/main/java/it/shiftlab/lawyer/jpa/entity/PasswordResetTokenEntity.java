package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "RESET_PASSWORD")
public class PasswordResetTokenEntity {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UsersEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username_id")
    private UsersEntity user;

    private Date expiryDate;

    public PasswordResetTokenEntity() {
        super();
    }

    public PasswordResetTokenEntity(final String token) {
        super();
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetTokenEntity(final String token, final UsersEntity user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
