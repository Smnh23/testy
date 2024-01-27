package com.holitor.holitorservice.module.user.batch.users;

import com.holitor.holitorservice.module.image.service.ImageService;
import com.holitor.holitorservice.module.user.model.User;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;

public class UserProcessor implements ItemProcessor<UserData, User> {

	@Override
	public User process(@NonNull UserData userData) throws Exception {
    User user = new User();
    user.setIdOkta(userData.getIdOkta());
    user.setPseudo(userData.getPseudo());
    user.setEmail(userData.getEmail());
    user.setImage(ImageService.readImage(ResourceUtils.getFile("classpath:"+userData.getImage())));
		return user;
	}

}