package com.spring_boots.spring_boots.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * url 패스 경로 바꾸기
 * addViewController("원하는 경로").setViewName("forward:현재 정적 경로")
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /login 으로 요청이 들어오면 login/login.html로 매핑
        registry.addViewController("/").setViewName("forward:/home/home.html"); //메인페이지
        registry.addViewController("/login").setViewName("forward:/login/login.html");  //로그인페이지
        //forward 는 서버 내에서 요청을 리다이렉트하지 않고 다른 경로로 넘겨주는 방식, 내부적으로 처리됨.
        registry.addViewController("/register").setViewName("forward:/register/register.html"); //회원가입 페이지
        registry.addViewController("/mypage").setViewName("forward:/account/account.html");   //마이페이지
        registry.addViewController("/account/signout").setViewName("forward:/account-signout/account-signout.html"); //회원 탈퇴 페이지
        registry.addViewController("/account/security").setViewName("forward:/account-security/account-security.html"); //회원 정보
        registry.addViewController("/order-summary").setViewName("forward:/order-summary/order-summary.html");
        registry.addViewController("/order-list").setViewName("forward:/order-list/order-list.html");
        registry.addViewController("/order").setViewName("forward:/order/order.html");
        registry.addViewController("/order-details").setViewName("forward:/order-details/order-details.html");
        registry.addViewController("/admin-orders").setViewName("forward:/admin-orders/admin-orders.html");
        // 카테고리 관리 페이지
        registry.addViewController("/admin/categories").setViewName("forward:/admin-categories/admin-categories.html");
        // 카테고리 생성 페이지
        registry.addViewController("/admin/categories/create").setViewName("forward:/category-form/category-form.html");
        // 카테고리 수정 페이지
        registry.addViewController("/admin/categories/edit/**").setViewName("forward:/category-form/category-form.html");
        // 카테고리 상세 페이지
        registry.addViewController("/categories/how-to").setViewName("forward:/category-how-to/category-how-to.html");
        registry.addViewController("/categories/how-to/**").setViewName("forward:/category-how-to/category-how-to-detail.html");
        registry.addViewController("/categories/new-in").setViewName("forward:/category-new-or-best/category-new-or-best.html");
        registry.addViewController("/categories/best").setViewName("forward:/category-new-or-best/category-new-or-best.html");
        registry.addViewController("/categories/**").setViewName("forward:/category-detail/category-detail.html");
        // 이벤트 목록 페이지
        registry.addViewController("/events").setViewName("forward:/event-list/event-list.html");
        // 이벤트 생성 페이지
        registry.addViewController("/events/create").setViewName("forward:/event-form/event-form.html");
        // 이벤트 수정 페이지
        registry.addViewController("/events/edit").setViewName("forward:/event-form/event-form.html");
        // 이벤트 상세 페이지
        registry.addViewController("/events/**").setViewName("forward:/event-detail/event-detail.html");
        registry.addViewController("/admin/items").setViewName(("forward:/product-add/product-add.html"));
    }
}
