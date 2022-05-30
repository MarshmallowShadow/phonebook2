package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
	
	//생성자
	
	
	//메소드 get/set
	
	
	//메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//코드
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if("list".equals(action)) {
			//데이터 가져오기
			PhoneDao pDao = new PhoneDao();
			List<PersonVo> pList = pDao.personSelect();
			System.out.println(pList);
			
			//request에 데이터 추가
			request.setAttribute("pList", pList);
			
			//데이터 + html --> jsp시킨다
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
		}
		else if("writeForm".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
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
			PhoneDao pDao = new PhoneDao();
			pDao.personInsert(pVo);
			
			//redirect
			response.sendRedirect("./pbc?action=list");
			
		}
		else if("updateForm".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			PhoneDao pDao = new PhoneDao();
			PersonVo pVo = pDao.getPerson(id);
			
			request.setAttribute("pVo", pVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
		}
		else if("update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo pVo = new PersonVo(id, name, hp, company);
			System.out.println(pVo);
			
			PhoneDao pDao = new PhoneDao();
			pDao.personUpdate(pVo);
			
			response.sendRedirect("./pbc?action=list");
		}
		else if("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			PhoneDao pDao = new PhoneDao();
			pDao.personDelete(id);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		else {
			System.out.println("action 파라미터 없음");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
