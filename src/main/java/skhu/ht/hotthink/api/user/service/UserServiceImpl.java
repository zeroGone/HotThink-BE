package skhu.ht.hotthink.api.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.ht.hotthink.api.domain.RoleName;
import skhu.ht.hotthink.api.domain.User;
import skhu.ht.hotthink.api.user.model.NewUserDTO;
import skhu.ht.hotthink.api.user.repository.UserRepository;

import javax.management.relation.Role;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    /*
        작성자: 홍민석
        작성일: 19-10-07
        내용: 회원가입 정보를 바탕으로 새로운 계정을 생성합니다.
    */
    @Override
    public boolean setUser(NewUserDTO newUserDTO, int initPoint) {
        User user = modelMapper.map(newUserDTO,User.class);
        user.setAuth(RoleName.ROLE_MEMBER);
        user.setPoint(initPoint);//초기 포인트 설정
        user.setRealTicket(0);
        userRepository.save(user);
        return true;
    }


    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @Override
    public boolean saveUser(User user){
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
    /*
        작성자: 홍민석
        작성일: 19-10-07
        내용: 아이디가 중복되었는지 검사합니다.
    */
    public Boolean checkOverlap(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            return false;
        }
        return true;
    }
}