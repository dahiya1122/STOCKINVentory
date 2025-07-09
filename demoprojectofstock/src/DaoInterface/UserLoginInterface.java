package DaoInterface;

import Bean.UserLogin;

public interface UserLoginInterface {

    //for new user registeration
    public void  RegisterNewUser(UserLogin UL);

    //for already register user
    public UserLogin AlreadyRegistered(String username , String password_hash);

    //to get the full detail of specific user
    public void DetailsOfUser();

    // To get the full detail of a user by username
    public UserLogin getUserByUsername(String username);
}

