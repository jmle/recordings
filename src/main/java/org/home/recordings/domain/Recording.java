package org.home.recordings.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "recordings")
@Builder
@Wither
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

    @Id
    private String filename;
    @JsonFormat(pattern = "yyyy-MMM-d")
    private LocalDate date;
    private Integer tempo;
    private String beat;
    private String style;
    private String comments;

}
