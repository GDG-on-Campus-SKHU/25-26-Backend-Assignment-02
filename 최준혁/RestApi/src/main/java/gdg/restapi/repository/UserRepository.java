package gdg.restapi.repository;

import gdg.restapi.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
//  유저 정보 저장
    User save(User user);
//  상위 50명의 유저 반환
    List<User> findAll();
//  특정 유저의 id로 해당 유저의 정보 반환
    Optional<User> findById(Long id);
//  유저의 이름, 최고 기록, 재화, 보유 스킨 갱신 후 유저 정보 반환
    User update(Long id, User user);
//  유저 탈퇴
    boolean delete(Long id);
}
