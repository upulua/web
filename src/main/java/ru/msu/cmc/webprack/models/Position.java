package ru.msu.cmc.webprack.models;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "position")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Position implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "position_id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "duty")
    @NonNull
    private String duty;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @NonNull
    private Department department;

    @Column(nullable = false, name = "size")
    @NonNull
    private Long size;
}
