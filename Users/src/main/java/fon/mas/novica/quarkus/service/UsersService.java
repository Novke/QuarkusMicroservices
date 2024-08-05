package fon.mas.novica.quarkus.service;

import fon.mas.novica.quarkus.model.dto.login.UpdatePasswordCmd;
import fon.mas.novica.quarkus.model.dto.user.CreateUserCmd;
import fon.mas.novica.quarkus.model.dto.user.UserInfo;
import fon.mas.novica.quarkus.model.dto.user.UserInsight;

import java.util.List;

public interface UsersService {

    List<UserInsight> findAllUsers();

    Object createUser(CreateUserCmd cmd);

    Object createAdmin(CreateUserCmd cmd);

    List<UserInfo> findActiveUsers();

    void disableUser(String username);

    void enableUser(String username);

    void updatePassword(UpdatePasswordCmd cmd);

    UserInsight findById(Long id);

    Boolean checkAuthorization(String username, List<Long> ids);

    Integer increaseTaskCount(Long id);
}
