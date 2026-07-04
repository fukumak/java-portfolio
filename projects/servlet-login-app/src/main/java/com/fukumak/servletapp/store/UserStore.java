package com.fukumak.servletapp.store;

import com.fukumak.servletapp.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * デモ用のインメモリ・ユーザーストア。
 * アプリ再起動でデータは消える（永続化なし）。
 */
public final class UserStore {

    private static final UserStore INSTANCE = new UserStore();

    private final Map<String, User> usersByUsername = new ConcurrentHashMap<>();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    public boolean exists(String username) {
        return usersByUsername.containsKey(username);
    }

    public void save(User user) {
        usersByUsername.put(user.getUsername(), user);
    }

    public User find(String username) {
        return usersByUsername.get(username);
    }
}
