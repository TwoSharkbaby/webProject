package org.web.project.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.web.project.domain.Criteria;
import org.web.project.domain.ReplyPageDTO;
import org.web.project.domain.ReplyVO;
import org.web.project.service.ReplyService;

@RestController // REST + Controller
@RequestMapping("/replies")
@RequiredArgsConstructor
@Log4j
public class ReplyController {

	private final ReplyService replyService;

	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(
			value = "/{rno}", 
			consumes = "application/json", 
			produces = {MediaType.TEXT_PLAIN_VALUE },
			method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
		
		vo.setRno(rno);
		
		return replyService.modify(vo) == 1
		? new ResponseEntity<>("success", HttpStatus.OK)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
		log.info("remove : " + rno);
		return replyService.remove(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
	}

	@GetMapping(value = "/pages/{bno}/{page}", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		log.info(".............." + page);
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<>(replyService.getListPage(cri, bno), HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", 
			consumes = "application/json", 
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO reply) {
		log.info("ReplyVO" + reply);

		int insertCount = replyService.register(reply);

		return (insertCount == 1) ? new ResponseEntity<>("sucess", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
