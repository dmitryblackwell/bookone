package com.blackwell.web;

import com.blackwell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrders(Model model){
        model.addAttribute("orders", orderService.getOrders());
        return "orderview";
    }

    @DeleteMapping("/{orderNo}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteOrder(@PathVariable("orderNo") String orderNo){
        orderService.deleteOrder(orderNo);
    }

    @PostMapping("/{orderNo}")
    @ResponseStatus(code = HttpStatus.OK)
    public void approveOrder(@PathVariable("orderNo") String orderNo){
        orderService.approve(orderNo);
    }
}
