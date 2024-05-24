package com.springlab.biz.board.controller2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;
import com.springlab.biz.user.domain.UserDO;
import com.springlab.biz.user.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@Controller
@RequestMapping("/")
@SessionAttributes(value = {"user", "board"})
public class BoardController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
	@GetMapping("login")
	public String login() {
		//String 리턴값을 jsp Name 해석한다.
		return "login";
	}
//	@RequestMapping(value="/login", method=RequestMethod.POST)
	@PostMapping("login")
	//	public String loginProc(HttpServletRequest request) {
	/*
	 * public String loginProc(
	 * 
	 * @RequestParam("id") String id,
	 * 
	 * @RequestParam("password") String password, Model model) {
	 */
		public String loginProc(
				//DO 객체의 속성 이름과 pram의 이름이 같은 경우에 사용 가능 아니면 위에 RequestParam으로 직접 읽어야함
				UserDO user,
				Model model) {
		//step 1. get request parameters
		
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
		
		if(user.getId() == null || user.getId().isBlank()) {
			throw new IllegalArgumentException("사용자 ID를 입력하십시오.");
		}
		if(user.getPassword() == null || user.getPassword().isBlank()) {
			throw new IllegalArgumentException("사용자 PW를 입력하십시오.");
		}
		//step 2. data processing by business logic
		/*
		 * UserDO user = new UserDO(); 
		 * user.setId(id); 
		 * user.setPassword(password);
		 */
		
		UserDO user1 = userService.getUser(user);
		String viewName = null;
		//step 3. output results --> route to views
		if (user1 != null && user1.getPassword().equals(user.getPassword())) {
//			request.getSession().setAttribute("user", user);
			model.addAttribute("user", user1);
			viewName = "redirect:getBoardList";
		}
		else {
			viewName = "redirect:login";
		}

		return viewName;
	}
	
	
	@RequestMapping("getBoardList")
	public String getBoardList(
			BoardDO board,
			Model model) {
		System.out.println(">>> 게시글 목록 처리...");
		
		// step #1. get request parameters
		
		// step #2. data processing
		//그냥 리스트 출력이면 board의 모든 값이 NULL이 되고 Post 요청에 두 search 값이 바인딩된 채로 들어온다.
		List<BoardDO> boardList = boardService.getBoardList(board);
		Map<String,String> conditionMap = new LinkedHashMap<String, String>();
		conditionMap.put("TITLE", "제목");
		conditionMap.put("CONTENT", "내용");
		conditionMap.put("WRITER", "작성자");
		
		
		model.addAttribute("board_list", boardList);
		model.addAttribute("conditionMap", conditionMap);
		return "getBoardList";
	}
	
	@RequestMapping("getBoard")
	public String getBoard(
			BoardDO board,
			Model model) {
		System.out.println(">>> 게시글 상세보기 처리...");
		
		
		board = boardService.getBoard(board);		
		model.addAttribute("board", board);
		
		
		
		return "getBoard";
	}
	@GetMapping("insertBoard")
	public String insertBoard() {
		return "insertBoard";
	}
//	@PostMapping("insertBoard")
//	public String insertBoardProc(
//			HttpServletRequest request) throws IOException, ServletException {
//		System.out.println(">>> 게시글 등록 처리...");
//		BoardDO board = new BoardDO();
//		board.setTitle(request.getParameter("title"));
//		board.setContent(request.getParameter("content"));
//		board.setWriter(request.getParameter("writer"));
//		
//		String uploadDir = request.getServletContext().getRealPath("/WEB-INF/upload");
//		System.out.println(uploadDir);
//		File uploadDirFile = new File(uploadDir);
//		if(!uploadDirFile.exists()) {
//			uploadDirFile.mkdirs();
//		}
//		
//		
//		Collection<Part> parts = request.getParts();
//		StringBuilder sb = new StringBuilder();
//		// upload append file
//		for(Part part : parts) {
//			
//		if(!part.getName().equals("uploadFile")) continue;
//		
//		if(part.getSize() > 0)continue; 
//			String fileName = part.getSubmittedFileName();
//			sb.append(fileName);
//			sb.append(",");
//			InputStream fis = part.getInputStream();
//			String filePath = 
//					uploadDir
//					+ File.separator
//					+ part.getSubmittedFileName()
//					+ fileName;
//			FileOutputStream fos = new FileOutputStream(filePath);
//			
//			byte[] buf = new byte[1024];
//			int size = 0;
//			while ((size = fis.read(buf)) != -1) {
//				fos.write(buf, 0, size);
//			}
//			fis.close();
//			fos.close();
//		
//	}
//		int length = sb.length();
//		if(length > 0) {
//			sb.delete(length-1, length);
//		}
//		board.setUploadFiles(sb.toString());
//		//register to DB
//		System.out.println(board);
//		boardService.insertBoard(board);
//					
//		return "redirect:getBoardList";
//	}
	@PostMapping("insertBoard")
	public String insertBoardProc(
			HttpServletRequest request,
			BoardDO board,
			@RequestParam("uploadFile")MultipartFile[] uploadFile) throws IOException, ServletException {
		System.out.println(">>> 게시글 등록 처리...");
		
		
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		
		// upload append file
		String uploadDir = request.getServletContext().getRealPath("/upload");
		System.out.println(uploadDir);
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}
		
		
		StringBuilder sb = new StringBuilder();
		// upload append file
		for(MultipartFile mf : uploadFile) {
			
		if(mf.isEmpty())continue; 
		
			String fileName = mf.getOriginalFilename();
			sb.append(fileName);
			sb.append(",");
			
			String filePath = 
					uploadDir
					+ File.separator
					+ fileName;
			
			mf.transferTo(new File(filePath));
			}
		
		int length = sb.length();
		
		if(length > 0) {
			sb.delete(length-1, length);
			board.setUploadFiles(sb.toString());
		}
		else {
			board.setUploadFiles(null);
		}
		
		//register to DB
		System.out.println(board);
		boardService.insertBoard(board);
					
		return "redirect:getBoardList";
	}
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("board") BoardDO board) {
		System.out.println(">>> 게시글 수정 처리");
		
		BoardDO regBoard = boardService.getBoard(board);
		
		if (!regBoard.getTitle().equals(board.getTitle()) ||
				!regBoard.getContent().equals(board.getContent()) || 
				(regBoard.getCnt() != board.getCnt())) {
				boardService.updateBoard(board);
			}
		
		return "redirect:getBoardList";
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(BoardDO board) {
		System.out.println(">>> 게시글 삭제 처리");

		boardService.deleteBoard(board);
		
		return "redirect:getBoardList";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println(">>> 로그아웃 처리");
		
		session.invalidate();
		
		return "redirect:login";	
	}
}
