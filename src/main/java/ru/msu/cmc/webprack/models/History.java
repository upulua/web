package ru.msu.cmc.webprack.models;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "history")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class History implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "worker_id")
    @ToString.Exclude
    @NonNull
    private Worker worker;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    @NonNull
    private Position position;

    @Column(nullable = false, name = "start_date")
    @NonNull
    private Timestamp start;

    @Column(name = "finish_date")
    private Timestamp finish;
}
