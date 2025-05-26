package com.buthdev.demo.dtos;

import com.buthdev.demo.model.Role;

public record RegisterDTO(String name, String password, Role role) {

}
