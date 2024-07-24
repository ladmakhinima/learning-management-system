package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.user.CreateUserDto;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.repositories.UserRepository;
import com.ladmakhi.lms.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Override
    public User createUser(CreateUserDto dto) throws DuplicateException {
        if (userRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateException("شماره تماس کاربر تکراری میباشد");
        }
        return userRepository.save(
                User.builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .phone(dto.getPhone())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(dto.getRole())
                        .bio(dto.getBio())
                        .profileUrl(dto.getProfileUrl())
                        .build()
        );
    }

    @Override
    public User findUserByPhoneAndPassword(String phone, String password) throws UsernameNotFoundException {
        User user = userRepository.findUserByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("اطلاعات احراز هویت کاربر نادرست میباشد"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("اطلاعات احراز هویت کاربر نادرست میباشد");
        }
        return user;
    }

    @Override
    public User findUserById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("کاربر یافت نشد"));
    }

    @Override
    public User findUserByPhone(String phone) throws NotFoundException {
        return userRepository.findUserByPhone(phone)
                .orElseThrow(() -> new NotFoundException("کاربری با این شماره تماس یافت نشد"));
    }

    @Override
    public List<User> findUsersPage(PageRequest pagination) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        return entityManager
                .createQuery(query.select(root))
                .setFirstResult(pagination.getPageNumber())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }
}
