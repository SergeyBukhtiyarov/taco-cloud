package sia.tacocloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.TacoOrder;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo= orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("tacoOrder") TacoOrder order) {
        log.info("order controller" + order.toString());

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrder order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepo.save(order);

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
