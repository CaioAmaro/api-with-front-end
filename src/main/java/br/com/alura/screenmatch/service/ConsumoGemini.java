package br.com.alura.screenmatch.service;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsumoGemini {

    public static String obterTraducao(String texto) {
        final String KEY_API_GEMINI = "AIzaSyCB7xQfA_Ip6ndBjKEOtGIb0wjXA64cgow";

        ChatModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(KEY_API_GEMINI)
                .modelName("gemini-1.5-flash")
                .build();

        String response = gemini.chat("Traduza para português o texto: " + texto);
        return response.trim();
    }
}
