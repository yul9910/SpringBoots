-- 테스트용
CREATE TABLE `Users` (
                         `user_id`	LONG	NOT NULL,
                         `username`	VARCHAR	NOT NULL,
                         `user_real_id`	VARCHAR	NOT NULL,
                         `email`	VARCHAR	NOT NULL,
                         `password`	VARCHAR	NOT NULL,
                         `is_deleted`	TINYINT	NOT NULL,
                         `delete_reason`	TEXT	NULL,
                         `role`	VARCHAR	NOT NULL,
                         `provider`	VARCHAR	NOT NULL,
                         `created_at`	DATETIME	NOT NULL,
                         `updated_at`	DATETIME	NOT NULL
);