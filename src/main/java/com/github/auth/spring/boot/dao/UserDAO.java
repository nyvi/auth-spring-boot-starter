package com.github.auth.spring.boot.dao;

import com.github.auth.spring.boot.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author czk
 * @date 2019-11-2
 */
@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDTO getUser(String account) {
        String sql = "select id, user_name as userName, phone, pwd, enable_state as enableState from sys_user where user_name = ? and active = 1";
        List<UserDTO> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserDTO.class), account);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public Set<String> listUserPermission(Long id) {
        String sql = "SELECT c.permission "
            + "        FROM sys_role_menu a "
            + "                 INNER JOIN sys_user_role b ON a.role_id = b.role_id "
            + "                 INNER JOIN sys_menu c ON a.menu_id = c.id "
            + "        WHERE b.user_id = ?"
            + "          AND c.type = 2"
            + "          AND c.enable_state = 1"
            + "          AND c.active = 1";
        List<String> query = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(String.class), id);
        return new HashSet<>(query);
    }
}
