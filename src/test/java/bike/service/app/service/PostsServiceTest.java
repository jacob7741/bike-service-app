package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Posts;
import bike.service.app.model.Users;
import bike.service.app.model.repository.PostsRepository;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private UsersRepository userRepository;
    @Mock
    private PostsRepository postsRepository;
    @InjectMocks
    private PostsService postsService;
    @Mock
    private UsersService userService;

    private static Posts post() {
        Posts posts = new Posts();
        posts.setContent("content of someone post");
        posts.setDate("12/04/2025");
        posts.setUserId(2);
        posts.setPostId(11);

        return posts;
    }

    private List<Posts> postsL = Arrays.asList(post());

    @Test
    public void testCreatePost() {
        Posts post = new Posts();
        String content = "Some content";
        AtomicReference<String> fullName = new AtomicReference<>("Julius Cesar");

        Users testUser = new Users();
        testUser.setFirstName("Julius");
        testUser.setLastName("Cesar");
        testUser.setUserId(10);

        List<Users> usersList = new ArrayList<>();
        usersList.add(testUser);

        when(userService.getAllUsers()).thenReturn(usersList);

        when(postsRepository.save(any(Posts.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Posts result = postsService.createPost(post, content, fullName);

        assertNotNull(result);
        assertEquals(content, result.getContent());
        assertEquals(10, result.getUserId());
    }

    @Test
    public void testGetAllPosts() {

        when(postsRepository.findAll()).thenReturn(postsL);

        List<Posts> result = postsService.getAllPosts();

        assertNotNull(postsL);
        assertEquals("content of someone post", result.get(0).getContent());
    }
}
