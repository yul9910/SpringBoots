-- 테스트용
INSERT INTO `Users` (`user_id`, `username`, `user_real_id`, `email`, `password`, `is_deleted`, `delete_reason`, `role`, `provider`, `created_at`, `updated_at`)
VALUES (1, 'test_user', 'test_real_id', 'test_user@example.com', 'password123', 0, NULL, 'USER', 'LOCAL', NOW(), NOW());
