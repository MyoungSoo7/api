package com.lms.api;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    private String test;

    /*@CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;*/
}
