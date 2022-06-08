package woowacourse.shoppingcart.dto.response;

import java.util.Optional;
import woowacourse.shoppingcart.domain.Cart;
import woowacourse.shoppingcart.domain.CartItem;
import woowacourse.shoppingcart.domain.Product;

public class ProductResponse {

    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;
    private Long cartId;
    private int quantity;

    public ProductResponse() {
    }

    public ProductResponse(final Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), null, 0);
    }

    public ProductResponse(final Product product, final CartItem cartItem) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), cartItem.getId(),
                cartItem.getQuantity());
    }

    public ProductResponse(final Long id, final String name, final Integer price, final String imageUrl,
                           final Long cartId, final int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public static ProductResponse of(final Cart cart, final Product product) {
        final Optional<CartItem> cartItem = cart.findByProductId(product.getId());
        return cartItem.map(item -> new ProductResponse(product, item))
                .orElseGet(() -> new ProductResponse(product));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getCartId() {
        return cartId;
    }

    public int getQuantity() {
        return quantity;
    }
}
