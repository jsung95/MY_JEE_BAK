package org.zerock.myapp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.mapper.RegisterMapper;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2

@EnableAsync //Async로 딜레이 최대한 줄이기
@Service
public class RegisterServiceImpl implements RegisterService {

	
	@Setter(onMethod_ = @Autowired)
	private RegisterMapper mapper;

	@Override
	public int checkCBNO(MemberDTO dto) {
		
		int check = this.mapper.selectCheckCBNO(dto);
		
		return check;
	}

	@Override
	public Double homeTaxCBNO(MemberDTO dto) throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=SaEtDV6V2ezASF4OroNIlotaYMp84VVz%2BYxZ%2BFvB9MlN%2F7DSCPLNnNRRTZrFKSffpip1B4BvtocfTS08%2F6Hm1Q%3D%3D");
		
		
		try(client) {
			String input = Long.toString(dto.getCbno());
			String json = "{ \"b_no\":[\""+ input +"\"]}";
			StringEntity entity = new StringEntity(json);
			
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			
			CloseableHttpResponse response = client.execute(httpPost);
			
			try(response) {
				log.info("STATUS : " + response.getStatusLine().getStatusCode());
				
				HttpEntity print = response.getEntity();
				
				String result = EntityUtils.toString(print);
				log.info(result);
				
				Gson gson = new Gson();
				Map map = new HashMap();
				
				map = (Map) gson.fromJson(result, map.getClass());
				log.info("@@@ : " + map.get("match_cnt"));
				
				return (Double) map.get("match_cnt");
				
			}//try-with-resources
			
			
		}//try-with-resources
		
		
	}
	
	@Setter(onMethod_ = @Autowired)
	JavaMailSenderImpl mailSender;

	@Async //Async로 딜레이 최대한 줄이기 
	@Override
	public void sendMail(MemberDTO dto, HttpSession session) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
			
			
			String auth_key = getKey(false, 6);
			
			
			helper.setFrom("테스트메일<25800s10@gmail.com>");
			helper.setTo(dto.getEmail());
			helper.setSubject("테스트 제목asd");
			helper.setText("인증키 : " + auth_key);
			
//			msg.addRecipient(RecipientType.TO, new InternetAddress("jsung9912@gmail.com"));
//			
//			msg.setFrom(new InternetAddress("25800s10@gmail.com"));
//			msg.setSubject("테스트 제목asd", "utf-8");
//			msg.setText("본문입니다. asd", "utf-8");
			
			mailSender.send(msg);
			
			session.removeAttribute("auth");
			session.setAttribute("auth", auth_key);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
	
	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	@Override
	public boolean emailCertifiation(HttpSession session, String inputCode) {
		
		try {
			
			String code = (String) session.getAttribute("auth");
			log.info("&&code : " + code);
			log.info("&&inputCode : " + inputCode);
			if(code.equals(inputCode)) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}//

	@Override
	public void register(MemberDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		dto.setMemberpw(digest(dto));
		this.mapper.insertMember(dto);
		
	}//register

	@Override
	public void uploadFile(AttachFileDTO dto) {
		this.mapper.insertFile(dto);
		
	}//FILE UPLOAD
	
	
	@Override
	public String digest(MemberDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String str = dto.getMemberpw();
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
        
        salt.update(str.getBytes("UTF-8"));
        
        String digest = bytesToHex(salt.digest());
		
		return digest;
	}//HASH
	
	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
	
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        } // for
        
        return new String(hexChars);
    } // bytesToHex
	
}
