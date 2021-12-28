package com.charter.dataprocessor.repository;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("CAMPAIGN")
public class Campaign {

    @Id
    @Column("ID")
    private Integer id;

    @Column("TASK_NAME")
    private String name;

    @Column("TYPE")
    private String type;

    @Column("CRON_EXPRESSION")
    private String cronExpression;

    @Column("MAPPING_FILE")
    private String propertyFile;

    @Column("STATUS")
    private boolean status;

}
