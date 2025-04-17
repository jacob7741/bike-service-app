package bike.service.app.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Posts;
import bike.service.app.model.repository.PostsRepository;
import bike.service.app.model.Users;

@Service
public class PostsService {

    @Autowired
    private PostsRepository pRepository;
    @Autowired
    private UsersService userService;

    public List<Posts> getAllPosts() {
        List<Posts> pList = pRepository.findAll();

        if (pList.isEmpty()) {
            return pList;
        } 
        return pList;
    }

    // nowa metoda sprawdzić poprawność działania!!!
    public Posts createPost(Posts posts, String content, AtomicReference<String> fullName) {
        List<Users> uList = userService.getAllUsers();
        String userName = fullName.get();
        Users userT = new Users();

        for(Users user : uList) {
            if ((user.getFirstName() + " " + user.getLastName()).equals(userName)) {
                userT.setFirstName(user.getFirstName());
                userT.setLastName(user.getLastName());
                userT.setUserId(user.getUserId());
            }
        }

        if (fullName.get() != null) {
            posts.setContent(content);
            posts.setUserId(userT.getUserId());
            pRepository.save(posts);
        }
        

        return posts;
    }
}