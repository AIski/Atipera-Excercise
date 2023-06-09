package pl.Alski.zadanie.error;

public record ErrorResponse (
    int status,
    String message
){ }
