package domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "CREATE_DT")
    private LocalDateTime createdDate;
    @Column(name = "CREATE_NO")
    private Long createNo;
    @Column(name = "UPDATE_DT")
    private LocalDateTime lastModifiedDate;
    @Column(name = "UPDATE_NO")
    private Long lastModifiedNo;
}
