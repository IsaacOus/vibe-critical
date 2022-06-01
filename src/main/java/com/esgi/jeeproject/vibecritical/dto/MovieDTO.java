package com.esgi.jeeproject.vibecritical.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieDTO {

    @JsonProperty("Title")
    private String name;

    @JsonProperty("Released")
    private String releaseDate;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Plot")
    private String plot;

}
