package org.web.project.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.web.project.domain.SampleVO;
import org.web.project.domain.Ticket;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText(){
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    @GetMapping(value = "/getSample", produces =
            {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample(){
        return new SampleVO(112, "스타", "로드");
    }

    @GetMapping(value = "/getSample2", produces = MediaType.APPLICATION_JSON_VALUE)
    public SampleVO getSample2(){
        return new SampleVO(112, "스타", "로드");
    }

    @GetMapping(value = "/getSample3", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SampleVO> getSample3(){
        return IntStream.rangeClosed(1, 10).mapToObj(
                value -> new SampleVO(value, value+"first", value+"last")).collect(Collectors.toList());
    }

    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        return ticket;
    }
}
