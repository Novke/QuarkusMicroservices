package fon.mas.novica.quarkus.service;

import fon.mas.novica.quarkus.model.dto.user.UserInfo;

import java.util.List;

public interface UsersService {

    List<UserInfo> findAll();
}
