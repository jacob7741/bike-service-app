package bike.service.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bike.service.app.model.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer>{

}