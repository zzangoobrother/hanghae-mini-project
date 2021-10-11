package com.miniproject.spring.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 jwt 토큰 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);

        ///////////////////   JWT 토큰 인증방식 순서   ///////////////////
        // 0. 서버가 클라이언트에게 JWT 발급해줌 -> 유저이름, 이메일, 토큰생성시간, 만료시간 등의 정보 포함.
        // 1. 클라이언트가 기능 수행을 위해
        // 서버에 Data(서버-클라이언트 간 주고받기로 한 약속. ex/ 글쓰기 기능에는 "title", "content", "imageUrl"이 포함)와 JWT를 묶어서 보냄.
        // 2. 서버는 클라이언트에게 받은 JWT를 doFilter로 들여와 검증함.
        // 3. Filter를 거치면서 validate되지 않고, authentication이 생성되지 않는다면 클라이언트는 기능 수행이 불가함.
        // 4. getAuthentication이 되었다면 이를 SecurityContextHolder에 저장해주어 인증상태를 유지하도록 해줌.
        // ==== UserDetailsImpl에 담기게 됨!!!
        // 5.
        // 5. response 결과로 클라이언트가 요청한 부분을 처리해서 그 결과를 보내줌.
    }
}
