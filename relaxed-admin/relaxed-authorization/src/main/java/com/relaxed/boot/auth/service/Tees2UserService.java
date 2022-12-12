package com.relaxed.boot.auth.service;

import com.relaxed.oauth2.auth.extension.ExtendUserDetailsService;
import com.relaxed.oauth2.common.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Yakir
 * @Topic UserServuce
 * @Description
 * @date 2022/7/21 13:53
 * @Version 1.0
 */
@RequiredArgsConstructor
public class Tees2UserService implements ExtendUserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// List<User> findUserList = userList.stream().filter(user ->
		// user.getUsername().equals(username))
		// .collect(Collectors.toList());
		// if (!CollectionUtils.isEmpty(findUserList)) {
		// User user = findUserList.get(0);
		// User user1 = ObjectUtil.cloneByStream(user);
		// ;
		// return user1;
		// }
		// else {
		// throw new UsernameNotFoundException("用户名或密码错误");
		// }
		String password = PasswordUtils.encode("123456");
		// if (!username.equals("admin")) {
		// throw new UsernameNotFoundException("用户名或密码错误");
		//
		// }
		return new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("macro-test2"));

	}

	@Override
	public UserDetails loginByMobile(String mobile) {
		String password = PasswordUtils.encode("123456");
		return new User("18668389786", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin-test2"));
	}

	public static void main(String[] args) {
		String password = PasswordUtils.encode("123456");

		System.out.println(password);
	}

}
