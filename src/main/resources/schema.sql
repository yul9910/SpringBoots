-- Category 테이블 생성
CREATE TABLE `category` (
                            `category_id` Long NOT NULL PRIMARY KEY,
                            `category_name` VARCHAR(255) NOT NULL,
                            `category_content` TEXT NULL,
                            `parent_category_id` BIGINT NULL,
                            `display_order` INT NOT NULL,
                            `created_at` DATETIME NOT NULL,
                            `updated_at` DATETIME NOT NULL,
                            `image_url` VARCHAR(255) NULL,
                            `is_active` BOOLEAN NOT NULL
);

-- Item 테이블 생성
CREATE TABLE `item` (
                        `item_id` Long NOT NULL,
                        `category_id` BIGINT NOT NULL,
                        `item_name` VARCHAR(255) NULL,
                        `item_price` INT NULL,
                        `item_description` VARCHAR(255) NULL,
                        `item_maker` VARCHAR(255) NULL,
                        `item_color` VARCHAR(50) NULL,
                        `created_at` DATETIME NULL,
                        `updated_at` DATETIME NULL,
                        `image_url` VARCHAR(255) NULL,
                        `item_size` INT NULL,
                        PRIMARY KEY (`item_id`),
                        CONSTRAINT `FK_Category_TO_Item_1` FOREIGN KEY (`category_id`) REFERENCES `Category` (`category_id`)
);

-- Users 테이블 생성
CREATE TABLE `users` (
                         `user_id` Long NOT NULL PRIMARY KEY,
                         `username` VARCHAR(255) NOT NULL,
                         `user_real_id` VARCHAR(255) NOT NULL,
                         `email` VARCHAR(255) NOT NULL,
                         `password` VARCHAR(255) NOT NULL,
                         `is_deleted` TINYINT NOT NULL,
                         `delete_reason` TEXT NULL,
                         `role` VARCHAR(50) NOT NULL,
                         `provider` VARCHAR(50) NOT NULL,
                         `created_at` DATETIME NOT NULL,
                         `updated_at` DATETIME NOT NULL
);

-- Orders 테이블 생성
CREATE TABLE `orders` (
                          `orders_id` Long NOT NULL PRIMARY KEY,
                          `user_id` Long NOT NULL,
                          `quantity` INT NOT NULL,
                          `orders_total_price` INT NOT NULL,
                          `discount_amount` INT NULL,
                          `delivery_fee` INT NULL,
                          `created_at` DATETIME NOT NULL,
                          `updated_at` DATETIME NOT NULL,
                          CONSTRAINT `FK_Users_TO_Orders` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);

-- OrderItems 테이블 생성
CREATE TABLE `orderItems` (
                              `order_items_id` Long NOT NULL,
                              `orders_id` Long NOT NULL,
                              `item_id` Long NOT NULL,
                              `orderitems_total_price` INT NOT NULL,
                              `orderitems_quantity` INT NOT NULL,
                              `order_status` VARCHAR(50) NOT NULL COMMENT '주문 상태 (예: "Pending", "Shipped", "Delivered", "Cancelled")',
                              `shipping_address` VARCHAR(255) NOT NULL COMMENT '주문 시 사용자가 입력한 실제 배송지',
                              `recipient_name` VARCHAR(100) NOT NULL,
                              `recipient_contact` VARCHAR(50) NOT NULL,
                              `delivery_message` VARCHAR(255) NULL,
                              `created_at` DATETIME NOT NULL,
                              `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `is_canceled` BOOLEAN NOT NULL,
                              PRIMARY KEY (`order_items_id`),
                              CONSTRAINT `FK_Orders_TO_OrderItems_1` FOREIGN KEY (`orders_id`) REFERENCES `Orders` (`orders_id`),
                              CONSTRAINT `FK_Item_TO_OrderItems_1` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`)
);

-- Event 테이블 생성
CREATE TABLE `event` (
                         `event_id` Long NOT NULL,
                         `category_id` Long NOT NULL,
                         `event_title` VARCHAR(255) NOT NULL,
                         `event_content` TEXT NULL,
                         `thumbnail_image_url` VARCHAR(255) NULL,
                         `content_image_url` VARCHAR(255) NULL,
                         `start_date` DATE NULL,
                         `end_date` DATE NULL,
                         `created_at` DATETIME NOT NULL,
                         `updated_at` DATETIME NOT NULL,
                         `is_active` BOOLEAN NOT NULL,
                         PRIMARY KEY (`event_id`),
                         CONSTRAINT `FK_Category_TO_Event_1` FOREIGN KEY (`category_id`) REFERENCES `Category` (`category_id`)
);

-- UserInfo 테이블 생성
CREATE TABLE `userInfo` (
                            `user_info_id` Long NOT NULL PRIMARY KEY,
                            `user_id` Long NOT NULL,
                            `address` VARCHAR(255) NULL,
                            `zip_code` VARCHAR(50) NULL,
                            `phone` VARCHAR(50) NULL,
                            `created_at` DATETIME NOT NULL,
                            `updated_at` DATETIME NOT NULL,
                            CONSTRAINT `FK_Users_TO_UserInfo_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);
