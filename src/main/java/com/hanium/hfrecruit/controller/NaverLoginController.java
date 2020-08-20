package com.hanium.hfrecruit.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

//클라이언트키와 콜백URL을 네이버에 보냄->네이버가 유효성 확인하고 접근 토큰, 갱신 토큰, 유효 시간 정보를 보내줌->회원정보 가져옴
@Controller
public class NaverLoginController {
    private static final String CLIENT_ID = "RRmaWc3aGqrvUcYP2rTE";
    private static final String CLIENT_SECRET = "l7GxrKFhpg";

    //로그인 페이지
    @RequestMapping("/naver-login")
    public String naverLogin(HttpSession session, Model model) throws UnsupportedEncodingException {
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver-login/callback", "UTF-8");
        String state = new BigInteger(130, new SecureRandom()).toString();

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;

        session.setAttribute("state", state);
        model.addAttribute("apiURL", apiURL);

        return "naver-login";
    }

    //CALLBACK 페이지
    @RequestMapping("/naver-login/callback")
    public String naverLoginCallback(HttpSession session, HttpServletRequest httpServletRequest, Model model) throws IOException, ParseException {
        String code = httpServletRequest.getParameter("code");
        String state = httpServletRequest.getParameter("state");
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver-login/callback", "UTF-8");

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
//        String access_token = "";
//        String refresh_token = "";

        System.out.println("apiURL=" + apiURL);

        String result = requestToServer(apiURL);
        model.addAttribute("result", result);

//       if (result != null && !result.equals("")) {
//            model.addAttribute("result", result);
//            Map<String, Object> parsedJson = new JSONParser(result).parseObject();
//            System.out.println(parsedJson);
//           session.setAttribute("currentUser", result);
//           session.setAttribute("currentAT", parsedJson.get("access_token"));
//           session.setAttribute("currentRT", parsedJson.get("refresh_token"));
//        } else {
//            model.addAttribute("result", "Login failed!");
//        }
        return "naver-login-callback";
    }

    //토큰 갱신 요청 페이지
    @RequestMapping("/naver-login/refreshToken")
    public String refreshToken(HttpSession session, Model model, String refreshToken) throws IOException, ParseException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
        apiURL += "&refresh_token=" + refreshToken;
        System.out.println("apiURL=" + apiURL);

        String result = requestToServer(apiURL);
        model.addAttribute("result", result);
        session.invalidate();
        return "naver-login-callback";
    }

    //토큰 삭제 페이지
    @RequestMapping("/naver-login/deleteToken")
    public String deleteToken(HttpSession session, Model model, String accessToken) throws IOException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
        apiURL += "&access_token=" + accessToken;
        apiURL += "&service_provider=NAVER";
        System.out.println("apiURL=" + apiURL);

        String result = requestToServer(apiURL);
        model.addAttribute("result", result);
        session.invalidate();
        return "naver-login-callback";
    }

    //프로필 받기
    @ResponseBody
    @RequestMapping("/naver-login/getProfile")
    public String getProfile(String accessToken) throws IOException {
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerString = "Bearer " + accessToken;
        return requestToServer(apiURL, headerString);
    }

    @RequestMapping("/naver-login/logout")
    public String invalidateSession(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/naver-login";
    }

    //통신
    private String requestToServer(String apiURL) throws IOException {
        return requestToServer(apiURL, "");
    }

    private String requestToServer(String apiURL, String headerString) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        System.out.println("header Str: " + headerString);
        if (!headerString.equals("") && headerString != null) {
            httpURLConnection.setRequestProperty("Authorization", headerString);
        }

        int responseCode = httpURLConnection.getResponseCode();
        BufferedReader bufferedReader;
        System.out.println("responseCode=" + responseCode);

        if (responseCode == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        }

        String inputLine;
        StringBuffer result = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            inputLine = bufferedReader.readLine();
            result.append(inputLine);
        }

        bufferedReader.close();
        if (responseCode == 200) {
            return result.toString();
        } else {
            return null;
        }
    }
}
