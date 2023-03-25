package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class TeleSignSMS {
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String API_KEY = "API_KEY";
    private static final String PHONE_NUMBER_PREFIX = "55";

    public void enviarSMS(Integer posicaoFila, String nome) {
        try {
            String mensagem = "Olá " + nome + ", sua posição na fila é " + posicaoFila;
            String numeroTelefone = PHONE_NUMBER_PREFIX + "081998548738";
            TeleSign.getInstance(CUSTOMER_ID, API_KEY).message().sms(numeroTelefone, mensagem).execute();
        } catch (TeleSignException e) {
            e.printStackTrace();
        }
    }
}
