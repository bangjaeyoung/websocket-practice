package com.websocket.message.entity;

import com.websocket.messageroom.entity.MessageRoom;
import com.websocket.common.entity.BaseTime;
import com.websocket.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private String receiverName;

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private MessageRoom messageRoom;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User sender;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User receiver;

    @Builder
    public Message(String senderName, String receiverName, String content, MessageRoom messageRoom, User sender, User receiver) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.receiver = receiver;
    }
}
