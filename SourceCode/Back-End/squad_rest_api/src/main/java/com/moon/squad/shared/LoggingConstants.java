package com.moon.squad.shared;

import org.jetbrains.annotations.NotNull;

public abstract class LoggingConstants {

    @NotNull
    public static String saving(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Saving ");
        stringBuffer.append(s);
        return stringBuffer.toString();
    }

    @NotNull
    public static String deleting(@NotNull Class<?> aClass, String id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Deleting ");
        stringBuffer.append(aClass.getSimpleName());
        stringBuffer.append(" with id ");
        stringBuffer.append(id);
        return stringBuffer.toString();
    }

    @NotNull
    public static String gettingById(@NotNull Class<?> aClass, String id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Getting ");
        stringBuffer.append(aClass.getSimpleName());
        stringBuffer.append(" with id ");
        stringBuffer.append(id);
        return stringBuffer.toString();
    }

    @NotNull
    public static String gettingAll(@NotNull Class<?> aClass, boolean flag) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Getting All ");
        stringBuffer.append(aClass.getSimpleName());
        stringBuffer.append("s ");
        if (flag) stringBuffer.append("By Order");
        return stringBuffer.toString();
    }

    @NotNull
    public static String idSearching(@NotNull Class<?> aClass, String id, boolean flag) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(aClass.getSimpleName());
        stringBuffer.append(" with id ");
        stringBuffer.append(id);
        if (flag) stringBuffer.append(" was Found");
        else stringBuffer.append(" wasn't Found!");
        return stringBuffer.toString();
    }

    @NotNull
    public static String loadingUserByEmail(String email) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Loading User with email ");
        stringBuffer.append(email);
        return stringBuffer.toString();
    }

}
