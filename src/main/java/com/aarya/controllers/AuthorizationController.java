package com.aarya.controllers;

import org.javacord.api.DiscordApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    private DiscordApi api;
    private final String template = "client_id=%s&client_secret=%s&code=%s&redirect_uri=%s&scope=%s&grant_type=authorization_code";


    @Autowired
    public AuthorizationController(DiscordApi api){
        this.api = api;
    }

    @RequestMapping("/discordOAuthCode")
    public ModelAndView getCode(HttpServletRequest req, HttpServletResponse res, ModelAndView mv) throws Exception{
        String code = req.getParameter("code");
        if(code != null) {
            String scope = "identify email";
            String redirectUri = "http://localhost:5000/authorization/discordOAuthAccessToken";
//            String grantType = "authorization_code";
            String clientSecret = "n5VBeXXISeX9s1RuUB6Bm91l1M40GfMV";
            String clientId = "795488321974304791";

            String requestBody = String.format(template, clientId, clientSecret, code, redirectUri, scope);
            System.out.println(requestBody);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://discord.com/api/oauth2/token"))
                    .setHeader("Content-Type", "x-www-form-urlencoded")
                    .build();
//            client.send(request);

            mv.setViewName("index.html");
        } else {
        }
        return mv;
    }
}




































//            URL discordToken = new URL("https://discord.com/api/oauth2/token");
//            HttpURLConnection connection = (HttpURLConnection) discordToken.openConnection();
//
//            connection.setRequestProperty("Content-Type", "x-www-form-urlencoded");
//            connection.setRequestMethod("POST");
//
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//
//
//            OutputStream os = connection.getOutputStream();
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//            bw.write(requestBody);
//            os.close();
//            bw.close();
//
//            InputStream is = connection.getErrorStream() != null ? connection.getErrorStream() : connection.getInputStream();
//
//            System.out.println(is.readAllBytes().length);
//            System.out.println(is == null);
//            Scanner scanner = new Scanner(is);
//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine() + "hello world");
//            }
//            scanner.close();
//            is.close();
