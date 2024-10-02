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
public class OrdersApiController {

    // 사용자 주문 목록 조회
    @GetMapping("/api/orders")
    public ResponseEntity<List<OrderDto>> getUserOrders(@AuthenticationPrincipal UserPrincipal user) {
        List<OrderDto> orders = new ArrayList<>();
        return ResponseEntity.ok(orders);
    }

    // 특정 주문 상세 조회
    @GetMapping("/api/orders/{orders_id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long orders_id) {
        OrderDetailsDto orderDetails = new OrderDetailsDto();
        return ResponseEntity.ok(orderDetails);
    }

    // 사용자 주문 추가
    @PostMapping("/api/orders")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody PlaceOrderRequest request, @AuthenticationPrincipal UserPrincipal user) {
        OrderResponseDto response = new OrderResponseDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 사용자 주문 수정
    @PutMapping("/api/orders/{orders_id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orders_id, @RequestBody UpdateOrderRequest request) {
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orders_id)
                .status("주문이 성공적으로 수정되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }

    // 사용자 주문 취소
    @DeleteMapping("/api/orders/{orders_id}")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long orders_id) {
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orders_id)
                .status("주문이 성공적으로 취소되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }

    // 관리자 모든 주문 조회
    @GetMapping("/api/admin/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders(@AuthenticationPrincipal UserPrincipal admin) {
        List<OrderDto> orders = new ArrayList<>();
        return ResponseEntity.ok(orders);
    }

    // 관리자 주문 상태 수정
    @PatchMapping("/api/admin/orders/{orders_id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long orders_id, @RequestBody UpdateOrderStatusRequest request) {
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orders_id)
                .status("주문 상태가 성공적으로 수정되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }

    // 관리자 주문 삭제
    @DeleteMapping("/api/admin/orders/{orders_id}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable Long orders_id) {
        OrderResponseDto response = OrderResponseDto.builder()
                .ordersId(orders_id)
                .status("주문이 성공적으로 삭제되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }
}
