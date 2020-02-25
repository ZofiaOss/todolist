package com.ossowska.todolist.persistance;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseAuditableEntity {
    @Version
    private Long version;
}
