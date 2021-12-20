package com.example.demoBookShop.servicies;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Role;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.models.UserRole;
import com.example.demoBookShop.repositories.RoleRepository;
import com.example.demoBookShop.repositories.UserRepository;
import com.example.demoBookShop.repositories.UserRoleRepository;
import com.example.demoBookShop.validators.RoleValidation;
import com.example.demoBookShop.validators.UserValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
//@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserValidation userValidation=new UserValidation();
    private final RoleValidation roleValidation=new RoleValidation();
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       UserRoleRepository userRoleRepository) {
        this.roleRepository=roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userRoleRepository=userRoleRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws AppException {
        try{
            User user= userRepository.getById(id);
            return user;
        }catch (Exception ex){
            throw  new AppException("There isn't such user with this id="+id);
        }
    }

    public User findByEmail(String email) throws AppException{
        if(email.isBlank())
            throw new AppException("Field email is empty");
        return userRepository.findByEmail(email).orElseThrow(
                ()->new AppException("Not exist such user with this email="+email)
        );
    }

    public User findByFirstName(String firstName) throws AppException{
        if(firstName.isBlank())
            throw new AppException("Field firstName is empty");
        User user= userRepository.findByFirstName(firstName);
        if(user==null)
            throw new AppException("Not exist such user with this firstName="+firstName);
        return user;
    }

    public User findByLastName(String lastName) throws AppException{
        if(lastName.isBlank())
            throw new AppException("Field lastName is empty");
        User user= userRepository.findByLastName(lastName);
        if(user==null)
            throw new AppException("Not exist such user with this lastName="+lastName);
        return user;
    }

    public User findByUserName(String userName) throws AppException{
        if(userName.isBlank())
            throw new AppException("Field userName is empty");
        return userRepository.findByUserName(userName).orElseThrow(
                ()->new AppException("Not exist such user with this userName="+userName)
        );
    }

    public User create(User user, Role role) throws AppException{
        userValidation.validation(user);
        roleValidation.validation(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleFromDataBase=roleRepository.findByRole(role.getRole())
                .orElseThrow(()->new AppException("No such role in database"));
        User userFromDataBase=userRepository.saveAndFlush(user);
        UserRole userRole=new UserRole(userFromDataBase, roleFromDataBase);
        userRoleRepository.saveAndFlush(userRole);
        return userFromDataBase;
    }

    public Role findByRole(String rol) throws AppException{
        return roleRepository.findByRole(rol).orElseThrow(
                ()->new AppException("Not exist such role")
        );
    }

    public Role createRole(String role){
        if(role==null){
            return null;
        }
        Role role1=new Role(role);
        return roleRepository.saveAndFlush(role1);
    }

    public User delete(Long id) throws AppException {
        User user=null;
        try{
            //also need to check for children records before deleting.
            user= findUserById(id);
            userRepository.deleteById(id);
        }catch (Exception ex){
            throw new AppException("There isn't such user with this id="+id);
        }
        return user;
    }

    public User update(Long id, User user) throws AppException {
        //validation of all atributes
        userValidation.validation(user);
        User existingUser = findUserById(id);
        if(existingUser==null){
            throw new AppException("There isn't such user with this id="+id);
        }
        BeanUtils.copyProperties(user, existingUser, "id_user");
        return userRepository.saveAndFlush(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(userName).orElseThrow(
                ()->new UsernameNotFoundException("User not found!")
        );
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getUserRole().forEach(
                userRol -> authorities.add(new SimpleGrantedAuthority(userRol.getRoleData().getRole()))
        );
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),authorities);
    }
}
