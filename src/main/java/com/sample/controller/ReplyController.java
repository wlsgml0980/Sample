package com.sample.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.ReplyService;
import com.sample.vo.ReplyVO;



/*
 * ...최근 Open API에서 많이 사용되면서 REST방식으로 제공되는 외부 연결 URI를 REST API라함.
 * ...REST 방식의 서비스 제공이 가능한 것을 'Restful'하다고 표현함.
 * ...
 * ...스프링4~ : @RestController 사용
 * ...         JSP와 같은 뷰를 만드는것이 목적이 아닌 REST 방식의 데이터 처리를 위해 사용함.
 * 
 * ...스프링3~ : @ResponseBody를 지원하면서 본격적인 REST 방식의 처리를 지원함.
 * ...
 * ...~스프링3 : Content Negotiation...을 이용해서 처리함.
 *               스프링3까지는 주로 @Controller만을 사용하고, 화면처리를 담당하는
 *               JSP가 아닌 데이터 자체를 서비스하려면 해당 메서드나 리턴타입에
 *               '@ResponseBody'를 추가하는 형태로 작성했음.
 *               과거에는 개발자가 직접 MIME타입을 지정하고, 해당 데이터를 만드는 방식이었음.
 * 
 * ...REST 방식을 이용하려면 우선 컨트롤러를 먼저 작성하고, 그에 맞는 URI를
 * ...결정하는 것이 첫단계임.
 * 
 *  
 *  ...@RestController를 이용하는 경우에는 데이터만 만들어서 전송하므로,
 *  ...화면처리에 개발이 집중이 됨.
 *  ...앱에서는 HTTP 방식으로 데이터를 주고받는 라이브러리등을 활용해서 처리함.
 *  ...웹에서는 Ajax를 이용해서 처리함.
 *  -- 태호 추가 
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private ReplyService service;

	/*
	 * ...REST 방식 처리에 사용하는 특별한 어노테이션.
	 * ...@PathVariable : URI의 경로에서 원하는 데이터를 추출함.
	 * ...@RequestBody : 전송된 JSON데이터를 객체로 변환해주는 어노테이션으로
	 * ...@ModelAttribute와 유사한 역할을 하지만 JSON에서 사용된다는 차이가 있음.
	 * 
	 * 
	 * ...ResponseEntity<String>반환형을 사용하여, 댓글등록에 실패하면
	 * ...try~ catch~ 선언처리로 예외의 원인메시지를 전송하고
	 * ...사용자에게는 BAD_REQEUST(400)을 결과로 전송함.
	 * ...데이터 전송방식은 JSON을 이용하므로 @RequestBody를 사용함.
	 * 
	 * ...Advanced REST Cient 이용 테스트.
	 * ...http://localhost/sample/replies
			Content-Type: application/json
			{
			  "content" : "댓글을 추가합니다",
			  "no"       : "1"
			}
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertReply(@RequestBody ReplyVO vo) {
	
		logger.info("insert POST called ...........");
				
		ResponseEntity<String> entity = null;
		try {
			service.insertReply(vo);
			logger.info("OK... vo = " + vo.toString());	
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/*
	 * ...@RequestMapping(value = "/all/{no}"...)를 보면 URI내의 경로 {no}를 활용함.
	 * ...{no}는 메서드의 파라미터에서 @PathVariable("no")로 활용됨.
	 * 
	 * ...아래 URI에서 1638456은 게시글 번호임.
	 * ...http://localhost/sample/replies/all/1638456
	      Content-Type: application/json
	 */
	@RequestMapping(value = "/all/{no}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> selectReplies(@PathVariable("no") Integer no) {

		logger.info("selectReplies GET called ...........");						
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(service.selectReplies(no),  HttpStatus.OK);
			logger.info("OK... bno = " + no);
		
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		return entity;
		
	}

	/*
	 * ...REST방식에서 update작업은 PUT, PATCH방식을 이용해서 처리함.
	 * ...PUT : 전체 데이터를 수정하는 경우 사용.
	 * ...PATCH : 일부 데이터를 수정하는 경우 사용. 
	 * ...@RequestMapping(value = "/{no}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	 * ...에서는 PUT, PATCH 방식 모두 사용할 수 있도록 선언되 있음.
	 * 
	 * ...아래 URI에서 11은 댓글번호임.
	 * ...http://localhost:8080/sample/replies/11
			Content-Type: application/json
			{
			 "content" : "댓글을 수정"
			 }
	 *
	 * ...GET/POST 방식만 지원하는 브라우저의 경우를 대비하기 위해 HiddenHttpMethodFilter를
	 *    web.xml에 필터 설정을 추가함.
	 *    form 태그를 이용해서 전송하는 경우 POST 방식으로 전송하되, '_method' 라는 추가적인 정보를 이용함.
	 *
	 */
	@RequestMapping(value = "/{no}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> updateReply(@PathVariable("no") Integer no,   @RequestBody ReplyVO vo) {
	
		logger.info("update PUT/PATCH called ...........");
		
		ResponseEntity<String> entity = null;
		try {
			vo.setNo(no);
			service.updateReply(vo);
			logger.info("OK... vo = " + vo.toString());
			
		entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}

	/*
	 * ...아래 URI에서 4은 댓글번호임. 
	 * ...http://localhost:8080/sample/replies/4
			Content-Type: application/json
	 */
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReply(@PathVariable("no") Integer no) {
	
		logger.info("delete PUT/PATCH called ...........");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteReply(no);
			logger.info("OK... no = " + no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
}
