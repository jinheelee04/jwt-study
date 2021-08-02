package com.jwt.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @JsonIgnore
    @Id
    @Column(name="service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(name="building_name", length = 50, nullable = false)
    private String buildingName;
}
