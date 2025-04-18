package com.geek.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geek.dto.BorrowBookDto;
import com.geek.dto.LoginDto;
import com.geek.entity.Book;
import com.geek.entity.User;
import com.geek.repository.UserRepository;
import com.geek.secure.config.JwtTokenUtil;
import com.geek.secure.service.JwtUserDetailsService;
import com.geek.service.UserService;
@RestController
@RequestMapping("users")
public class UserApi {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping("/register")
	public ResponseEntity<User> addNewUser(@RequestBody User u) {
		//insert or update
		return  new ResponseEntity<User>(userService.addNewUser(u),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public String login( @RequestBody LoginDto loginDto) {
		authenticate(loginDto.getUsername(), loginDto.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        //to get the roles....
		//  userDetails.getAuthorities()
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
	}
	@Autowired
	private UserRepository userRepo; //Not good practice
	
	@PostMapping("/borrowBook")
	@PreAuthorize("hasAuthority('user')")
	public ResponseEntity<Book> borrowBook(@RequestBody BorrowBookDto borrowDto,Principal p){
	
		return new ResponseEntity<Book>(userService.borrowBook(borrowDto,p.getName()),HttpStatus.CREATED);
	}
	
	@PutMapping("/returnBook/{tid}")
	public ResponseEntity<Book> returnBook( @PathVariable int tid ){
		return new ResponseEntity<Book>(userService.returnBook(tid),HttpStatus.OK);
	}
	private void authenticate(String username, String password)  {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			//throw new Exception("USER_DISABLED", e);
			e.printStackTrace();
		} catch (BadCredentialsException e) {
			//throw new Exception("INVALID_CREDENTIALS", e);
			e.printStackTrace();
		}
	}
}
