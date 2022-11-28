package com.pms.pmsinbox.service;

import com.pms.pmsinbox.Repository.UsersRepository;
import com.pms.pmsinbox.model.Users;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Service
public class UserService {

    private final UsersRepository repository;

    public Optional<List<Users>> getUserDetailsInPagination(Integer roleId) {
        // fetch all the user based on type,
        // get user type from roleId
        return  repository.findByRoleId(roleId);
    }

    public Optional<Users> getUserById(Integer userId){
       return repository.findByUserId(userId);
    }

    public Users updateUserStatus(Map<?,?> body) {
        Integer userId =( (Number) body.get("userId")).intValue();
        Optional<Users> users=getUserById(userId);
        if (users.isPresent()) {
            Integer activeStatus= (Integer) body.get("isActive");
            Users user= users.get();
            user.setIsActive(activeStatus);
            return repository.save(user);
        }
        return  null;
    }

    public  Optional<Users> fetchUserDetailsById(Integer userId) {
        return getUserById(userId);
    }

    public List<Users> getUserDetailsInPagination(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Users> pagedResult = repository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Users>();
        }
    }

}
