package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.model.dto.users.UserInfo;

import java.util.List;

public interface UsersServiceClient {
    boolean verifyAuthorization(List<Long> ids);

    UserInfo findUserById(Long id);

    void increaseTaskCount(Long assigneeId);
}
