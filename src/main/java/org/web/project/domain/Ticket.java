package org.web.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private int tno;
    private String owner;
    private String grade;
}
