package com.stakeguard.api.services;

import com.stakeguard.api.models.User;

public interface UserService {
    User getUserById(Long userId);
    void recalculateTrustScore(Long userId);
}
