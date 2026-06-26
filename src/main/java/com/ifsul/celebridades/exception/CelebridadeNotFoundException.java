package com.ifsul.celebridades.exception;

public class CelebridadeNotFoundException extends RuntimeException {

    public CelebridadeNotFoundException(Long id) {
        super("Celebridade com ID " + id + " não encontrada.");
    }
}
