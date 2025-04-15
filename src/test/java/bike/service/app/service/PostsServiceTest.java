package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import bike.service.app.model.Posts;
import bike.service.app.model.repository.PostsRepository;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {
    
    @Mock 
    private PostsRepository postsRepository;
    @InjectMocks
    private PostsService postsService;

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
    void testCreatePost() {
        
    }

    @Test
    void testGetAllPosts() {
         
        when(postsRepository.findAll()).thenReturn(postsL);

        List<Posts> result = postsService.getAllPosts();

        assertNotNull(postsL);
        assertEquals("content of someone post", result.get(0).getContent());
    }
}
