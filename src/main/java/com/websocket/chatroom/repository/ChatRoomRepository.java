package com.websocket.chatroom.repository;

import com.websocket.chatroom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.sender.id = :userId OR cr.receiver.id = :userId")
    List<ChatRoom> findChatRoomsBySenderIdOrReceiverId(@Param("userId") Long userId);

    @Query("SELECT cr FROM ChatRoom cr WHERE (cr.sender.id = :senderId AND cr.receiver.id = :receiverId) OR (cr.sender.id = :receiverId AND cr.receiver.id = :senderId)")
    ChatRoom findChatRoomBySenderAndReceiverIds(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
