package com.gxdxx.programadmin.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class AfterService {

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}
