/*
 * 密码字段更新脚本
 * 将原 Shiro MD5 密码更新为 Spring Security BCrypt 密码
 * 所有员工默认密码重置为: 123456
 */

USE `repdb`;

-- 将所有员工密码更新为 BCrypt 加密的 123456
-- BCrypt 格式: $2a$10$开头的60字符字符串
-- 以下密码为明文 "123456" 的真实BCrypt加密结果（已验证通过）

UPDATE `emp` SET `pwd` = '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK';

-- 验证更新结果
SELECT eid, username, LEFT(pwd, 20) as pwd_prefix, name FROM emp WHERE state = '1';

-- 注意事项:
-- 1. 所有员工密码已重置为 123456
-- 2. 建议员工登录后立即修改密码
-- 3. BCrypt 密码长度为60字符，确保 pwd 字段长度足够 (VARCHAR 64+)
-- 4. 如需单独重置某个员工密码，可使用以下 SQL:
--    UPDATE emp SET pwd = '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK' WHERE username = '员工用户名';