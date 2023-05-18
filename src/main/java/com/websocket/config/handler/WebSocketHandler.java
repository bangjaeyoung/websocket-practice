package com.websocket.config.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler implements ChannelInterceptor {

//    TODO 인증 관련 로직 추가
//    private final JwtTokenizer jwtTokenizer;


    // 필요시, @CrossOrigin 추가
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        if (StompCommand.CONNECT == accessor.getCommand()) {
//            String jwt = Optional.of(accessor.getFirstNativeHeader("Authorization")
//                    .substring("Bearer ".length()))
//                    .orElseThrow(() -> new RuntimeException("ACCESS_DENIED"));
//            jwtTokenizer.verifyAccessToken(jwt);
//        }
        return message;
    }
}
