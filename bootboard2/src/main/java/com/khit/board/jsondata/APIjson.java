package com.khit.board.jsondata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class APIjson {
    public static void main(String[] args) {
        //데이터를 받기 위해서 URL 클래스의 객체 생성
        try {
            //지진해일 대피소 데이터 테스트
            String serviceKey = "cWq32bGv6s%2BYXyuviPtmB%2BnsNsq7yi9oKktaYE7NvEuuNW280DC%2BuwD69iQhnRtNu6W8QFVoBi%2FigywhpOU6qQ%3D%3D";
            String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
                    url += "?serviceKey=" + serviceKey;
                    url += "&pageNo=1";
                    url += "&numOfRows=10";
                    url +="&type=json";
            URL requestUrl = new URL(url);
            System.out.println(url);

            //openConnection()을 이용한 연결
            HttpURLConnection conn = (HttpURLConnection)requestUrl.openConnection();
            conn.setRequestMethod("GET"); //대문자로 명시함

            //응답 상태코드(200, 403, 404, 500)
            int status = conn.getResponseCode();
            System.out.println(status);

            //버퍼는 입출력속도 향상을 위해서 데이터를 일시적으로 메모리 영역에 모아두는 것
            //BufferedReader(보조스트림) : 기반스트림(생성자) - InputStreamReader
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String responseText = "";
            String line;
            while ((line = br.readLine()) != null){
                responseText += line;
            }
            System.out.println(responseText);
            br.close(); //br 종료
            conn.disconnect(); //연결 종료
            
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
}
