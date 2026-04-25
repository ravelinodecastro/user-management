package pt.uevora.user_management.exeptions;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
        int status,
        String error,
        Instant timestamp,
        String path,
        List<FieldErrorResponse> fieldErrors
) {}
