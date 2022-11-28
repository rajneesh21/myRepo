package com.pms.user.utility;

public class Constant {

    public static class Role{
        public static final Integer ADMIN = 1;
        public static final Integer PATIENT = 2;
        public static final Integer PHYSICIAN = 3;
        public static final Integer NURSE = 4;
    }

    public static class Status{
        public static final Integer INACTVE = 0;
        public static final Integer ACTIVE = 1;
    }

    public static class Email{

        public static final Integer EmailSent = 1;
        public static final Integer EmailSentFailed = 0;

        public static class Type{
            public static final String FORGETPASSWORDEMAIL = "ForgetPassword";
        }

        public static class SubType{

        }

        public static class Subject{
            public static final String ForgetEmail = "Verify authentication passcode";
        }
    }
}
