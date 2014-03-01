package com.urqa.android.rank;

import com.urqa.rank.ErrorRank;

public enum ErrorLevel {
    NOTHING(-1), UN_HANDLE(0), NATIVE(1), CRITICAL(2), MAJOR(3), MINOR(4);

    private final int value;

    private ErrorLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static ErrorLevel valueOf(ErrorRank rank) {
        ErrorLevel[] errorLevels = values();
        ErrorLevel errorLevel = ErrorLevel.NOTHING;
        for (ErrorLevel level : errorLevels) {
            if (level.getValue() == rank.value())
                errorLevel = level;

        }

        return errorLevel;
    }
}
