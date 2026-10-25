package az.shopery.chat_ms.controller;

import az.shopery.chat_ms.model.dto.response.ChatMessageResponseDto;
import az.shopery.chat_ms.model.dto.response.ConversationResponseDto;
import az.shopery.chat_ms.model.dto.shared.SuccessResponse;
import az.shopery.chat_ms.service.ChatService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatRestController {

    private final ChatService chatService;

    @GetMapping("/conversation/{otherUserId}")
    public ResponseEntity<SuccessResponse<List<ChatMessageResponseDto>>> getConversation(@PathVariable UUID otherUserId, @RequestParam String email) {
        return ResponseEntity.ok(chatService.getConversation(email, otherUserId));
    }

    @GetMapping("/conversations")
    public ResponseEntity<SuccessResponse<List<ConversationResponseDto>>> getConversations(@RequestParam String email) {
        return ResponseEntity.ok(chatService.getConversations(email));
    }
}
