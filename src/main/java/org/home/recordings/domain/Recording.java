package org.home.recordings.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "recordings")
@Getter
@Builder
public class Recording {

    @Id
    private final String filename;
    private final LocalDate date;
    private final Integer tempo;
    private final String beat;
    private final String style;

}
