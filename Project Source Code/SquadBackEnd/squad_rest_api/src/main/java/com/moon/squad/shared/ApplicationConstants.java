package com.moon.squad.shared;

import org.jetbrains.annotations.NotNull;

abstract public class ApplicationConstants {
    //Mapping
    public static final String API = "/api";
    public static final String SLASH = "/";
    public static final String ALL_MAPPING = "*";
    public static final String ID_MAPPING = "/{id}";
    public static final String NAME_MAPPING = "/{name}";
    public static final String JOB_TITLE_MAPPING = "/{jobTitle}";
    public static final String DATE_MAPPING = "/{date}";
    public static final String ORDER_MAPPING = "/order";
    //Users
    public static final String USER_MAPPING = API + "/users";
    //Result
    public static final String RESULT_MAPPING = API + "results";
    //Report
    public static final String REPORT_MAPPING = API + "reports";
    //Evaluation
    public static final String EVALUATION_MAPPING = API + "evaluation";
    //Event
    public static final String EVENT_MAPPING = API + "events";
    //Track
    public static final String TRACK_MAPPING = API + "tracks";
    //Tasks
    public static final String TASKS_MAPPING = API + "tasks";
    //Project
    public static final String PROJECT_MAPPING = API + "projects";
    //Team
    public static final String TEAM_MAPPING = API + "teams";
    //Security
    public static final String AUTH_MAPPING = "/auth";
    public static final String SIGN_IN = "/login";
    public static final String SIGN_UP = "/register";
    //Field Names
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String JOB_TITLE = "jobTitle";
    public static final String SEQUENCE = "sequence";
    public static final String DATE = "date";
    public static final String JSON = "application/json";
    public static final String SEQ_KEY = "squad";
    public static final String EMAIL = "email";
    public static final String TOKEN = "token";
    //Cache
    public static final String CACHE_USER = "User";
    public static final String CACHE_TEAM = "Team";
    public static final String CACHE_TRACK = "Track";
    public static final String CACHE_TASK = "Track";
    public static final String CACHE_RESULT = "Result";
    public static final String CACHE_REPORT = "Report";
    public static final String CACHE_PROJECT = "Project";
    public static final String CACHE_EVENT = "Event";
    public static final String CACHE_EVALUATION = "Evaluation";
    public static final String CACHE_ID = "#id";
    public static final String CACHE_EMAIL = "#email";
    public static final String CACHE_DATE = "#date";
    //Security
    public static final String USER = "USER";
    public static final String UNAUTHORIZED = "Unauthorized";
    //Errors
    public static final String ERROR_EMAIL_NOT_FOUND = "Email Not Found";
    public static final String ERROR_INVAIL_AUTH = "Invalid Email or/and Password";
    public static final String ERROR_EXPIRE_OR_INVALID_TOKEN = "Expired or invalid JWT token";
    public static final String ROLE_NOT_FOUND = "Role Not Found";
    //Texts
    public static final String ADDED_SUCCESSFULLY = "Added Successfully";
    public static final String DELETED_SUCCESSFULLY = "Deleted Successfully";
    public static final String MESSAGE = "message";
    private static final String THIS_VALUE_CANT_BE = "This value can't be ";
    public static final String NOT_NULL = THIS_VALUE_CANT_BE + "Null";
    public static final String NOT_EMPTY = THIS_VALUE_CANT_BE + "Empty";
    public static final String NOT_BLANK = THIS_VALUE_CANT_BE + "Blank";

    @NotNull
    public static String alreadyExist(String email) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("User with email ");
        stringBuffer.append(email);
        stringBuffer.append(" already exsits");
        return stringBuffer.toString();
    }

}
