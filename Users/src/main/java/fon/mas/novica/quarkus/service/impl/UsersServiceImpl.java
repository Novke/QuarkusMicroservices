package fon.mas.novica.quarkus.service.impl;

import fon.mas.novica.quarkus.exception.BadCredentialsException;
import fon.mas.novica.quarkus.exception.UserAlreadyDisabledException;
import fon.mas.novica.quarkus.exception.UserAlreadyEnabledException;
import fon.mas.novica.quarkus.exception.UserNotFoundException;
import fon.mas.novica.quarkus.model.dto.login.UpdatePasswordCmd;
import fon.mas.novica.quarkus.model.dto.user.CreateUserCmd;
import fon.mas.novica.quarkus.model.dto.user.UserInfo;
import fon.mas.novica.quarkus.model.dto.user.UserInsight;
import fon.mas.novica.quarkus.model.entity.RoleEntity;
import fon.mas.novica.quarkus.model.entity.UserEntity;
import fon.mas.novica.quarkus.service.UsersService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
@Transactional
public class UsersServiceImpl implements UsersService {

    @Inject
    ModelMapper mapper;

    @Override
    public List<UserInsight> findAllUsers() {
        List<UserEntity> users = UserEntity.listAll();
        return users.stream().map(u -> mapper.map(u, UserInsight.class)).toList();
    }

    @Override
    public Object createUser(CreateUserCmd cmd) {
        UserEntity user = mapper.map(cmd, UserEntity.class);
        user.role = RoleEntity.findById(1L);
        user.password = BcryptUtil.bcryptHash(user.password);
        UserEntity.persist(user);
        return mapper.map(user, UserInfo.class);
    }

    @Override
    public Object createAdmin(CreateUserCmd cmd) {
        UserEntity user = mapper.map(cmd, UserEntity.class);
        user.role = RoleEntity.findById(2L);
        user.password = BcryptUtil.bcryptHash(user.password);
        UserEntity.persist(user);
        return mapper.map(user, UserInfo.class);
    }

    @Override
    public List<UserInfo> findActiveUsers() {
        return UserEntity.findActiveUsers().stream()
                .map(u -> mapper.map(u, UserInfo.class))
                .toList();
    }

    @Override
    public void disableUser(String username) {
        UserEntity user = UserEntity.findByUsername(username);
        if (user == null) throw new UserNotFoundException("User with username " + username + " not found");
        if (!user.enabled) throw new UserAlreadyDisabledException(username);

        user.enabled = false;
        UserEntity.persist(user);
    }

    @Override
    public void enableUser(String username) {
        UserEntity user = UserEntity.findByUsername(username);
        if (user == null) throw new UserNotFoundException("User with username " + username + " not found");
        if (user.enabled) throw new UserAlreadyEnabledException(username);

        user.enabled = true;
        UserEntity.persist(user);
    }

    @Override
    public void updatePassword(UpdatePasswordCmd cmd) {
        UserEntity user = UserEntity.findByUsername(cmd.getUsername());
        if (user == null) throw new BadCredentialsException("Wrong username or password");

        if (BcryptUtil.matches(cmd.getConfirmPassword(), user.password)){
            user.password = BcryptUtil.bcryptHash(cmd.getNewPassword());
        } else throw new BadCredentialsException("Wrong username or password");
    }
}
