package com.websocket.message.room.entity;

import com.websocket.message.entity.Message;
import com.websocket.common.entity.BaseTime;
import com.websocket.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageRoomId;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageRoomStatus messageRoomStatus;

    @Setter
    @Column(nullable = false)
    private String lastMessage;

    @Setter
    @Column(nullable = false)
    private Long lastSenderId;

    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User sender;

    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User receiver;

    @OrderBy("messageId")
    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private Set<Message> messages = new LinkedHashSet<>();

    @Builder
    public MessageRoom(Long messageRoomId, User sender, User receiver) {
        this.messageRoomId = messageRoomId;
        this.sender = sender;
        this.receiver = receiver;
    }
}
