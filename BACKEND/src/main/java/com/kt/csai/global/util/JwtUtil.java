package com.kt.csai.global.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.csai.domain.authentication.payload.AuthDto;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
	public final static long TOKEN_VALIDATION_SECOND = 1000L * 60; // access token은 1분
	public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60; // 60분마다 만료 (보안 요건에 맞게 수정)

	public final static String ACCESS_TOKEN_NAME = "accessToken";
	public final static String REFRESH_TOKEN_NAME = "refreshToken";
	
	private final ObjectMapperUtil objectMapperUtil;

	@Value("${spring.jwt.secret}")
	private String SECRET_KEY;

	private Key getSigningKey(String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * 토큰 페이로드 반환
	 * 
	 * @param token
	 * @return
	 * @throws ExpiredJwtException
	 */
	public Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token).getBody();
	}

	/**
	 * 페이로드에서 get empNo
	 * 
	 * @param token
	 * @return
	 */
	public String getEmpNo(String token) {
		return extractAllClaims(token).get("empNo", String.class);
	}
	
	public String getUserInfo(String token) {
		return extractAllClaims(token).get("userInfo", String.class);
	}
	
	public ResponseSigninVo convertTokenToUserInfo(String userInfo) {
		return objectMapperUtil.convertValue(userInfo, ResponseSigninVo.class);
	}

	/**
	 * 토큰 유효 시간 갱신
	 * 
	 * @param token
	 * @param expireTime
	 */
	public void renewalExpireTime(String token, long expireTime) {
		extractAllClaims(token).setExpiration(new Date(System.currentTimeMillis() + expireTime));
	}

	/**
	 * 토큰 만료 확인
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenExpired(String token) {
		try {
			final Date expiration = extractAllClaims(token).getExpiration();
			return expiration.before(new Date());
		} catch (ExpiredJwtException expiredJwtException) {
			log.info(String.format("[EXPIRED TOKEN : %s", token));
			return true;
		}

	}

	/**
	 * 토큰 생성 - access token
	 * 
	 * @param empNo
	 * @return
	 */
	public String generateToken(String empNo) {
		return doGenerateToken(empNo, TOKEN_VALIDATION_SECOND);
	}
	
	public String generateToken(ResponseSigninVo userInfo) {
		return doGenerateToken(userInfo, TOKEN_VALIDATION_SECOND);
	}

	/**
	 * 토큰 생성 - refresh token
	 * 
	 * @param empNo
	 * @return
	 */
	public String generateRefreshToken(String empNo) {
		return doGenerateToken(empNo, REFRESH_TOKEN_VALIDATION_SECOND);
	}
	
	public String generateRefreshToken(ResponseSigninVo userInfo) {
		return doGenerateToken(userInfo, REFRESH_TOKEN_VALIDATION_SECOND);
	}

	/**
	 * 토큰 생성, 페이로드는 empNo(사번)
	 * 
	 * @param empNo
	 * @param expireTime
	 * @return
	 */
	public String doGenerateToken(String empNo, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("empNo", empNo);

		String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256).compact();

		return jwt;
	}
	
	public String doGenerateToken(ResponseSigninVo userInfo, long expireTime) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(userInfo, Map.class);
		Claims claims = Jwts.claims();
		
		claims.put("userInfo", map.toString());

		String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256).compact();

		return jwt;
	}

	/**
	 * 토큰 검증j
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		try {
			final String empNo = getEmpNo(token);
			return (empNo.equals(userDetails.getUsername()) && !isTokenExpired(token));
		} catch (ExpiredJwtException expiredJwtException) {
			log.info(String.format("[INVALID TOKEN] : %s", token));
			return false;
		}
	}

	/**
	 * 토큰 검증
	 * 
	 * @param token
	 * @param authDto
	 * @return
	 */
	public boolean validateToken(String token, AuthDto authDto) {
		if (authDto == null) {
			log.info("[NULL] : Auth Info");
			return false;
		}

		try {
			return token.equals(authDto.getRefreshToken());
		} catch (ExpiredJwtException expiredJwtException) {
			log.info(String.format("[INVALID TOKEN] : %s", token));
			return false;
		}
	}

	/**
	 * 토큰 검증
	 * 
	 * @param token
	 * @param empNo
	 * @return
	 */
	public boolean validateToken(String token, String empNo) {
		try {
			return (empNo.equals(getEmpNo(token)) && !isTokenExpired(token));
		} catch (ExpiredJwtException expiredJwtException) {
			log.info(String.format("[INVALID TOKEN] : %s", token));
			return false;
		}
	}
}
