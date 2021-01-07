package domain;

import entityMapping.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name="member_seq_generator", sequenceName = "seq_member", initialValue = 1, allocationSize = 50)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") //filed명과 DB column명이 다를 경우
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //Enum 속성은 언제든 순서가 바뀔 수 있으므로, Ordinary를 사용하면 안됨 (default : ordinary)
    private RoleType roleType;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

}
