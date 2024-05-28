package zerobase.bud.news.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user", url = "http://34.64.219.120:8080/test")
public interface UserClient {
    @GetMapping(value = "/users/bookmark/{userId}")
    int getBookMark(@PathVariable("userId") Long userId);


}
