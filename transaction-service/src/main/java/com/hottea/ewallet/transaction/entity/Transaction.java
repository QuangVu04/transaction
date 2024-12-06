package com.hottea.ewallet.transaction.entity;

        import jakarta.persistence.*;
        import lombok.*;
        import lombok.experimental.FieldDefaults;

        import java.io.Serializable;
        import java.math.BigDecimal;
        import java.sql.Timestamp;
        import java.time.Instant;
        import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="transaction_uuid", nullable = false, unique = true)
    private String uuid;
    @Column(name = "from_wallet_id", nullable = false, unique = true)
    String fromWalletId;
    @Column(name = "to_wallet_id",nullable = false, unique = true)
    String toWalletId;
    @Column( precision = 19, scale = 4)
    BigDecimal amount;
    String description;
    @Column(name = "transaction_status")
    String transactionStatus;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @PrePersist
    protected void onCreate() {
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = Timestamp.from(Instant.now());
    }



}
