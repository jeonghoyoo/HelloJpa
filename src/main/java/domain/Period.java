package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Period {
    @Column(name = "CREATE_DT")
    private LocalDateTime createdDate;
    @Column(name = "CREATE_NO")
    private Long createNo;
    @Column(name = "UPDATE_DT")
    private LocalDateTime lastModifiedDate;
    @Column(name = "UPDATE_NO")
    private Long lastModifiedNo;
}
