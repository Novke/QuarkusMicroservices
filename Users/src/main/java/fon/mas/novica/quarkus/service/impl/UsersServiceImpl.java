package fon.mas.novica.quarkus.service.impl;

import fon.mas.novica.quarkus.model.dto.user.UserInfo;
import fon.mas.novica.quarkus.model.entity.UserEntity;
import fon.mas.novica.quarkus.service.UsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    ModelMapper mapper;

    @Override
    public List<UserInfo> findAll() {
        List<UserEntity> users = UserEntity.listAll();
        return users.stream().map(u -> mapper.map(u, UserInfo.class)).toList();
    }
}
