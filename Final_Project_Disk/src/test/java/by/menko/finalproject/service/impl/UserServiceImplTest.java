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
                {getUserForRegistrFail(3), null},
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

    @Test(description = "Tests a save method to throw a ServicePersonalException"
            + ",PersonalException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForSaveException", priority = 2)
    public void testSaveException(UserInfo user, String repeatPass)
            throws ServicePersonalException, PersonalException {
        userService.registrUser(user, repeatPass);
    }

    @Test(description = "Positive scenario of update personal data method",
            priority = 3)
    public void testPositiveUpdatePersonalData() throws Exception {
        userService.updateUser(fillUserForUpdate(1), 2, "Futurama@33", "Futurama@22");
        UserInfo actual = userService.getUser(2);
        UserInfo expectedUser = fillUserForUpdate(0);
        Assert.assertEquals(actual, expectedUser);
    }

    @DataProvider(name = "dataForUpdatePersonalDataException")
    private Object[][] getDataForUpdatePersonalDataException() {
        return new Object[][]{
                {getUserForRegistrFail(1), 1, "", ""},
                {getUserForRegistrFail(2), 2, "aaZZa4@", "aaZZa4@"},
                {getUserForRegistrFail(3), 3, " ", ""},
                {getUserForRegistrFail(4), 4, "aa123", ""},
                {getUserForRegistrFail(5), 2, "aaZZa44@", "sda"},
                {getUserForRegistrFail(6), 4, "a", ""},
                {getUserForRegistrFail(7), 1, "Aasd@12", ""},
                {fillUserForUpdate(0), 3, "", ""}
        };
    }

    @Test(description = "Tests a update personal data method to throw a"
            + " ServicePersonalException,PersonalException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForUpdatePersonalDataException", priority = 4)
    public void testUpdatePersonalDataException(UserInfo user, Integer idEntity,
                                                String oldPassword, String repeatPassword)
            throws ServicePersonalException, PersonalException {
        userService.updateUser(user, idEntity, oldPassword, repeatPassword);
    }

    @Test(description = "Positive scenario of get personal data method",
            priority = 5)
    public void testPositiveGetPersonalData() throws Exception {
        UserInfo actualUser = userService.getUser(2);
        Assert.assertEquals(actualUser, fillUserForUpdate(0));
    }

    @DataProvider(name = "dataForGetPersonalDataException")
    private Object[] getDataForGetPersonalDataException() {
        return new Object[]{0, 4, 5, 6, 7, 8, 9, 10, 123123, 1231, -123
        };
    }

    @Test(description = "Tests a get personal data method to throw a"
            + "PersonalException",
            expectedExceptions = PersonalException.class,
            dataProvider = "dataForGetPersonalDataException", priority = 6)
    public void testGetPersonalDataException(Integer idEntity)
            throws PersonalException {
        userService.getUser(idEntity);
    }

    @Test(description = "Positive scenario find by email and password",
            priority = 7)
    public void testPositiveFindByEmailAndPassword() throws Exception {
        UserInfo actualUser = userService.finUserByEmail("Futurama@mail.com", "Futurama@22");
        UserInfo expectedUser = getExpectedUser();
        expectedUser.setPassword("");
        expectedUser.setSalt("");
        Assert.assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataForFindByEmailAndPasswordException")
    private Object[][] getDataForFindByEmailAndPasswordException() {
        return new Object[][]{
                {"Asdasd@mail.com", "asdqw"},
                {"Futurama@mail.com", null},
                {null, "asda"},
                {"qwexzcz", ""},
        };
    }

    @Test(description = "Tests a get personal data method to throw a"
            + "ServicePersonalException, PersonalException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForFindByEmailAndPasswordException", priority = 8)
    public void testFindByEmailAndPasswordException(String email, String password)
            throws PersonalException, ServicePersonalException {
        userService.finUserByEmail(email, password);
    }


    private UserInfo fillUserForUpdate(Integer number) {
        UserInfo user = new UserInfo();
        user.setFirstName("Name");
        user.setLastName("Last");
        user.setEmail("Futurama@mail.com");
        user.setNickname("Nick");
        user.setImage("images/no.png");
        user.setPhone("+37529 8283127");
        if (number == 1) {
            user.setPassword("Futurama@22");
        }
        return user;
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
