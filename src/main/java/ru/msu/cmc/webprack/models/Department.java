package ru.msu.cmc.webprack.models;

import lombok.*;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "department")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Department implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "department_id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(name = "head_department")
    private Long head;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    @ToString.Exclude
    @NonNull
    private Worker worker;

}
