package database;

/**
 * Created by maria on 1/19/17.
 */
public class User {
    private UserPassword userPass;
    private UserAddress userAdd;

    public User (UserPassword p, UserAddress a) {
        this.userPass = p;
        this.userAdd = a;
    }

    public UserPassword getUserPass() {
        return userPass;
    }

    public UserAddress getUserAdd() {
        return userAdd;
    }

    public void setUserPass(UserPassword userPass) {
        this.userPass = userPass;
    }

    public void setUserAdd(UserAddress userAdd) {
        this.userAdd = userAdd;
    }
}
