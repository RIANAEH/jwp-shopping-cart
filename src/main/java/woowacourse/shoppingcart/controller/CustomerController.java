package woowacourse.shoppingcart.controller;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.auth.support.UserNameResolver;
import woowacourse.shoppingcart.domain.customer.UserName;
import woowacourse.shoppingcart.dto.request.EditCustomerRequest;
import woowacourse.shoppingcart.dto.request.SignUpRequest;
import woowacourse.shoppingcart.dto.response.ExistsCustomerResponse;
import woowacourse.shoppingcart.dto.response.CustomerResponse;
import woowacourse.shoppingcart.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Valid final SignUpRequest request) {
        customerService.addCustomer(request);
        return ResponseEntity.created(URI.create("/api/customers/" + request.getUserName())).build();
    }

    @PutMapping("/me")
    public ResponseEntity<Void> edit(@UserNameResolver final UserName userName,
                                     @RequestBody @Valid final EditCustomerRequest request) {
        customerService.editCustomerByName(userName, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerResponse> customer(@UserNameResolver final UserName userName) {
        return ResponseEntity.ok(customerService.findCustomerByName(userName));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> withDraw(@UserNameResolver final UserName userName) {
        customerService.deleteCustomerByName(userName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exist")
    public ResponseEntity<ExistsCustomerResponse> exists(@RequestParam(name = "userName") final String userName) {
        return ResponseEntity.ok(customerService.existsByName(userName));
    }
}
