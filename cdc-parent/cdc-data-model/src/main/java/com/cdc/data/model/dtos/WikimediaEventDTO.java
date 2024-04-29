package com.cdc.data.model.dtos;

import lombok.Data;

@Data
public class WikimediaEventDTO {
    private String title;
    private String title_url;
    private String timestamp;
    private String user;
}
