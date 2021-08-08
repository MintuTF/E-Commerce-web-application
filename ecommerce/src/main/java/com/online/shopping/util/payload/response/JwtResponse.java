package com.online.shopping.util.payload.response;

import com.online.shopping.util.userdetails.UserDetailsimp;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private UserDetailsimp detailsimp;



	public JwtResponse(String accessToken,UserDetailsimp detailsimp ) {
		this.token = accessToken;

		this.detailsimp=detailsimp;
	}


}
