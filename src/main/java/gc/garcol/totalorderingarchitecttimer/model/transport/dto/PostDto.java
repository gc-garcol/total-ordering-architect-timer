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

    PostType type;
    String   baseCurrency;
    String   quoteCurrency;

    BigInteger amount;
    BigInteger price;

    BigInteger minQuote;
    BigInteger maxQuote;

    public static PostDto from(Post post) {
        var dto = new PostDto();
        dto.id = post.getId();
        dto.ownerId = post.getOwnerId();
        dto.type = post.getType();
        dto.amount = post.getAvailableAmount();
        dto.price = post.getPrice();
        dto.minQuote = post.getMinQuote();
        dto.maxQuote = post.getMaxQuote();
        dto.baseCurrency = post.getBaseCurrency();
        dto.quoteCurrency = post.getQuoteCurrency();
        return dto;
    }
}
