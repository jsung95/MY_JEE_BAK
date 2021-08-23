package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@ToString

//@WebServlet("/Hello2")
public class HelloServlet2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   ArrayList<Integer> arr = new ArrayList<Integer>();
   
   StringBuffer stbf = new StringBuffer();

   
   int randNum(){
      int num = 0;
      
      while(true) {
         num = (int)(Math.random() * 45) +1;
         if(!arr.contains(num)) {
            break;
         } //if
         
      }//while
      
      return num;
   }//randNum
 
   
   protected void service(HttpServletRequest req, HttpServletResponse res) 
         throws ServletException, IOException {
      log.debug("service(req, res) invoked.");
      
      PrintWriter out = res.getWriter();
      
      int num = randNum();
      out.println(num);
      
      arr.add(num);
      
      out.println(arr);
      
      if(arr.size() == 45) {
          arr.clear();
      } //if

      
      out.flush();
      out.close();
   }//service

}//end class