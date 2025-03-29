package bike.service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Posts;
import bike.service.app.model.repository.PostsRepository;

@Service
class PostsService {

    @Autowired
    private PostsRepository pRepository;

    public List<Posts> getAllPosts() {
        List<Posts> pList = pRepository.findAll();

        if (pList.isEmpty()) {
            throw new RuntimeException("List is empty");
        } else { 
            return pList;
        }
    }

    
}