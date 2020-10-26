package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public Order completeOrder(@RequestParam Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.getById(userId));
        return orderService.completeOrder(shoppingCart.getTickets(),
                userService.getById(userId));
    }

    @GetMapping
    public List<OrderResponseDto> getAllUserOrders(@RequestParam Long userId) {
        User user = userService.getById(userId);
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }
}
