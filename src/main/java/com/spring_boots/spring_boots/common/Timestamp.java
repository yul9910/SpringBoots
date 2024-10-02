package com.spring_boots.spring_boots.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Timestamp {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt;

//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분");
//
//    @Transient
//    public String recentTime(){
//        return updatedAt == null ? updatedAt.format(formatter) : updatedAt.format(formatter);
//    }

}
