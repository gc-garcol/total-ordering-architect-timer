package gc.garcol.totalorderingarchitecttimer.model.transport.dto;

import gc.garcol.totalorderingarchitecttimer.model.domain.Post;
import gc.garcol.totalorderingarchitecttimer.model.domain.PostType;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class PostDto {
    Long id;
    Long ownerId;

    PostType   type;
    BigInteger amount;

    public static PostDto from(Post post) {
        var dto = new PostDto();
        dto.id = post.getId();
        dto.ownerId = post.getOwnerId();
        dto.type = post.getType();
        dto.amount = post.getAmount();
        return dto;
    }
}
