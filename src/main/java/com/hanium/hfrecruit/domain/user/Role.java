package com.hanium.hfrecruit.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    COMPANYUSER("ROLE_COMPANY", "기업사용자"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
