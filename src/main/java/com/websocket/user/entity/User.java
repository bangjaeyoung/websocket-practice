package com.websocket.user.entity;

import com.websocket.common.entity.BaseTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Builder
    public User(String nickname) {
        this.nickname = nickname;
    }
}
