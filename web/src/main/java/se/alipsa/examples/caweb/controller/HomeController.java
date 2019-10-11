package se.alipsa.examples.caweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import se.alipsa.examples.caweb.service.ReportService;

@Controller
public class HomeController {
  
  private ReportService reportService;
  
  @Autowired
  public HomeController(ReportService reportService) {
    this.reportService = reportService;
  }
  
  @GetMapping("/")
  public String index() {
    return "index.html";
  }
  
  @GetMapping("/ringlist")
  public String ringList() {
    return "ringlist.html";
  }
}