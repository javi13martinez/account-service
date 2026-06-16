package com.bank.account_service.shared;

import java.util.regex.Pattern;

public class SharedRegex {
    public static Pattern DNI_PATTERN =
            Pattern.compile("\\d{8}[A-Z]");
}
