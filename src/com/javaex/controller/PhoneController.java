package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;
import com.javaex.util.WebUtil;


@WebServlet("/pbc") //컨트롤러의 주소 경로 이름
public class PhoneController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
	
	//생성자
	
	
	//메소드 get/set
	
	
	//메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//코드
		request.setCharacterEncoding("UTF-8");
		
		PhoneDao pDao = new PhoneDao();
		
		String action = request.getParameter("action");
		
		if("list".equals(action)) {
			//데이터 가져오기
			List<PersonVo> pList = pDao.personSelect();
			System.out.println(pList);
			
			//request를 통해서 전달할 데이터 추가
			request.setAttribute("pList", pList);
			
			//jsp 부를때 쓰는 코드
			WebUtil.forward(request, response, "WEB-INF/list.jsp");
		}
		else if("writeForm".equals(action)) {
			WebUtil.forward(request, response, "WEB-INF/writeForm.jsp");
		}
		else if("insert".equals(action)) {
			//파라미터에서 값 꺼내기(name, hp, company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo만들어서 값 초기화
			PersonVo pVo = new PersonVo(name, hp, company);
			System.out.println(pVo);
			
			//phoneDao.personInsert()
			pDao.personInsert(pVo);
			
			//redirect
			WebUtil.redirect(request, response, "./pbc?action=list");
			
		}
		else if("updateForm".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			PersonVo pVo = pDao.getPerson(id);
			
			request.setAttribute("pVo", pVo);
			
			WebUtil.forward(request, response, "WEB-INF/updateForm.jsp");
		}
		else if("update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo pVo = new PersonVo(id, name, hp, company);
			System.out.println(pVo);
			
			pDao.personUpdate(pVo);
			
			WebUtil.redirect(request, response, "./pbc?action=list");
		}
		else if("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			pDao.personDelete(id);
			
			WebUtil.redirect(request, response, "./pbc?action=list");
		}
		else {
			System.out.println("action 파라미터 없음");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
