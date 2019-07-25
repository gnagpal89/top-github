package com.gnagpal.top_github.Model;

import java.util.List;

public class DataWrapper {
    private List<User> users;
    private Throwable error;

    public DataWrapper(List<User> users) {
        this.users = users;
    }

    public DataWrapper(Throwable error) {
        this.error = error;
    }

    public List<User> getUsers() {
        return users;
    }

    public Throwable getError() {
        return error;
    }
}
