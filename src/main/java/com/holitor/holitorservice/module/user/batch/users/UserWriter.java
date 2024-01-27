package com.holitor.holitorservice.module.user.batch.users;

import java.util.List;

import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.repository.UserRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class UserWriter implements ItemWriter<User> {

  private @Autowired UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		users.stream().forEach(user -> userRepository.save(user));
	}

}