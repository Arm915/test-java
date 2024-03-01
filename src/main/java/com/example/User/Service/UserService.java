package com.example.User.Service;

import com.example.User.Model.User;
import com.example.User.repository.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User user) {
        if (user != null && isValidPost(user)) {
            user.setcreated_date(LocalDate.now());
            //save เข้าไป save เข้า db
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean isValidPost(@NotNull User user) {
        return  user.getfirstName() != null && !user.getfirstName().isEmpty() &&
                user.getlastName() != null && !user.getlastName().isEmpty() &&
                user.getemail() != null && !user.getemail().isEmpty() &&
                user.getbirthday() != null &&
                user.getgender() != null && !user.getgender().isEmpty() ;
    }

    public List<User> getAllUsers() {
        //findAll return list ของ entities ทั้งหมด
        return userRepository.findAll();
    }

    public long getCount() {
        //count return เป็นจำนวนของ entities
        return userRepository.count();
    }


    public User getUsersById(Long User_id) {
        //findById ใส่ id ให้แล้วจะ return entities นั้น
        return userRepository.findById(User_id).orElse(null);
    }

    public List<User> getUsersEmail(String email) {
        //findOneBy return มาตัวเดียว
        return userRepository.findOneByEmail(email);
    }

    public List<User> findByFirstNameContaining(String firstName) {
        //findByตัวแปร return เป็น list มา และจะใส่ตัวใหญ้หรือตัวเล็กก็ได้
        //Containing ใส่กี่ตัวและตัวใหญ่หรือเล็กก็ได้ในการค้นหา
        return userRepository.findByFirstNameContaining(firstName);
    }

    public boolean editUser(Long User_id, User editedUser) {
        //findById ใส่ id ให้แล้วจะ return entities นั้น
        User user = userRepository.findById(User_id).orElse(null);
        if (user != null && isValidPut(editedUser)) {
            user.setfirstName(editedUser.getfirstName());
            user.setlastName(editedUser.getlastName());
            //save เข้าไป save เข้า db
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean isValidPut(@NotNull User editedUser) {
        return  editedUser.getfirstName() != null && !editedUser.getfirstName().isEmpty() &&
                editedUser.getlastName() != null && !editedUser.getlastName().isEmpty() ;
    }

    public boolean deleteUser(Long User_id) {
        //existsById ให้ id แล้วจะ return boolean ว่าเจอไหม
        if (userRepository.existsById(User_id)) {
            //delete จะไปหาเอง เจอแล้วก็จะลบ
            userRepository.deleteById(User_id);
            return true;
        }
        return false;
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        //findBy ตัวแปร And ตัวแปร เอาหาหาข้อมูลหลายตัวและต้องใส่ถูกทุกค่า ในฟังชั่นนี้2ตัว เอาไว้ต้นหาแบบเจาะจง
        return userRepository.findByFirstNameAndLastName(firstName , lastName);
    }
    public List<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email) {
        //findBy ตัวแปร And ตัวแปร And ตัวแปร เอาหาหาข้อมูลหลายตัวและต้องใส่ถูกทุกค่า ในฟังชั่นนี้3ตัว เอาไว้ต้นหาแบบเจาะจง
        return userRepository.findByFirstNameAndLastNameAndEmail(firstName , lastName, email);
    }

    public List<User> findByFirstNameOrLastName(String firstName, String lastName) {
        //findBy ตัวแปร Or ตัวแปร เอาหาหาข้อมูลหลายตัวและจะใส่ค่าไหนก็ได้ ในฟังชั่นนี้2ตัว เอาไว้ค้นหาหลายรายการ
        return userRepository.findByFirstNameOrLastName(firstName , lastName);
    }
    public List<User> findByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email) {
        //findBy ตัวแปร Or ตัวแปร Or ตัวแปร เอาหาหาข้อมูลหลายตัวและจะใส่ค่าไหนก็ได้ ในฟังชั่นนี้3ตัว เอาไว้ค้นหาหลายรายการ
        return userRepository.findByFirstNameOrLastNameOrEmail(firstName , lastName, email);
    }

    public List<User> findByFirstNameIs(String firstName) {
        //findByตัวแปรIs return เป็น list มา ต้องใส่พิมใหญ่พิมเล็กให้เหมือนคำที่ต้องการค้นหาทุกอย่าง หรือคือ ตัวแปร = คำที่ต้องการ
        return userRepository.findByFirstNameIs(firstName);
    }

    public List<User> findByFirstNameEquals(String firstName) {
        //findByตัวแปรEquals return เป็น list มา
        return userRepository.findByFirstNameEquals(firstName);
    }

    public List<User> findByBirthdayBetween(LocalDate startDate, LocalDate endDate) {
        //Between ใช้กับวันที่เอาไว้หาวันที่ต้องการว่าระหว่างวันไหนถึงวันไหน
        return userRepository.findByBirthdayBetween(startDate, endDate);
    }

    public List<User> findByBirthdayAfter(LocalDate date) {
        //After ใช้กับวันที่เอาไว้หาวันที่ใหม่กว่าที่เรากำหนดไป
        return userRepository.findByBirthdayAfter(date);
    }

    public List<User> findByBirthdayBefore(LocalDate date) {
        //After ใช้กับวันที่เอาไว้หาวันที่เก่ากว่าที่เรากำหนดไป
        return userRepository.findByBirthdayBefore(date);
    }

    public List<User> findByFirstNameNotLike(String firstName) {
        //Like เอาไว้หาค่าที่ใกล้เคียงกับที่ใส่ลงไปมากที่สุดไม่ว่าจะใส่ไปตำแหน่งไหน
        //NotLike เอาไว้หาค่าที่ไม่ใกล้เคียงกับที่ใส่ลงไปมากที่สุด
        //Not หาคำที่ไม่ใช่กับคำที่พิมลงไป
        return userRepository.findByFirstNameNotLike(firstName);
    }

    public List<User> findByFirstNameIn(List<String> firstName) {
        return userRepository.findByFirstNameIn(firstName);
    }

    public List<User> findByUseridLessThan(Long userid) {
        //LessThan หาตัวเลขที่มีค่าน้อยกว่า
        return userRepository.findByUseridLessThan(userid);
    }

    public long countByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email) {
        return userRepository.countByFirstNameOrLastNameOrEmail(firstName, lastName, email);
    }
}


