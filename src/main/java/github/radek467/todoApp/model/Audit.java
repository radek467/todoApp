package github.radek467.todoApp.model;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
class Audit {

    private LocalDateTime updatedOn;
    private LocalDateTime createdOn;

    @PreUpdate
    void setUpdatedOn(){
        updatedOn = LocalDateTime.now();
    }

    @PrePersist
    void setCreatedOn(){
        createdOn = LocalDateTime.now();
    }
}
