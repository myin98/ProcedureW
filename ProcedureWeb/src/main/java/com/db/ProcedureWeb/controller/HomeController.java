package com.db.ProcedureWeb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@Autowired @Qualifier("sql1")
	private SqlSession sql;
	
	@GetMapping("/list")
    public String list(Model model) {
      //  model.addAttribute("list", new String[] {"연습1","연습2","연습3"});
        List<Map<String, Object>> list = sql.selectList("sql.list");
        if(list != null) {
        	for(Map row : list) {
        		log.info("Row : {}", row);
        	}
        }
        
        model.addAttribute("list", list);
        return "home";
    }
	
	@GetMapping("/insert")
	public String insert() {
		sql.selectOne("sql.insert", new HashMap<String, Object>() {{
			put("nm", "미역");
			put("gender", false);
		}});
		return "redirect:/list";
	}
	
	@GetMapping("/update")
	public String update() {
		sql.selectOne("sql.update", new HashMap<String, Object>() {{
			put("no", 16);
			put("nm", "다시마");
		}});
		return "redirect:/list";
	}

	@GetMapping("/delete")
	public String delete() {
		sql.selectOne("sql.delete", 16);
	return "redirect:/list";
	}

	
	
}
