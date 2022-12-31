package com.peaksoft;


import com.peaksoft.dao.UserDaoJdbcImpl;

public class App
{
    public static void main( String[] args ){
        // реализуйте алгоритм здесь
        UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();
        userDao.createUsersTable();
//        userDao.dropUsersTable();
        userDao.saveUser("ibo","kalmatov", (byte) 26);
        userDao.saveUser("ser","sernek", (byte) 23);
        userDao.saveUser("manas","manasov", (byte) 25);
        userDao.saveUser("kubat","kubatov",(byte)21);
//        userDao.removeUserById(3);
//        userDao.cleanUsersTable();
        System.out.println(userDao.getAllUsers());

    }
}
