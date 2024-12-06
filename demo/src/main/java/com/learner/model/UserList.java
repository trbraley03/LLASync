package com.learner.model;

import java.util.ArrayList;

/*

 Implemented the login() and addUser() methods within UserList.

 The isEmailTaken() and isUsernameTaken() methods check for unique emails and usernames when adding users.

 [This makes UserList responsible for handling user logic such as checking if an email or username exists, 
 adding users, and logging users in. This reduces the complexity in the UI and encapsulates user-related logic in one place.]

 */
public class UserList {

    private static UserList instance;
    private final ArrayList<User> users;

    // Private constructor (singleton pattern)
    private UserList() {
        users = new ArrayList<>();
    }

    // Get the instance (singleton pattern)
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Get all users
    public ArrayList<User> getUsers() {
        return users;
    }

    // Add a user, with checks for uniqueness
    public boolean addUser(User newUser) {
        if (isEmailTaken(newUser.getEmail()) || isUsernameTaken(newUser.getUsername())) {
            return false; // Email or username already exists
        }
        users.add(newUser); // Add the user if the checks pass
        return true;
    }

    // Check if an email is already taken
    public boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true; // Email exists
            }
        }
        return false;
    }

    // Check if a username is already taken
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true; // Username exists
            }
        }
        return false;
    }

    // User login
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user; // Successful login
            }
        }
        return null; // Login failed
    }

    public String registerUser(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password);
        String result = "";
        if (addUser(newUser)) {
            result = "true"; // User added successfully, could remove this line
        } else {
            if(isEmailTaken(newUser.getEmail())) {
                result += "Email already exists";
            } 
            if(!result.equals("instance")) {
                if(!result.equals("")) { result += " and "; } 
                result += "Username already exists";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserList:\n");

        if (users.isEmpty()) {
            sb.append("No users in the list.\n");
        } else {
            for (User user : users) {
                sb.append(user.toString()).append("\n");  // Assumes each User has a `toString` method
            }
        }

        return sb.toString();
    }

}
