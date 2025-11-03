package gc.garcol.totalorderingarchitecttimer.view;

import gc.garcol.totalorderingarchitecttimer.model.domain.User;
import gc.garcol.totalorderingarchitecttimer.model.transport.QueryPosts;
import gc.garcol.totalorderingarchitecttimer.model.transport.QueryPostsResult;
import gc.garcol.totalorderingarchitecttimer.repository.OrderBookRepository;
import gc.garcol.totalorderingarchitecttimer.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author thaivc
 * @since 2025
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final OrderBookService    orderBookService;
    private final OrderBookRepository orderBookRepository;

    @GetMapping("/")
    public String index(Model model) {
        var query = new QueryPosts();
        query.setLimit(10);
        QueryPostsResult postsResult = (QueryPostsResult) orderBookService.getPosts(query);
        List<User> users = orderBookRepository.getUsers().values().stream().toList();
        model.addAttribute("posts", postsResult.getPosts());
        model.addAttribute("totalPost", postsResult.getTotal());
        model.addAttribute("users", users);
        return "index";
    }
}
