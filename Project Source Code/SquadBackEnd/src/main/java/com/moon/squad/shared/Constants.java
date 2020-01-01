package com.moon.squad.shared;

public abstract class Constants {
    //Mapping
    public static final String SLASH = "/";
    public static final String API = "/api";
    public static final String ALL_MAPPING = "*";
    public static final String ID_MAPPING = "/{id}";
    public static final String NAME_MAPPING = "/{name}";
    public static final String JOB_TITLE_MAPPING = "/{jobTitle}";
    public static final String DATE_MAPPING = "/{date}";
    public static final String ORDER_BY_ID_MAPPING = "/orderById";
    //Users
    public static final String USER_MAPPING = "/users";
    //Result
    public static final String RESULT_MAPPING = "/results";
    //Report
    public static final String REPORT_MAPPING = "/reports";
    //Evaluation
    public static final String EVALUATION_MAPPING = "/evaluation";
    //Event
    public static final String EVENT_MAPPING = "/events";
    //Track
    public static final String TRACK_MAPPING = "/tracks";
    //Tasks
    public static final String TASKS_MAPPING = "/tasks";
    //Project
    public static final String PROJECT_MAPPING = "/projects";
    //Team
    public static final String TEAM_MAPPING = "/teams";
    //Field Names
    public static final String ID = "id";
    public static final String JOB_TITLE = "jobTitle";
    public static final String SEQUENCE = "sequence";
    public static final String DATE = "date";
    public static final String JSON = "application/json";
    public static final String SEQ_KEY = "squad";
    //Log
    public static final String ADDED_SUCCESSFULLY = "Added Successfully";
    public static final String DELETED_SUCCESSFULLY = "Deleted Successfully";
    private static final String THIS_VALUE_CANT_BE = "This value can't be ";
    public static final String NOT_NULL = THIS_VALUE_CANT_BE + "Null";
    public static final String NOT_EMPTY = THIS_VALUE_CANT_BE + "Empty";
    public static final String NOT_BLANK = THIS_VALUE_CANT_BE + "Blank";
}
