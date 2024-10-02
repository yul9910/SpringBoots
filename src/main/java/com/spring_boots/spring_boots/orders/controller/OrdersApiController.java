package com.spring_boots.spring_boots.orders.controller;

import com.spring_boots.spring_boots.orders.dto.*;
import com.sun.security.auth.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersApiController {

    // 사용자 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<OrderDto>> getUserOrders(@AuthenticationPrincipal UserPrincipal user) {
        // 사용자 주문 목록 조회 로직
        List<OrderDto> orders = new ArrayList<>();
        return ResponseEntity.ok(orders);
    }

    // 특정 주문 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long orderId) {
        // 특정 주문 상세 정보 조회 로직
        OrderDetailsDto orderDetails = new OrderDetailsDto();
        return ResponseEntity.ok(orderDetails);
    }

    // 사용자 주문 추가
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody PlaceOrderRequest request, @AuthenticationPrincipal UserPrincipal user) {
        // 장바구니 데이터를 받아 주문 추가 로직
        OrderResponseDto response = new OrderResponseDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 사용자 주문 취소
    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long orderId) {
        // 주문 취소 로직 추가 필요
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orderId)
                .status("주문이 성공적으로 취소되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }


    // 관리자 모든 주문 조회
    @GetMapping("/admin")
    public ResponseEntity<List<OrderDto>> getAllOrders(@AuthenticationPrincipal UserPrincipal admin) {
        // 관리자 주문 목록 조회 로직
        List<OrderDto> orders = new ArrayList<>();
        return ResponseEntity.ok(orders);
    }

    // 관리자 주문 상태 수정
    @PatchMapping("/admin/{orderId}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest request) {
        // 주문 상태 수정 로직
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orderId)
                .status("주문 상태가 성공적으로 수정되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }
}
