package pt.uevora.user_management.exeptions;

public record FieldErrorResponse(
        String field,
        String message
) {}