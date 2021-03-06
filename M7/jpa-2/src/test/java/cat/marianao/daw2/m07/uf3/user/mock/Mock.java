package cat.marianao.daw2.m07.uf3.user.mock;

import java.sql.Timestamp;
import java.util.Date;

import cat.marianao.daw2.m07.uf3.domain.User;

public class Mock {
    public static User getUser0() {
        User user = new User();
        user.setUsername("user1");
        user.setName("John Test");
        user.setEmail("john@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser1() {
        User user = new User();
        user.setUsername("user2");
        user.setName("Paul Test");
        user.setEmail("paul@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser2() {
        User user = new User();
        user.setUsername("user3");
        user.setName("Mike Test");
        user.setEmail("mike@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser3() {
        User user = new User();
        user.setUsername("user4");
        user.setName("Chris Test");
        user.setEmail("chris@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser4() {
        User user = new User();
        user.setUsername("user5");
        user.setName("Ben Test");
        user.setEmail("ben@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser5() {
        User user = new User();
        user.setUsername("user6");
        user.setName("Charli Test");
        user.setEmail("charli@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }

    public static User getUser6() {
        User user = new User();
        user.setUsername("user6");
        user.setName("Juan Test");
        user.setEmail("juan@email.com");
        user.setPassword("password");
        user.setRank(100);
        user.setActive(true);
        user.setCreatedOn(new Timestamp(new Date().getTime()));
        return user;
    }
}
