package com.cdc.data.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "wikimedia_events")
public class WikimediaEvent extends EntityBase<WikimediaEvent> {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "title_url", nullable = false)
    private String titleUrl;
    @Column(name = "event_time", nullable = false)
    private String eventTime;
    @Column(name = "user_name", nullable = false)
    private String user;
}
