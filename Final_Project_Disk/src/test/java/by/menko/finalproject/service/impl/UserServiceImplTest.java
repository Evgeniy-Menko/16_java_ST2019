package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.dao.impl.TransactionFactoryImpl;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.service.ServiceFactory;
import by.menko.finalproject.service.UserService;
import by.menko.finalproject.service.exception.ServicePersonalException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserServiceImplTest {

    private ServiceFactory factory;
    private UserService userService;

    @BeforeClass
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        userService = factory.createService(TypeServiceAndDao.USER);
    }

    @AfterClass
    public void tearDown() throws Exception {
        factory.close();
    }

    @DataProvider(name = "dataForSaveException")
    private Object[][] getDataForSaveException() {
        return new Object[][]{
                {getUserForRegistrFail(1), "aaZZa"},
                {getUserForRegistrFail(2), "aaZZa4@"},
                {getUserForRegistrFail(3), " "},
                {getUserForRegistrFail(4), "aa123"},
                {getUserForRegistrFail(5), "aaZZa44@"},
                {getUserForRegistrFail(6), "a"},
                {getUserForRegistrFail(7), "Aasd@12"},

        };
    }

    @Test(description = "Positive scenario of save user method", priority = 1)
    public void testPositiveSave() throws PersonalException, ServicePersonalException {
        UserInfo user = new UserInfo();
        user.setFirstName("Name");
        user.setLastName("Last");
        user.setEmail("Futurama@mail.com");
        user.setNickname("Nickname");
        user.setPassword("Futurama@33");
        UserInfo actualUser = userService.registrUser(user, "Futurama@33");
        UserInfo expectedUser = getExpectedUser();
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Tests a save method to throw a ServiceException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForSaveException", priority = 2)
    public void testSaveException(UserInfo user, String repeatPass)
            throws ServicePersonalException, PersonalException {
        userService.registrUser(user, repeatPass);
    }

    private UserInfo getExpectedUser() {
        UserInfo user = new UserInfo();
        user.setIdEntity(2);
        user.setRole(Role.USER);
        return user;
    }

    private UserInfo getUserForRegistrFail(Integer number) {
        UserInfo user = new UserInfo();
        switch (number) {
            case 1:
                user.setNickname("Nickname1");
                break;
            case 2:
                user.setFirstName("Name");
                user.setLastName("Name1");
                break;
            case 3:
                user.setNickname("Nickname");
                user.setFirstName("Name");
                user.setLastName("Name");
                user.setEmail("Futurama");
                break;
            case 4:
                user.setNickname("Nickname");
                user.setFirstName("Name");
                user.setLastName("Name");
                user.setEmail("Futurama@mail.com");
                user.setPhone("asda");
                break;
            case 5:
                user.setNickname("Nickname");
                user.setFirstName("Name");
                user.setLastName("Name");
                user.setEmail("Futurama@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("asd");
                break;
            case 6:
                user.setNickname("Nickname");
                user.setFirstName("Name");
                user.setLastName("Name");
                user.setEmail("Futurama@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("asdA@12");
                break;
            case 7:
                user.setNickname("Nickname");
                user.setFirstName("Name");
                user.setLastName("Name");
                user.setEmail("Futurama@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("Aasd@12");
                break;
        }
        return user;
    }
}
