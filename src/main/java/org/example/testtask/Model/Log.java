package org.example.testtask.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Log_user")
@Data
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long userId;
    private String inputText;
    private String outputText;
    private LocalDateTime createdAt = LocalDateTime.now();
}
