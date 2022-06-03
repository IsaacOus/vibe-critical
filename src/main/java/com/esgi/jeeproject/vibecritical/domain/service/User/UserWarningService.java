package com.esgi.jeeproject.vibecritical.domain.service.User;

import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;

import java.util.List;

public interface UserWarningService {

    List<UserWarning> getWarningsByUserId(Long userId);

    UserWarning addUserWarning(Long userId, UserWarning userWarning);
}
