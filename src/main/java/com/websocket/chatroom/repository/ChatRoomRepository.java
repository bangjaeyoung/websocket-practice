package com.websocket.chatroom.repository;

import com.websocket.chatroom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value =
            "SELECT * FROM chat_room WHERE sender_id = :senderId AND receiver_id = :receiverId " +
                    "UNION SELECT * FROM chat_room WHERE sender_id = :receiverId AND receiver_id = :senderId",
            nativeQuery = true)
    ChatRoom findChatRoom(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
