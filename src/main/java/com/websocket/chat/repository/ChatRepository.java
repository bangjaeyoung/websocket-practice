package com.websocket.chat.repository;

import com.websocket.chat.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllByChatRoom_ChatRoomId(Long chatRoomId, Pageable pageable);
}
