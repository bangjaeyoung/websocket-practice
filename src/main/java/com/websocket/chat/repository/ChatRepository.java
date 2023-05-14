package com.websocket.chat.repository;

import com.websocket.chat.entity.Chat;
import com.websocket.chatroom.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllByChatRoom_ChatRoomIdOrderByChatIdDesc(ChatRoom chatRoom, Pageable pageable);
}
