package fon.mas.novica.quarkus.service;

import fon.mas.novica.quarkus.model.dto.login.UpdatePasswordCmd;
import fon.mas.novica.quarkus.model.dto.user.CreateUserCmd;
import fon.mas.novica.quarkus.model.dto.user.UserInfo;

import java.util.List;

public interface UsersService {

    List<UserInfo> findAllUsers();

    Object createUser(CreateUserCmd cmd);

    Object createAdmin(CreateUserCmd cmd);

    List<UserInfo> findActiveUsers();

    void disableUser();

    void enableUser();

    void updatePassword(UpdatePasswordCmd cmd);
}
